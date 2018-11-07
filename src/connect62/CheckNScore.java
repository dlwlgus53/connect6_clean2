package connect62;

import java.io.FileWriter;
import java.io.IOException;

public class CheckNScore {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer;

	CheckNScore(int[][] map,double[][]scoreMap,int myColor, FileWriter writer) throws IOException{
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

	}

	public double scoreMust(double base, double d) {
		double a = (int)(base/10)*10 +d;//modify
		return a;
	}

	public boolean checkMust(int i, int j, double score) {
		boolean result = false;
		if(i<19&&i>=0&&j<19&&j>=0) {
			if(map[i][j]==0&&(scoreMap[i][j]%10==0||scoreMap[i][j]%10>score)){
				result = true;
			}
		}

		return result;
	}

	public boolean check(int i, int j) {
		boolean result = true;
		if(i<19&&i>=0&&j<19&&j>=0) {
			if(map[i][j]!=0) {
				result = false;
			}
		}
		return result;
	}
	
	int[] colNext(int i, int j, int n){
		int[] point = new int[2];
		point[0] = i;
		point[1] = j+n; 

		return point;
	}

	int[]rowNext(int i, int j, int n){
		int[] point = new int[2];
		point[0] = i+n;
		point[1] = j; 

		return point;
	}

	int[] dia1Next(int i, int j, int n){
		int[] point = new int[2];
		point[0] = i-n;
		point[1] = j+n; 

		return point;
	}

	int[] dia2Next(int i, int j, int n){
		int[] point = new int[2];
		point[0] = i-n;
		point[1] = j-n; 

		return point;
	}
	
	int[]copyToColUnit(int[]unit, int row, int col){
		if(col<map.length-6+1) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row][col+1];
			unit[k+2]=map[row][col+2];
			unit[k+3]=map[row][col+3];
			unit[k+4]=map[row][col+4];
			unit[k+5]=map[row][col+5];
		}
		return unit;
	}
	
	int[]copyToRowUnit(int[]unit, int row, int col){
		if(row<map.length-6+1) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row+1][col];
			unit[k+2]=map[row+2][col];
			unit[k+3]=map[row+3][col];
			unit[k+4]=map[row+4][col];
			unit[k+5]=map[row+5][col];
		}

		return unit;
	}
	
	int[]copyToDia2Unit(int[]unit, int row, int col){
		if(row>5&&col>5) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row-1][col-1];
			unit[k+2]=map[row-2][col-2];
			unit[k+3]=map[row-3][col-3];
			unit[k+4]=map[row-4][col-4];
			unit[k+5]=map[row-5][col-5];
		}
		return unit;
	}
	
	int[]copyToDia1Unit(int[]unit, int row, int col){
		if(row>=5&& col<=map.length-6) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row-1][col+1];
			unit[k+2]=map[row-2][col+2];
			unit[k+3]=map[row-3][col+3];
			unit[k+4]=map[row-4][col+4];
			unit[k+5]=map[row-5][col+5];
		}
		return unit;
	}
	
	
	/*map[i][j]!=0 이면 true 돌려주네
	 * 아니근데..일반인이자나 그건 더해주는건데 그래서 확률을 높히는 거자나..
	 * 왜저렇게 되어있지?
	 * 아 일단 맵이 비어있으면 +10주는거구나~
	 * 그거는 영향을 안끼치는 거구나~틀린게 아니구나~
	 */



}
