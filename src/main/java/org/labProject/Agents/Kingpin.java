package org.labProject.Agents;

public class Kingpin extends Citizen{
  private float sellPrice;
  private int operationRange;
  private float profitCut;
  public Kingpin(float s,int o, float p){
    super();
    this.sellPrice = s;
    this.operationRange = o;
    this.profitCut = p;
  }
  @Override
  public void action(){};
}
