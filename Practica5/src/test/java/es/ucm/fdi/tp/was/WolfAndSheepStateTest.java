package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.was.WolfAndSheepState;
import static org.junit.Assert.*;

public class WolfAndSheepStateTest {
	
	public WolfAndSheepStateTest(WolfAndSheepStateTest state, int[][] board, boolean b, int i) {
		// TODO Auto-generated constructor stub
	}

	public void WolfWin(){
		WolfAndSheepState state =new WolfAndSheepState(8);
		int [][] board;
		for(int i=1;i < state.getBoard().length;i=i+2){
			board=state.getBoard();
			board[0][i]=0;
			WolfAndSheepState newstate = new WolfAndSheepState(state,board,true,0);
			assertEquals("The winner has to be the wolf",true,newstate.isWinner(board, 0));
			
		}
	}

	public void SheepWinner(){
		WolfAndSheepState state= new WolfAndSheepState(8);
		int [][] board;
		for(int i=0;i<state.getBoard().length;i++){
			for(int j=0;j<state.getBoard().length;j++){
				board=state.getBoard();
				if(board[i][j]==0){
					if(state.validActions(0).isEmpty()){
						WolfAndSheepState newstate= new WolfAndSheepState(state,board,true,1);
						assertEquals("The winner has to be the sheep",true,newstate.isWinner(board, 1));
					}
				}
			}
		}
	}
	
	public void InitialWolf(){
		WolfAndSheepState state =new WolfAndSheepState(8);
		int [][] board=state.getBoard();
		assertEquals("The wolf only has one action",1,state.validActions(0).size());
		board[state.getBoard().length-1][0]=-1;
		board[state.getBoard().length-2][0]=0;
		state= new WolfAndSheepState(state,board,false,1);
		assertEquals("The wolf only has four actions",4,state.validActions(0).size());
	}
	
	public void InitialSheep(){
		WolfAndSheepState state =new WolfAndSheepState(8);
		int [][] board=state.getBoard();
		assertEquals("The sheep only has seven actions",7,state.validActions(1).size());
		for(int i=0;i<state.validActions(1).size();i++){
			board[0][state.getBoard().length-1]=-1;
			board[0][state.getBoard().length-2]=1;
		}
		state=new WolfAndSheepState(state,board,false,0);
		assertEquals("The sheep only has four actions",4,state.validActions(0).size());
	}
	
}
