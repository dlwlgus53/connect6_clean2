package connect62;

import java.io.FileWriter;
import java.io.IOException;

public class findBetter extends CheckNScore {



	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer;

	findBetter(int[][] map,double[][]scoreMap,int myColor, FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

	}

	double getScore(int row, int col) {

		double score = -20000;

		if(0<=col&&col<19&&0<=row&&row<19) {//범위검사

			score = scoreMap[row][col];

		}
		return score;
	}


	double scoreCase(int row, int col) {
		double score = 0;

		if(0<=col&&col<19&&0<=row&&row<19) {//범위검사
			if(map[row][col]==0)
				score = scoreMap[row][col];
			if (map[row][col]==myColor)
				score = 1000;
		}
		else
			score = -10000;


		return score;
	}


	int findBlank(int i, int j, int way){
		int blank=0;
		int[] unit = new int[6];
		int k=0;

		if (way==0) 
			unit=copyToColUnit(unit,i,j);

		else if (way==1) //row
			unit=copyToRowUnit(unit,i,j);

		else if (way==2) //dia1
			unit=copyToDia1Unit(unit,i,j);

		else if (way==3)//dia2
			unit=copyToDia2Unit(unit,i,j);


		for(k=0;k<3;k++) {
			if(unit[k]==0) {
				blank=k;
			}
		}
		return blank;
	}


	

	double[]copyToColUnitS(double[]unit, int row, int col){
		if(col<map.length-6+1) {
			int k=0;
			unit[k] = scoreMap[row][col];
			unit[k+1]=scoreMap[row][col+1];
			unit[k+2]=scoreMap[row][col+2];
			unit[k+3]=scoreMap[row][col+3];
			unit[k+4]=scoreMap[row][col+4];
			unit[k+5]=scoreMap[row][col+5];
		}
		return unit;
	}



	



	double[]copyToRowUnitS(double[]unit, int row, int col){
		if(row<map.length-6+1) {
			int k=0;
			unit[k] = scoreMap[row][col];
			unit[k+1]=scoreMap[row+1][col];
			unit[k+2]=scoreMap[row+2][col];
			unit[k+3]=scoreMap[row+3][col];
			unit[k+4]=scoreMap[row+4][col];
			unit[k+5]=scoreMap[row+5][col];
		}

		return unit;
	}

	double[]copyToDia1UnitS(double[]unit, int row, int col){
		if(row>=5&& col<=map.length-6) {
			int k=0;
			unit[k] = scoreMap[row][col];
			unit[k+1]=scoreMap[row-1][col+1];
			unit[k+2]=scoreMap[row-2][col+2];
			unit[k+3]=scoreMap[row-3][col+3];
			unit[k+4]=scoreMap[row-4][col+4];
			unit[k+5]=scoreMap[row-5][col+5];
		}
		return unit;
	}

	double[]copyToDia2UnitS(double[]unit, int row, int col){
		if(row>5&&col>5) {
			int k=0;
			unit[k] = scoreMap[row][col];
			unit[k+1]=scoreMap[row-1][col-1];
			unit[k+2]=scoreMap[row-2][col-2];
			unit[k+3]=scoreMap[row-3][col-3];
			unit[k+4]=scoreMap[row-4][col-4];
			unit[k+5]=scoreMap[row-5][col-5];
		}
		return unit;
	}

}