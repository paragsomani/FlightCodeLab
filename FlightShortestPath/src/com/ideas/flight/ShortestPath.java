/**
 * 
 */
package com.ideas.flight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;

/**
 * @author PARAGS
 * Assumptions: Input matrix is hard coded for quick turn around along with source location are considered as integer instead of strings
 * For hard coded matrix, please see .jpeg image of graph
 * Name of file is: "InputGraph for problem statement.PNG"
 * 									 {{0, 9, 6, 5, 13}, 
									  {0, 0, 0, 0, 10},
									  {0, 2, 0, 4, 10}, 
									  {0, 0, 0, 0, 1},
									  {0, 0, 0, 0, 0}}
 * 
 * Two junit test cases are added, i am trying to upload dependent jar file, in case of issue, please add junit jars
 * 
 * Sample output for above input for source location 3 and end location 5 is:
 * "Source location 3 to end location 5 has 5 planes"
 *
 */
public class ShortestPath {

	private Set<Integer> visitedNodes;
	private Set<Integer> unVisitedNodes;
	private Set<Integer> availableNodes;
	private int neighbourMatrix[][];
	private int planes[];
	private int numberOfNodes;

	/**
	 * Parametarised constructor to initialise
	 * @param numberOfNodes
	 */
	
	public ShortestPath(int numberOfNodes)
	{
		super();
		//Defined as sets because set will address duplicate member issue 
		this.visitedNodes = new HashSet<Integer>();
		this.unVisitedNodes = new HashSet<Integer>();
		this.availableNodes = new HashSet<Integer>();
		this.neighbourMatrix = new int[numberOfNodes][numberOfNodes];
		this.planes = new int[numberOfNodes];
		this.numberOfNodes = numberOfNodes;
	}

	/**
	 * This method is implemented using Dikshtras algorithm
	 * @param startNode
	 * @param endNode
	 */
	public void shortestPathAlgo(int startNode, int endNode)
	{
		int curNode;
		int positionOfCurNode = 0;
		for (int i = 0; i < getAvailableNodes().size(); i++) {
			Iterator avlItr = getAvailableNodes().iterator();
			while (avlItr.hasNext()) {
				// Retrieve one by one node and point it as curNode and once
				// done add it to visited node set
				curNode = (Integer) avlItr.next();
				// System.out.println("Current Node is: " + curNode);

				findMinFlights(positionOfCurNode, startNode, endNode);

				visitedNodes.add(curNode);
				positionOfCurNode++;
			}

		}

	}

	private void findMinFlights(int curNode, int startNode, int endNode)
	{
		//Set start node with zero value and start navigating non zero values of neighbour matrix and add it to unvisited nodes
		//start navigating one by one unvisited nodes and remove after completion and add it to visited matrix
		//visited set is not used as of now, but can be used for future purpose in case to tweak logic
		
		planes[startNode - 1] = 0;
		unVisitedNodes.add(startNode - 1);
		
		//This variable is temporarily used and also work as flag to remove and add in visited and unvisited nodes 
		int tmpNode = -999;

		for (int i = 0; i < neighbourMatrix.length; i++) {
			//Directly jump to start node and start navigating
			Iterator<Integer> unVisitedItr = unVisitedNodes.iterator();
			while (unVisitedItr.hasNext()) {
				tmpNode = unVisitedItr.next();
				i = tmpNode;
				break;
				//Need to break and close this loop here otherwise it will throw error of concurrent modification exception
				// as we are adding and removing member while navigating on set.
			}
			for (int j = 0; j < neighbourMatrix[i].length; j++) {
				if (neighbourMatrix[i][j] != 0) {
					// System.out.println("Neighbour for "+i+" is: "+(j+1)+" with filights "+ neighbourMatrix[i][j]);
					
					//in case of valid neighbour, add it to unvisited node. 
					unVisitedNodes.add(j);

					if (planes[j] > neighbourMatrix[i][j] + (planes[i] == 999 ? 0 : planes[i])) {
						planes[j] = neighbourMatrix[i][j] + (planes[i] == 999 ? 0 : planes[i]);
					}
				}
			}
			if (tmpNode != -999) {
				unVisitedNodes.remove(tmpNode);
				//Can be used for future purpose if needed.
				visitedNodes.add(tmpNode);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try 
		{
			int startNode = 3;
			int endNode = 5;
			ShortestPath shortestPath = new ShortestPath(5);

			// Hard coding of number if nodes to 5
			// TODO: Accept the input from command prompt
			shortestPath.setNumberOfNodes(5);
			shortestPath.setAvailableNodes(shortestPath.availableNodes);
			shortestPath.setNeighbourMatrix(shortestPath.neighbourMatrix);
			shortestPath.setPlanes(shortestPath.planes);
			shortestPath.shortestPathAlgo(startNode, endNode);
			shortestPath.printPlanes(startNode, endNode);
		}catch(ArrayIndexOutOfBoundsException ai)
		{
			System.out.println("Please check if you have entered valid inputs or there are no planes on this route: "+ai.getMessage());
		}
		catch (Exception e) {
			System.out.println("Something went bad, please check below stack trace: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to print minimum planed between two location
	 * @param startNode
	 * @param endNode
	 */
			
	public int printPlanes(int startNode, int endNode)
	{
		/*
		 * for (int i = 0; i < getPlanes().length; i++) { if (planes[i] != 999)
		 * System.out.println("Source location "+startNode+" to End location " +
		 * (i + 1) + " has planes " + planes[i]); }
		 */

		System.out.println("Source location " + startNode + " to end location "	+ endNode + " has " + planes[endNode - 1] + " planes");
		return planes[endNode - 1];

	}
	/**
	 * @return the unVisitedNodes
	 */
	public Set<Integer> getUnVisitedNodes()
	{
		return unVisitedNodes;
	}

	/**
	 * @param unVisitedNodes
	 *            the unVisitedNodes to set
	 */
	public void setUnVisitedNodes(Set<Integer> unVisitedNodes)
	{
		this.unVisitedNodes = unVisitedNodes;
	}

	/**
	 * @return the visitedNodes
	 */
	public Set<Integer> getVisitedNodes()
	{
		return visitedNodes;
	}

	/**
	 * @param visitedNodes
	 *            the visitedNodes to set
	 */
	public void setVisitedNodes(Set<Integer> visitedNodes)
	{
		this.visitedNodes = visitedNodes;
	}

	/**
	 * @return the availableNodes
	 */
	public Set<Integer> getAvailableNodes()
	{
		return availableNodes;
	}

	/**
	 * @param availableNodes
	 *            the availableNodes to set TODO: change it String, accept input
	 *            form command prompt
	 */
	public void setAvailableNodes(Set<Integer> availableNodes)
	{
		availableNodes.add(1);
		availableNodes.add(2);
		availableNodes.add(3);
		availableNodes.add(4);
		availableNodes.add(5);

		this.availableNodes = availableNodes;
	}

	/**
	 * @return the neighbourMatrix
	 */
	public int[][] getNeighbourMatrix()
	{
		return neighbourMatrix;
	}

	/**
	 * @param neighbourMatrix
	 *            the neighbourMatrix to set TODO: Accept input from command
	 *            prompt in following format
	 * 
	 * 
	 */
	public void setNeighbourMatrix(int[][] neighbourMatrix)
	{
		neighbourMatrix = new int[][]{{0, 9, 6, 5, 13}, 
									  {0, 0, 0, 0, 10},
									  {0, 2, 0, 4, 10}, 
									  {0, 0, 0, 0, 1},
									  {0, 0, 0, 0, 0}};
		this.neighbourMatrix = neighbourMatrix;
	}

	/**
	 * @return the planes
	 */
	public int[] getPlanes()
	{
		return planes;
	}

	/**
	 * @param planes
	 *            the planes to set
	 */
	public void setPlanes(int[] planes)
	{

		// Assumption: 999 is infinite value
		planes = new int[]{999, 999, 999, 999, 999};
		this.planes = planes;
	}

	/**
	 * @return the numberOfNodes
	 */
	public int getNumberOfNodes()
	{
		return numberOfNodes;
	}

	/**
	 * @param numberOfNodes
	 *            the numberOfNodes to set
	 */
	public void setNumberOfNodes(int numberOfNodes)
	{
		this.numberOfNodes = numberOfNodes;
	}

}
