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
		if(map[i][j]==0&&(scoreMap[i][j]%10==0||scoreMap[i][j]%10>score)){
			result = true;
		}

		return result;
	}

	public boolean check(int i, int j) {
		boolean result = true;
		if(map[i][j]!=0)
			result = false;
		return result;
	}
	/*map[i][j]!=0 이면 true 돌려주네
	 * 아니근데..일반인이자나 그건 더해주는건데 그래서 확률을 높히는 거자나..
	 * 왜저렇게 되어있지?
	 * 아 일단 맵이 비어있으면 +10주는거구나~
	 * 그거는 영향을 안끼치는 거구나~틀린게 아니구나~
	 */
	 
	

}
