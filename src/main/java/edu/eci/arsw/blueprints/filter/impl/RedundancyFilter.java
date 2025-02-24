package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Component
public class RedundancyFilter implements BlueprintFilter {

    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        for (int i = 1; i < points.size(); i++) {
            Point currentPoint = points.get(i);
            Point previousPoint = points.get(i - 1);
            if (!currentPoint.equals(previousPoint)) {
                filteredPoints.add(currentPoint);

            }
        }
        bp.setPoints(filteredPoints);
        return bp;
    }

    @Override
    public Set<Blueprint> filterBlueprints(Set<Blueprint> blueprints) {
        Set<Blueprint> filteredBlueprints = new HashSet<>();
        for (Blueprint bp : blueprints) {
            Blueprint filteredBlueprint = filterBlueprint(bp);
            filteredBlueprints.add(filteredBlueprint);
        }
        return filteredBlueprints;
    }
}
