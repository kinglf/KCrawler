package cn.trafficdata.Krawler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kinglf on 2016/8/13.
 */
public class RegexUtil {
    private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

    private final static String regexpScript="<script\\b[^<]*(?:(?!<\\/script>)<[^<]*)*<\\/script>";

    private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签

    private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

    private final static String regexDate="\\d{4}-\\d{1,2}-\\d{2}";

    /**
     *
     */


    /**
     *
     * 基本功能：替换标记以正常显示
     * <p>
     *
     * @param input
     * @return String
     */
    public String replaceTag(String input) {
        if (!hasSpecialChars(input)) {
            return input;
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i <= input.length() - 1; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                default:
                    filtered.append(c);
            }

        }
        return (filtered.toString());
    }

    /**
     *
     * 基本功能：判断标记是否存在
     * <p>
     *
     * @param input
     * @return boolean
     */
    public boolean hasSpecialChars(String input) {
        boolean flag = false;
        if ((input != null) && (input.length() > 0)) {
            char c;
            for (int i = 0; i <= input.length() - 1; i++) {
                c = input.charAt(i);
                switch (c) {
                    case '>':
                        flag = true;
                        break;
                    case '<':
                        flag = true;
                        break;
                    case '"':
                        flag = true;
                        break;
                    case '&':
                        flag = true;
                        break;
                }
            }
        }
        return flag;
    }

    /**
     *
     * 基本功能：过滤所有以"<"开头以">"结尾的标签
     * <p>
     *
     * @param str
     * @return String
     */
    public static String filterHtml(String str,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    public static String filterScript(String str){
        Pattern pattern = Pattern.compile(regexpScript);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    public static String filterHtml(String str){
        return filterHtml(str,regxpForHtml);
    }

    /**
     *
     * 基本功能：过滤指定标签
     * <p>
     *
     * @param str
     * @param tag
     *            指定标签
     * @return String
     */
    public static String fiterHtmlTag(String str, String tag) {
        String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
        Pattern pattern = Pattern.compile(regxp);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     *
     * 基本功能：替换指定的标签
     * <p>
     *
     * @param str
     * @param beforeTag
     *            要替换的标签
     * @param tagAttrib
     *            要替换的标签属性值
     * @param startTag
     *            新标签开始标记
     * @param endTag
     *            新标签结束标记
     * @return String
     * @如：替换img标签的src属性值为[img]属性值[/img]
     */
    public static String replaceHtmlTag(String str, String beforeTag,
                                        String tagAttrib, String startTag, String endTag) {
        String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
        String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
        Pattern patternForTag = Pattern.compile(regxpForTag);
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbreplace = new StringBuffer();
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
                    .group(1));
            if (matcherForAttrib.find()) {
                matcherForAttrib.appendReplacement(sbreplace, startTag
                        + matcherForAttrib.group(1) + endTag);
            }
            matcherForTag.appendReplacement(sb, sbreplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }
    public static String filterDate(String str){
        Pattern pattern = Pattern.compile(regexDate);
        Matcher matcher = pattern.matcher(str);
        boolean result1 = matcher.find();
        while (result1) {
           return matcher.group(0);
        }
        return null;
    }

    /**
     *
     * @param str
     * @param regex
     * @param //是否为完整截取,false为完整,true为不完整
     * @return
     */
    public static String filterStr(String str,String regex,boolean isWanZheng){
        int type=0;
        if(isWanZheng){
            type=1;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        boolean result1 = matcher.find();
        while (result1) {
            return matcher.group(type);
        }
        return null;
    }

}
