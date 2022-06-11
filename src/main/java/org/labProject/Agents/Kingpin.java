package org.labProject.Agents;

import org.labProject.Core.Map;

public class Kingpin extends Citizen{
  private float sellPrice;
  private float profitCut;
  public Kingpin(){
    super();
    this.sellPrice = (int) (Math.random() * (100 - 10) + 10);
    this.profitCut = (int) (Math.random() * (80 - 60) + 60);
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
