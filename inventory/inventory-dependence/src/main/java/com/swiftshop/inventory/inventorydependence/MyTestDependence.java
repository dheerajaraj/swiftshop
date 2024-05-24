package com.swiftshop.inventory.inventorydependence;

import com.basedomain.basedomain.Test;

public class MyTestDependence {
  public MyTestDependence(){

  }

  public void process(){
    Test mytest = new Test();
    System.out.println(mytest.getTestVariable());
  }
}
