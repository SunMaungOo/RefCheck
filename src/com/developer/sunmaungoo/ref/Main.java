package com.developer.sunmaungoo.ref;

import java.util.ArrayList;

public class Main{

	private static ArrayList<Command> commands;

	public static void main(String[] args){

		commands = new ArrayList<Command>();
		
		commands.add(new Command("-f", "Check the reference in the file",
				new Runnable(){
					
					@Override
					public void run(){
						// TODO Auto-generated method stub
					
						  if(args.length == 3){
							  				  
				              String fileLocation = args[1];
				            
				              String className = args[2];
				            
				              fileCommand(unquote(fileLocation), 
				            		  unquote(className));
				              
				          }else{
				        	  
				        	  displayHelp(commands);
				        	   
				          }				  
					}
				}));
		
		commands.add(new Command("-l", 
				"Check the reference in the single line", new Runnable() {
			
			@Override
			public void run(){
				// TODO Auto-generated method stub
				if(args.length == 3){
					
							
					String line = args[1];

					String className = args[2];

					lineCommand(unquote(line), unquote(className));
					
				}else{
					
					displayHelp(Main.commands);
					
				}
			}
		}));
		
		processCommand(commands, args);
	}

	private static void processCommand(ArrayList<Command> commands,
			String[] args){
		
		if(args.length >= 1){
			
			String commandName = args[0];

			Command command = getCommand(commands, commandName);
			
			if(command != null){
				
				command.run();
				
			}else{
				
				displayHelp(commands);
				
			}
			
		}else{
			displayHelp(commands);
		}
			
			
	}

	private static Command getCommand(ArrayList<Command> commands,
			String commandName) {
		
		for(Command command : commands){
			
			if(command.getName().equals(commandName)){			
				return command;				
			}
			
		}
		return null;
	}

	private static void displayHelp(ArrayList<Command> commands) {
		
		for(Command command : commands){
			
			System.out.println(command.getName() + 
					":" + command.getHelpCommand());
			
		}
		
		System.out.println("Usage : java -jar ref.jar -f \"C:\\test\\Other.java\" \"ClassNameToSearch\"");

		System.out.println("Usage : java -jar ref.jar -l \"MyClass variable;\" \"ClassNameToSearch\"");
	}

	private static void fileCommand(String fileLocation,
			String javaClassName){
		
		RefChecker refChecker = new RefChecker();
		
		if(refChecker.hasReferenceFile(javaClassName, fileLocation)){
			
			System.out.println("Reference found");
			
		}else{
			
			System.out.println("No reference found");
			
		}
	}

	private static void lineCommand(String line,
			String javaClassName){
		
		RefChecker refChecker = new RefChecker();
		
		if(refChecker.hasReferenceLine(javaClassName, line)){
			
			System.out.println("Reference found");
			
		}else{
			
			System.out.println("No reference found");
			
		}
	}

	private static String unquote(String input) {
		
		return input.replaceAll("^[\"']+|[\"']+$", "");
		
	}
}
