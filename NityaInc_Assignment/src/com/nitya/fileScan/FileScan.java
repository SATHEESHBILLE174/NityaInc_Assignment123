package com.nitya.fileScan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileScan {

	public static void main(String[] args) throws IOException, ParseException {

		String path="C:\\Users\\Satheesh Bille\\Desktop\\BusinessFile";
		final File folder = new File(path);
		listFilesForFolder(folder);
		
		

	}

	
	public  static void listFilesForFolder(final File folder) throws IOException, ParseException {
		
		List<String> al=new ArrayList<String>();
		
		//iterating files 
	    for (final File fileEntry : folder.listFiles()) {
	    	Path file = Paths.get(fileEntry.getAbsolutePath());

			BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
			
			Calendar cal = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			//fetching date of creation
			Date dateCreated = df.parse(df.format(attr.creationTime().toMillis()));
			
			LocalDate date = dateCreated.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			//fetching month of creation and adding Month Name into ArrayList
			al.add(  date.getMonth().toString());
	    }
	    Map<String ,Integer> map = new HashMap<>();
	    for(  String r  : al) {
	          if(  map.containsKey(r)   ) {
	                 map.put(r, map.get(r) + 1);
	          }
	          else {
	              map.put(r, 1);
	          }
	      }
	    
	    Set< Map.Entry<String ,Integer> > entrySet = map.entrySet();
	      for(    Map.Entry<String ,Integer>  entry : entrySet     ) {
	          System.out.println(entry.getKey()+":"+entry.getValue()  );
	      }
	}
}
