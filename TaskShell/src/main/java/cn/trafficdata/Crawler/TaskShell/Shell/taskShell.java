package cn.trafficdata.Crawler.TaskShell.Shell;

import cn.trafficdata.Crawler.TaskShell.util.DBUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by Kinglf on 2016/8/15.
 */
public class taskShell {
    private static Display display;
    private static Shell shell;
    static {
        display=new Display();
        shell=new Shell(display);
        shell.setSize(900,300);
        shell.setText("智库任务添加程序");
    }

    public static void main(String[] args) {
        initView();
    }
    private static void initView(){
        shell.setLayout(new GridLayout(20,true));
        GridData labelLayoutData=new GridData(SWT.FILL,SWT.FILL,true,false,1,1);
        GridData textLayoutData=new GridData(SWT.FILL,SWT.FILL,true,false,1,1);
        Label label=new Label(shell,SWT.NONE);
        label.setText("MySql链接");
        label.setLayoutData(labelLayoutData);
        final Text urlText=new Text(shell,SWT.BORDER);
        urlText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,14,1));
        urlText.setText(DBUtils.url);
        label=new Label(shell,SWT.NONE);
        label.setText("用户名");
        label.setLayoutData(labelLayoutData);
        final Text userNameText=new Text(shell,SWT.BORDER);
        userNameText.setLayoutData(textLayoutData);
        userNameText.setText(DBUtils.username);
        label=new Label(shell,SWT.NONE);
        label.setText("密码");
        label.setLayoutData(labelLayoutData);
        final Text passWordText=new Text(shell,SWT.BORDER);
        passWordText.setLayoutData(textLayoutData);
        passWordText.setText(DBUtils.password);
        final Button button=new Button(shell,SWT.BORDER);
        button.setLayoutData(textLayoutData);
        button.setText("测试");
        button.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent selectionEvent) {
                boolean b = DBUtils.validDB(urlText.getText(), userNameText.getText(), passWordText.getText());
                if(b){
                    button.setText("OK");
                }else{
                    button.setText("NO");
                }
            }

            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        final Text taskText=new Text(shell,SWT.MULTI|SWT.BORDER|SWT.V_SCROLL);
        taskText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,19,19));
        Button insertButton=new Button(shell,SWT.BORDER);
        insertButton.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,19));
        insertButton.setText("添加");
        insertButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent selectionEvent) {
                String[] tasks=taskText.getText().split("\n");
                if(tasks.length>0){
                    InsertTask(tasks);
                }
            }

            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    public static void Message(int type,String content){
        //-1,错误
        //0,提示
        //1,警告
    }
    public static void InsertTask(String[] tasks){
        Connection connection=null;
        try {
            connection=DBUtils.getConnection();
            PreparedStatement ps=connection.prepareStatement("INSERT INTO tasktable VALUES (null,?,?)");
            connection.setAutoCommit(false);
            for(String url:tasks){
                ps.setString(1,url);
                ps.setInt(2,0);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
