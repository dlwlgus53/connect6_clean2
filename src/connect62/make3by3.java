package connect62;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Make3by3 extends findBetter{
	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	ArrayList<Integer> targetRow = new ArrayList<Integer>(2);
	ArrayList<Integer> targetCol = new ArrayList<Integer>(2);
	ArrayList<Integer> targetWay = new ArrayList<Integer>(2);

	Make3by3(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);
	}


	double[][] execute(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException {
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

		targetRow.clear();
		targetCol.clear();
		targetWay.clear();

		find();
		return scoreMap;
	}

	void find() throws IOException{
		colTargetCheck();
		rowTargetCheck();
		dia1TargetCheck();
		dia2TargetCheck();
		
		if(targetRow.size()>=2) {
		int row = targetRow.get(0);
		int col = targetCol.get(0);
		int way = targetWay.get(0);
		System.out.println("3");
		setStone(row, col, way);
		System.out.println("4");
		row = targetRow.get(1);
		col = targetCol.get(1);
		way = targetWay.get(1);
		setStone(row, col, way);
		System.out.println("5");
		}
	}

	void colTargetCheck() {


		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				int[] unit = new int[6];

				if(unit[0]==myColor||unit[5]==myColor)
					continue;

				boolean isEnemy=false;

				unit=copyToColUnit(unit,i,j);
				int count = 0;

				for(int k=0;k<6;k++) {
					if(unit[k]==myColor) {
						count++;
					}
					if(unit[k]==enemyColor)
						isEnemy = true;
				}
				if(isEnemy == false && count == 2) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(0);
					return;
				}

			}

		}

		return;
	}

	void rowTargetCheck() {


		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {


				int[] unit = new int[6];

				if(unit[0]==myColor||unit[5]==myColor)
					continue;

				boolean isEnemy = false;
				unit=copyToRowUnit(unit,i,j);

				int count = 0;
				for(int k=0;k<6;k++) {
					if(unit[k]==myColor) {
						count++;
					}
					if(unit[k]==enemyColor)
						isEnemy = true;
				}

				if(isEnemy == false && count == 2) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(1);
					return;
				}
			}

		}

		return;
	}


	void dia1TargetCheck() {



		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {


				int[] unit = new int[6];

				if(unit[0]==myColor||unit[5]==myColor)
					continue;

				boolean isEnemy = false;
				unit=copyToDia1Unit(unit,i,j);
				int count = 0;


				for(int k=0;k<6;k++) {
					if(unit[k]==myColor) {
						count++;
					}
					if(unit[k]==enemyColor)
						isEnemy = true;
				}

				if(isEnemy == false && count == 2) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(2);
					return;

				}
			}

		}
		return;
	}

	void dia2TargetCheck() {

		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				int[] unit = new int[6];

				if(unit[0]==myColor||unit[5]==myColor)
					continue;

				boolean isEnemy = false;
				unit=copyToDia2Unit(unit,i,j);
				int count = 0;


				for(int k=0;k<6;k++) {
					if(unit[k]==myColor) {
						count++;
					}
					if(unit[k]==enemyColor)
						isEnemy = true;
				}

				if(isEnemy == false && count == 2) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(3);
					return;
				}
			}


		}
		return;

	}



	void setStone(int i, int j, int way) throws IOException{
		System.out.println("1");
		int[] point1 = new int[2];
		System.out.println("2");
		double score1=0;
		System.out.println("8");
		
		double score2=0;
		System.out.println("9");
		int[] point2 = new int[2];
		System.out.println("2");
		int index=0;

		if(way==0) {//col]
				index = findStone(i,j,way);

				point1 = colNext(i,j,index);
				score1 = getScore(point1[0],point1[1]);

				point2 = colNext(i,j, index-2);
				score2 = getScore(point2[0],point2[1]);


		}
		
		else if(way==1) {//row
			index = findStone(i,j,way);

			point1 = rowNext(i,j,index);
			score1 = getScore(point1[0],point1[1]);

			point2 = rowNext(i,j, index-2);
			score2 = getScore(point2[0],point2[1]);
		}
		

		else if(way==2) {//dai1
			index = findStone(i,j,way);

			point1 = dia1Next(i,j,index);
			score1 = getScore(point1[0],point1[1]);

			point2 = dia1Next(i,j, index-2);
			score2 = getScore(point2[0],point2[1]);

		}
		

		else {//dai2
			index = findStone(i,j,way);

			point1 = dia2Next(i,j,index);
			score1 = getScore(point1[0],point1[1]);

			point2 = dia2Next(i,j, index-2);
			score2 = getScore(point2[0],point2[1]);
		}
		
		
		
		if(score1>score2) {
			System.out.println("6");
			setStone(point1[0],point1[1],8);
			return;
			
		}
		
		else {
			System.out.println("5");
			setStone(point2[0],point2[1],8);
			return;
		}

	}

	int findMax(double[] s) {
		double max=0;
		int index=0;


		for(int i=0;i<6;i++) {
			if(max<s[i]) {
				max = s[i];
				index = i;
			}

		}

		return index;
	}

	void setStone(int row, int col, double score) throws IOException{
		if(checkMust(row,col,score)) {
			scoreMap[row][col]=scoreMust(scoreMap[row][col],score);
			writer.append("(" +row+ "," + col + ") makeMy3 "+ score+"\n");
			return;
		}

	}


	int findStone(int i, int j, int way){
		int stone=0;
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


		for(k=0;k<6;k++) {
			if(unit[k]==1) {
				stone=k;
			}
		}
		return stone;
	}







}
