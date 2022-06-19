package org.labProject.Agents;

/**
 * A class representing the item of a {@link Citizen}.
 */
public class Item {
    /**
     * Weight of an {@link Item}(Idk what to write else XD?).
     */
    public float weight;
    /**
     * Quantity of an {@link Item}.
     */
    public int quantity;
    /**
     * Name of an {@link Item}.
     */
    private String name;
    public Item(float w,int q, String n){
        this.weight = w;
        this.quantity = q;
        this.name = n;
    };
}
