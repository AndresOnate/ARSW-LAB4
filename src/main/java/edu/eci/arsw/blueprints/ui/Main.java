package edu.eci.arsw.blueprints.ui;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {
    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);

        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);
        Blueprint bp2 = new Blueprint("Andres", "House", pts);
        Blueprint bp3 = new Blueprint("john", "The Point", pts);

        try {
            bps.addNewBlueprint(bp);
            bps.addNewBlueprint(bp2);
            bps.addNewBlueprint(bp3);
            System.out.println("Planos registrados con exito!");
        } catch (BlueprintPersistenceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Solicitud un plano mediante su autor y nombre:");
        try {
            Blueprint resultBlueprint = bps.getBlueprint("Andres", "House");
            System.out.println(resultBlueprint);
        }catch (BlueprintNotFoundException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Solicitud de todos los planos de un autor especifico:");

        try {
            Set<Blueprint> author1Blueprints1 = bps.getBlueprintsByAuthor("john");
            System.out.println(author1Blueprints1);
        } catch (BlueprintNotFoundException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("==== Consulta de planos con filtro ===");

        Point pt1 = new Point(2, 2);
        Point pt2 = new Point(3, 3);
        Point pt3 = new Point(18, 18);
        Point pt4 = new Point(5, 5);
        Point pt5 = new Point(0, 0);


        Blueprint bp4 = new Blueprint("john", "House Filter");
        bp4.addPoint(pt1);
        bp4.addPoint(pt2);
        bp4.addPoint(pt3);
        bp4.addPoint(pt4);
        bp4.addPoint(pt5);

        System.out.println("==== Agregar Plano ===");
        try {
            bps.addNewBlueprint(bp4);
        } catch (BlueprintPersistenceException e) {
            throw new RuntimeException(e);
        }

        System.out.println("==== Consultando Plano Autor y Nombre ===");
        try {
            Blueprint resultBlueprint = bps.getBlueprint("john", "House Filter");
            System.out.println(resultBlueprint);
        }catch (BlueprintNotFoundException e){
            System.out.println(e.getMessage());
        }

        System.out.println("==== Consultando Planos de Autor ===");
        try {
            System.out.println(bps.getBlueprintsByAuthor("john"));
        } catch (BlueprintNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
