package com.developer.sunmaungoo.ref;


public final class Command implements Runnable {
	
  private String commandName;
  
  private String helpCommand;
  
  private Runnable runnable;
  
  public Command(String commandName, String helpCommand, Runnable runnable){
    this.commandName = commandName;
    
    this.helpCommand = helpCommand;
    
    this.runnable = runnable;
  }
  
  public String getName(){
    return commandName;
  }
  
  public String getHelpCommand(){
    return helpCommand;
  }
  
  public void run(){
    runnable.run();
  }
  
}

