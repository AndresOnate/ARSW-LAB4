package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filter.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.filter.impl.SubsamplingFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubsamplingFilterTest {

    private SubsamplingFilter subsamplingFilter;

    private Blueprint blueprint;
    @BeforeEach
    public void setUp() {
        subsamplingFilter = new SubsamplingFilter();
        blueprint = new Blueprint("Jhon", "Home");
    }


    @Test
    public void shouldDeleteAlternating() {
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4)
        ));

        Blueprint filteredBlueprint = subsamplingFilter.filterBlueprint(blueprint);

        assertEquals(2, filteredBlueprint.getPoints().size());
    }


    @Test
    public void shoudDeleteAlternatingOddPoints() {
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(7, 7),
                new Point(3, 3),
                new Point(5, 5)
        ));
        Blueprint filteredBlueprint = subsamplingFilter.filterBlueprint(blueprint);
        assertEquals(3, filteredBlueprint.getPoints().size());

    }

    @Test
    public void shoudFilterSetOfPoints() {
        Set<Blueprint> blueprints = new HashSet<>();
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2)
        ));
        blueprints.add(blueprint);
        Set<Blueprint> filteredBlueprints = subsamplingFilter.filterBlueprints(blueprints);
        assertEquals(1, filteredBlueprints.size());
        Blueprint filteredBlueprint = filteredBlueprints.iterator().next();
        List<Point> filteredPoints = filteredBlueprint.getPoints();
        assertEquals(2, filteredPoints.size());
    }

    @Test
    public void shouldDeleteSpecificPoints() {
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4),
                new Point(5, 5)
        ));
        Blueprint filteredBlueprint = subsamplingFilter.filterBlueprint(blueprint);
        List<Point> filteredPoints = filteredBlueprint.getPoints();
        assertEquals(3, filteredPoints.size());
        assertEquals(1, filteredPoints.get(0).getX());
        assertEquals(3, filteredPoints.get(1).getX());
        assertEquals(5, filteredPoints.get(2).getX());
    }

}