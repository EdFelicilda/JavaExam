/**
 * 
 */
package com.java.exam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * @author edward.t.felicilda
 *
 */
public enum Task {
	IS(110, 4, "Infra Setup", new HashSet<>(Arrays.asList(102, 103, 104))),	
	UTT(109, 6, "Users Training & Testing", new HashSet<>(Arrays.asList(102, 103, 104, 105, 106, 107))),
	PD(108, 3, "Production Deployment", new HashSet<>(Arrays.asList(102, 103, 104, 105, 106, 107, 109))),
	IT(107, 8, "Integration and Testing", new HashSet<>(Arrays.asList(102, 103, 104, 105, 106, 110))),
	PTS(106, 5, "Prepare Test Scripts", new HashSet<>(Arrays.asList(102, 103))),
	P(105, 30, "Programming", new HashSet<>(Arrays.asList(102, 103, 104))),
	D(104, 10, "Design", new HashSet<>(Arrays.asList(102, 103))),
	AR(103, 10, "Analysis & Requirements", new HashSet<>(Arrays.asList(102))),
	PI(102, 5, "Project Initiation", new HashSet<>());	
	
	private Integer taskId;
	private Date startDate;
	private Date endDate;
	private int duration;
	private String taskDescription;
	private Set<Integer> taskDependencies;
	
	private Task(Integer taskId, int duration, String taskDescription,
			HashSet<Integer> taskDependencies){
		this.taskId = taskId;
		this.duration = duration;
		this.taskDescription = taskDescription;
		this.taskDependencies = taskDependencies;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Integer getTaskId() {
		return taskId;
	}

	public int getDuration() {
		return duration;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public Set<Integer> getTaskDependencies() {
		return taskDependencies;
	}
	
	public String printDependencies(){
		String output = "";
		for(Integer str : taskDependencies){
			output += str + ", ";
		}
		return output.substring(0, output.lastIndexOf(",") == -1 ? 0 : output.lastIndexOf(","));
	}
	
}
