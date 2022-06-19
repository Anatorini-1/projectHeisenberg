package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.Street;
import org.labProject.Core.Map;
import org.labProject.Core.Renderable;
import org.labProject.Core.Simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class from which all agents in the simulation inherit.
 * This class is abstract, as it by itself has no defined behavior in the simulation,
 * and so, an agent of this class would be pointless.
 * @see org.labProject.Agents.SimAgent
 * @see org.labProject.Core.Renderable
 */
public abstract class Citizen extends Renderable implements SimAgent {
    /**
     * Holds the information about a given agents inventory.
     * Currently, only used for storing weed at slot 0
     */
    public List<Item> inventory;
    /**
     * Stores the information about a given agents age, which may alter
     * the behavior of some agents
     * @see RegularCitizen
     */
    public int age;
    /**
     * How much money does an agent have
     * */
    public int budget;
    /**
     * How much staff can a given agent carry at a time
     * */
    public int carryCapacity;
    /**
     * Is this agent supposed to go to jail
     * @see Police
     */
    public int goJail;

    /**
     * The location on the map, where an agent currently is.
     * If set to null, the agent will not be rendered and is considered to
     * be outside the map
     */
    public Building currentLocation;

    /**
     * The {@link Building} where a given agent returns for the night,
     * or however his behavior dictates
     */
    public Building home;

    /**
     * Indicates whether this agent is supposed to perform any specific action,
     * or should he just wonder the map at random.
     */
    public boolean randomMovement = true;

    /**
     * Indicates that an agent is supposed to be removed from the simulation
     * @see Simulation
     */
    public boolean markedForDeath;

    /**
     * The default constructor for all subclasses, invoked to prevent
     * repeating the same initialization in every subclass
     */
    public Citizen(){
        super(0,0, Color.WHITE);
        this.inventory = new ArrayList<>();
        this.age = (int) (Math.random() * (90-15)+15);
        this.budget = (int) (Math.random() * (500-50)+50);
        this.carryCapacity = (int)Math.floor(Math.random()*100);
        this.home = null;
        this.goJail = 0;
        this.markedForDeath = false;
    }

    /**
     * A function which makes an agent go to a given location which is a Building (not a Street)
     * @param map      An anchor to the {@link Map} object.
     * @param building the {@link Building} an agent should go to.
     *
     */
    @Override
    public void goLocation(Map map, Building building){
            int y = (building.y % 3 == 1) ? building.y - 1 : building.y + 1;
            if(this.currentLocation.x == building.x && this.currentLocation.y == y) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(building.x).get(building.y)).enter(this);
            }else if(this.currentLocation.x % 3 == 0 && this.currentLocation.y != y) {
                if (this.currentLocation.y > y) {
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y - 1)).enter(this);
                } else{
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y + 1)).enter(this);
                }
            }else if(this.currentLocation.y % 3 == 0 && this.currentLocation.x != building.x) {
                if (this.currentLocation.x < building.x) {
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
                } else{
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
                }
            }else if(this.currentLocation.y % 3 == 0){
                if(this.currentLocation.x%3==1){
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
                }else{
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
                }
            }else if(this.currentLocation != building && !this.currentLocation.getClass().getSimpleName().equals("Street")){
                this.randomMovement(map);
            }
    }

    /**
     * Similar to {@link Citizen#goLocation(Map, Building)}, but
     * used for sending agents to a specified {@link Street}, while
     * {@link Citizen#goLocation(Map, Building)} should be used for any other type of Building
     * @param map An anchor to the {@link Map} object
     * @param street the {@link Street} an agent should go to.
     */
    public void goToStreetLocation(Map map, Building street){
        if(this.currentLocation.x % 3 == 0 && this.currentLocation.y != street.y) {
            if (this.currentLocation.y > street.y) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y - 1)).enter(this);
            } else{
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y + 1)).enter(this);
            }
        }else if(this.currentLocation.y % 3 == 0 && this.currentLocation.x != street.x) {
            if (this.currentLocation.x < street.x) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
            } else{
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
            }
        }else if(this.currentLocation.y % 3 == 0){
            if(this.currentLocation.x%3==1){
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
            }else if(this.currentLocation.x%3==2){
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
            }
        }else if(!this.currentLocation.getClass().getSimpleName().equals("Street")){
            this.randomMovement(map);
        }
    }

    /**
     * Calling this function causes an agent to move in a random direction (only walking the {@link Street} type buildings)
     @param map An anchor to the {@link Map} object
     */
    protected void randomMovement(Map map){
        ArrayList<Building> moveOptions = new ArrayList<>();

        //Can we go right?
        if(this.currentLocation.x < map.gridSize-1)
                if(map.toRender.get(this.currentLocation.x+1).get(this.currentLocation.y).getClass().getSimpleName().equals("Street"))
                    moveOptions.add((Building) map.toRender.get(this.currentLocation.x+1).get(this.currentLocation.y));
        //Can we go left?
        if(this.currentLocation.x > 0)
            if(map.toRender.get(this.currentLocation.x-1).get(this.currentLocation.y).getClass().getSimpleName().equals("Street"))
                moveOptions.add((Building) map.toRender.get(this.currentLocation.x-1).get(this.currentLocation.y));
        //Can we go up?
        if(this.currentLocation.y > 0)
            if(map.toRender.get(this.currentLocation.x).get(this.currentLocation.y-1).getClass().getSimpleName().equals("Street"))
                moveOptions.add((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y-1));
        //Can we go down?
        if(this.currentLocation.y < map.gridSize-1)
            if(map.toRender.get(this.currentLocation.x).get(this.currentLocation.y+1).getClass().getSimpleName().equals("Street"))
                moveOptions.add((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y+1));

        //Pick random direction
        if(moveOptions.size()>0){
            Building targetBuilding = moveOptions.get((int)Math.floor(Math.random()*moveOptions.size()));
            this.currentLocation.leave(this);
            targetBuilding.enter(this);
            this.currentLocation = targetBuilding;
            this.x = currentLocation.x;
            this.y = currentLocation.y;
        }

    }

    /**
     * @see SimAgent#action(Map)
     * @param map An anchor to the {@link Map} object
     */
    @Override
    public void action(Map map) {
        this.randomMovement(map);
    }

    /**
     * @see SimAgent#delete()
     */
    @Override
    public void delete(){
        this.currentLocation.leave(this);
    }
}
