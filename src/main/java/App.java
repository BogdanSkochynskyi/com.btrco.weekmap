import com.btrco.weekmap.HibernateUtil;
import org.hibernate.SessionFactory;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }
}
