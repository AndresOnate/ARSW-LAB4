package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filter.BlueprintFilter;
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

import static org.junit.jupiter.api.Assertions.*;

public class RedundacyFilterTest {

    private RedundancyFilter filter;

    private Blueprint blueprint;
    @BeforeEach
    public void setUp() {
        filter = new RedundancyFilter();
        blueprint = new Blueprint("Jhon", "Home");

    }

    @Test
    public void shouldFilterConsecutiveNotEqualPoints() {
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4)
        ));

        Blueprint filteredBlueprint = filter.filterBlueprint(blueprint);

        assertEquals(3, filteredBlueprint.getPoints().size());
    }

    @Test
    public void shouldDeleteConsecutiveEqualPoints() {
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2),
                new Point(3, 3)
        ));

        Blueprint filteredBlueprint = filter.filterBlueprint(blueprint);
        assertEquals(3, filteredBlueprint.getPoints().size());
    }

    @Test
    public void shoudFilterOddPoints() {
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2),
                new Point(3, 3),
                new Point(5, 5)
        ));
        Blueprint filteredBlueprint = filter.filterBlueprint(blueprint);
        assertEquals(4, filteredBlueprint.getPoints().size());
    }

    @Test
    public void shoudFilterSetOfPoints() {
        Set<Blueprint> blueprints = new HashSet<>();
        blueprint.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2),
                new Point(3, 3)
        ));
        blueprints.add(blueprint);
        Set<Blueprint> filteredBlueprints = filter.filterBlueprints(blueprints);
        assertEquals(1, filteredBlueprints.size());
        Blueprint filteredBlueprint = filteredBlueprints.iterator().next();
        List<Point> filteredPoints = filteredBlueprint.getPoints();
        assertEquals(3, filteredPoints.size());
    }
}