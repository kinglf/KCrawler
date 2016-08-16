import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created by Kinglf on 2016/8/15.
 */
public class testHibernate {

    public static void main(String[] args) {
        LocalNews news=new LocalNews();
        news.setAuthor("kinglf");
        news.setTitle("hehe");
        news.setContent("this is a test");
        Session session=null;
        try{
            session=HibernateUtil.getSession();
            session.beginTransaction();
            session.save(news);
            session.getTransaction().commit();
            System.out.println("数据提交成功");
        }catch (HibernateException e){

        }finally {
            if(session!=null)
                session.close();
        }
    }
}
