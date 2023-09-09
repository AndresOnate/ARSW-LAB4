package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubsamplingFilter implements BlueprintFilter {

    @Override
    public Blueprint filterBlueprint(Blueprint bp){
        List<Point> points = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        filteredPoints.add(points.get(0));
        for (int i = 1; i < points.size(); i += 2) {
            filteredPoints.add(points.get(i));
        }
        bp.setPoints(filteredPoints);
        return bp;

}


}
