/**
 * 
 */
package com.java.exam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author edward.t.felicilda
 *
 */
public class TaskScheduler {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<Task> tasks = new ArrayList<Task>();		
		
		while(tasks.size() != 9){
			for(int a = 0; a < Task.values().length; a++){
				Task tmpTask = Task.values()[a];
				if(tasks.contains(tmpTask)){
					continue;
				}
				
				if(canAddTask(tasks, tmpTask)){
					assignDates(tasks, tmpTask);
				}
			}
		}
		output(tasks);
	}
	
	private static Boolean canAddTask(List<Task> tasks, Task tmpTask){
		Boolean canAdd = true;
		
		Set<Integer> dependencies = tmpTask.getTaskDependencies();
		if(dependencies.size() != 0){
			for(int a = 0; a < Task.values().length; a++){
				Task evalTask = Task.values()[a];
				if(tmpTask.getTaskId() == evalTask.getTaskId()){
					continue;
				}
				
				Iterator tmpIterator = dependencies.iterator();
				while(tmpIterator.hasNext()){
					Integer evalTaskId = (Integer) tmpIterator.next();
					for(int b = 0; b < Task.values().length; b++){
						if(evalTaskId == Task.values()[b].getTaskId()){
							if(!containTask(tasks, Task.values()[b])){
								canAdd = false;
								break;
							}
						}
					}
					if(!canAdd){
						break;
					}
				}
				if(!canAdd){
					break;
				}
			}
		}
		
		return canAdd;
	}
	
	private static Boolean containTask(List<Task> tasks, Task tmpTask){
		Boolean isExisting = false;
		Integer taskId = tmpTask.getTaskId();
		for(int a = 0; a < tasks.size(); a++){
			Task evalTask = tasks.get(a);
			if(taskId == evalTask.getTaskId()){
				isExisting = true;
				break;
			}
		}
		
		return isExisting;
	}
	
	private static void assignDates(List<Task> tasks, Task tmpTask){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if(tasks.isEmpty()){
			tmpTask.setStartDate(cal.getTime());
			cal.add(Calendar.DATE, tmpTask.getDuration());
			tmpTask.setEndDate(cal.getTime());
			tasks.add(tmpTask);
		}else{
			cal.setTime(tasks.get(tasks.size()-1).getStartDate());
			tmpTask.setStartDate(tasks.get(tasks.size()-1).getStartDate());
			cal.add(Calendar.DATE, tmpTask.getDuration());
			tmpTask.setEndDate(cal.getTime());
			tasks.add(tmpTask);
		}
	}
	
	private static void output(List<Task> tasks){
		final SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd");
		System.out.println("------------------------------------------------------------------------------------------------------");
		//System.out.print(sdf.format(new Date()));
		System.out.println("# Project Plan for Task Scheduler");
		System.out.println("# Target Start Date: " + sdf.format(tasks.get(0).getStartDate()));
		System.out.println("# Target Completion Date: " + sdf.format(tasks.get(tasks.size()-1).getStartDate()));		
		System.out.println("");
		System.out.println("# Project Tasks");
		
		System.out.format("%s%40s%40s%40s%40s%40s\n", "Task-ID", "Start-Date", "End-Date", "Duration(Days)", "Task", "Dependencies (Task-ID)");
		for(Task task : tasks){
			System.out.format("%s%40s%40s%40s%40s%40s\n", task.getTaskId(), sdf.format(task.getStartDate()), sdf.format(task.getEndDate()),
					task.getDuration(), task.getTaskDescription(), task.printDependencies());
		}
		System.out.println("");
		System.out.println("------------------------------------------------------------------------------------------------------");
	}
}
