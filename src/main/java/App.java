import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.service.MapPointService;

public class App {

    public static void main(String[] args) {
        MapPointService mapPointService = new MapPointService();

        MapPoint mapPoint = new MapPoint();
        mapPoint.setLat(111.000);
        mapPoint.setLng(000.111);
        MapPoint mapPoint1 = new MapPoint();
        mapPoint1.setLat(222.000);
        mapPoint1.setLng(000.222);
        MapPoint mapPoint2 = new MapPoint();
        mapPoint2.setLat(333.000);
        mapPoint2.setLng(000.333);

        System.out.println("START ADDING TO TABLE");
//
//        mapPointService.create(mapPoint);
//        mapPointService.create(mapPoint1);
//        mapPointService.create(mapPoint2);
        MapPoint mapPoint3 = mapPointService.findByID(1);
        System.out.println(mapPoint3);

        mapPoint.setId(1);
        mapPoint.setLat(000.000);
        mapPointService.update(mapPoint);
        System.out.println(mapPointService.findByID(1));
        for (MapPoint aL : mapPointService.findAll()) {
            System.out.println(aL);
        }

        mapPointService.delete(mapPoint2);
        mapPointService.delete(mapPoint3);

        for (MapPoint aL : mapPointService.findAll()) {
            System.out.println(aL);
        }



    }
}
