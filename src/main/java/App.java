import com.btrco.weekmap.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }
}
