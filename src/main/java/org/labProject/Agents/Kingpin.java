package org.labProject.Agents;

import org.labProject.Core.Map;

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
  public void action( Map map) {

  }

  @Override
  public void create() {}

  @Override
  public void delete() {}

  ;
}
