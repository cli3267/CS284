package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 * I pledge my honor that I have abided by the Stevens Honor System. -cli50
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        if (maze.getNCols() <= x ||maze.getNRows() <= y || x < 0 || y < 0 || maze.getColor(x,y) != NON_BACKGROUND) {
        		return false;
        }else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
        		maze.recolor(x, y, PATH);
        		return true;
        }else {
        		maze.recolor(x,y, PATH);
        		if(findMazePath(x+1, y) || findMazePath(x-1, y) || findMazePath(x, y+1) || findMazePath(x, y-1)){
        			return true;
        		} else{
        			maze.recolor(x, y, TEMPORARY);
        			return false;
        		}
        	}

    }
    /**
     * helper function for finding all maze paths
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @param result Arraylist of successful paths
     * @param trace trace of the path being explored
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    		PairInt pair = new PairInt(x,y);
		PairInt pair2 = new PairInt(x,y);
		ArrayList<PairInt> list = new ArrayList<PairInt>();
		if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x, y, PATH);
			trace.push(pair);
			list.addAll(trace);
			result.add(list);
			trace.pop();
		}else if (x>=maze.getNCols() || y>=maze.getNRows() || x<0 || y<0 || maze.getColor(x,y) != NON_BACKGROUND) {
    			return;
    		} else {
    			maze.recolor(x,y, PATH);
    			trace.push(pair2); 
    			//finds all directions
       		findMazePathStackBased(x+1,y,result,trace);
       		findMazePathStackBased(x,y+1,result,trace);
       		findMazePathStackBased(x-1,y,result, trace);
       		findMazePathStackBased(x,y-1,result,trace);
       		maze.recolor(x, y,NON_BACKGROUND);
       		trace.pop();
       		
    		}
    }
    /**
     * finds all the possible paths to the maze
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @return Arraylist of all possible paths to the maze
     */
    
    public ArrayList <ArrayList <PairInt>> findAllMazePaths(int x, int y) { 
    		ArrayList <ArrayList <PairInt>> result = new ArrayList<>();
    		if(!findMazePath(x,y)) {
    			maze.recolor(PATH, NON_BACKGROUND);
    			maze.recolor(TEMPORARY, NON_BACKGROUND);
    			ArrayList<PairInt> list = new ArrayList<PairInt>();
    			result.add(list);
    		}else {
    			maze.recolor(PATH, NON_BACKGROUND);
    			maze.recolor(TEMPORARY, NON_BACKGROUND);
        		Stack<PairInt> trace = new Stack<>(); 
    			findMazePathStackBased(x,y,result, trace);
    		}
    		return result;
    }
    /**
     * finding the shortest path 
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @return Arraylist that has the shortest path to the exit
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    		maze.recolor(PATH, NON_BACKGROUND);
    		ArrayList <ArrayList <PairInt>> result = findAllMazePaths(x,y);
    		if(result.size() == 0) {
    			ArrayList<PairInt> pair = new ArrayList<PairInt>();
    			return pair;
    		}else {
    			ArrayList<PairInt> minimum = result.get(0);
    			int min = minimum.size();
    			for(int i = 1; i < result.size(); i++) {
    				if(min >= result.get(i).size()) {
    					minimum = result.get(i);
    					min = minimum.size();
    				}
    			}
    			return minimum;
    		}
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
