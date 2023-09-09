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
    }
}
