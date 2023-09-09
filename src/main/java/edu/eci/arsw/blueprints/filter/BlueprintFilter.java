/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.Blueprint;

/**
 *
 * @author Andres Oñate
 */
public interface BlueprintFilter {

    /**
     * @return Filtered Blueprint
     */
    public Blueprint filterBlueprint(Blueprint bp);


}
