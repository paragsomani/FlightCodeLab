package com.idea.flight.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ideas.flight.ShortestPath;

import static org.junit.Assert.assertEquals;

public class TestShortestPath {
	ShortestPath shortestPath ;
	int startNode ;
	int endNode ;
	int availableNodes;
	
	@Before
	public void initialise()
	{
		shortestPath = new ShortestPath(5);
		
		startNode = 3 ;
		endNode = 5 ;
		availableNodes = 5;
		
		shortestPath.setNumberOfNodes(availableNodes);
		shortestPath.setAvailableNodes(shortestPath.getAvailableNodes());
		shortestPath.setNeighbourMatrix(shortestPath.getNeighbourMatrix());
		shortestPath.setPlanes(shortestPath.getPlanes());
	}
	
	@Test
	public void findShortestPath()
	{
		shortestPath.shortestPathAlgo(startNode, endNode);
		assertEquals(5, shortestPath.printPlanes(startNode, endNode));
	}
	
	@Test
	public void findShortestPathDiffRoute()
	{
		int startNode = 1;
		int endNode = 5;
		shortestPath.shortestPathAlgo(startNode, endNode);
		assertEquals(6, shortestPath.printPlanes(startNode, endNode));
	}
	
	@Test
	public void findShortestPathException()
	{
		//Invalid test case with invalid range
		shortestPath.shortestPathAlgo(startNode+10, endNode);
		assertEquals("Please check if you have entered valid inputs: ", shortestPath.printPlanes(startNode, endNode));
	}
	

}
