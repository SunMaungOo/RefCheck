package com.developer.sunmaungoo.ref;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RefChecker {
	
  private final String SINGLE_LINE_COMMENT = "\u002f\u002f";
  
  private final String WHITE_SPACE = "\u0020";
  
  private final String EXTENDS = "extends";
  
  private final String IMPLEMENTS = "implements";
  
  private final String OPEN_BRACKET = "\u0028";
  
  private final String COMMA = "\u002c";
  
  private final String OPEN_CURLY_BRACKET = "\u007b";
  
  public boolean hasReferenceFile(String javaClassName, 
		  String fileLocation){
	
	  try(BufferedReader reader = new BufferedReader(new FileReader(fileLocation))){
		
		  String line = null;
		  
		  do{
			  
			  line = reader.readLine();
			  
			  if(hasReferenceLine(javaClassName, line)){
				  return true;
			  }
			  
		  }while(line!=null);
		  
	  } catch (FileNotFoundException e) {
		  
		  e.printStackTrace();  
		  
	  } catch (IOException e) {
		  
		  e.printStackTrace();  
		  
	  }
	  	  	  
	  return false;
		  
  }
  
  public boolean hasReferenceLine(String javaClassName, String line){
	  
    if(line == null){
    	return false;
    }
    
    String[] words = line.trim().split(WHITE_SPACE);
    
    if(words.length<2) {
    	return false;
    }
    
    int endIndex = words.length-1;
    
    for(int i = 0; i < words.length; i++){
    	
    	String currentWord = words[i];
    	
    	//if it is a comment we can ignore everything which
    	//come after comment
    	
    	if(currentWord.equals(SINGLE_LINE_COMMENT)){
    		
    		endIndex = i-1;
    		
    		break;
    		
    	}
    	
    }
    
    return checkReference(javaClassName, words, endIndex);
    
  }
  
  private boolean checkReference(String javaClassName, String[] words, int endIndex)
  {
    if(endIndex < 1){
    	return false;
    }
    
    SearchData searchData = searchWithSplit(javaClassName, 
      words, 
      endIndex);
    
    if(searchData.isFound()) {
    	
      int classNameIndex = searchData.getFoundIndex();
       
      int nextIndex = classNameIndex + 1;
      
      if(nextIndex <= endIndex){
    	  
    	  return true;
    	  
      }
      
      return checkInheritance(words, classNameIndex);
      
    }
    
    return false;
  }
  
  private boolean checkInheritance(String[] words, int classNameIndex)
  {
    if(classNameIndex < 1){
    	return false;
      
    }
    
    int previousIndex = classNameIndex - 1;
    
    String previousWord = words[previousIndex];
    
    if(previousWord.equals(EXTENDS) || 		
    		previousWord.equals(IMPLEMENTS)){
    	return true;      
    }
    
    SearchData searchData = searchWithSplit(EXTENDS, 
    		words, 
    		previousIndex);
    
    if(searchData.isFound()){
    	  	
    	return true;
    	
    }
    
    searchData = searchWithSplit(IMPLEMENTS,
    		words,
    		previousIndex);
    
    if(searchData.isFound()){
    	
    	return false;
    	
    }
    
    return false;
  }
  
  private SearchData search(String keyword,
		  String[] words,
		  int endIndex){
	  
    if(endIndex < 0){
    	
    	return new SearchData(false, -1);
    	
    }
    
    for(int i = 0; i <= endIndex; i++){
    	
      String currentWord = words[i];
       
      currentWord = removeSpecialCharacter(currentWord);
      
      if(currentWord.equals(keyword)){
    	  
        return new SearchData(true, i);
        
      }
    }
    
    return new SearchData(false, -1);
    
  }
  
  private SearchData searchWithSplit(String keyword,
		  String[] words,
		  int endIndex,
		  String[] splitWords){
	  
    if(endIndex < 0){
    	
      return new SearchData(false, -1);
      
    }
    
    for(int i = 0; i <= endIndex; i++){
    	
      String currentWord = words[i];
      
      if(currentWord.equals(keyword)){
    	  
        return new SearchData(true, i);
        
      }else{
    	 
    	  for(String splitWord : splitWords){
    		  
    		  if(currentWord.contains(splitWord)){
    			  
    			  String regexPattern = "\\"+splitWord;
    			  
    			  String[] blocks = currentWord.trim().split(regexPattern);
    			  
    			  SearchData searchData = search(keyword,
    					  blocks,
    					  blocks.length-1);
    			  
    			  if(searchData.isFound()) {
    				  
    				  return new SearchData(true, i);
    			  }
    			  
    		  }
    		  
    	  }
    	  
      }
    }
           
    return new SearchData(false, -1);
     
  }
  
  private SearchData searchWithSplit(String keyword,
		  String[] words,
		  int endIndex){
	  
	  return searchWithSplit(keyword, 
			  words, 
			  endIndex,
			  new String[]
			  {
					  OPEN_BRACKET,
					  COMMA
			  });
  }
  
  /**
   * Remove special character at the end (if exist)
   * @param input
   * @return
   */
  private String removeSpecialCharacter(String input){	 
	  
    if(input.length() > 1 &&
    		input.endsWith(OPEN_CURLY_BRACKET)){
    	
    	input = input.substring(0,input.length()-1);
    	
    }
    
    return input;
       
  }
  
}
