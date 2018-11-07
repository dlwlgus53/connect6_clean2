package connect62;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Make4by4 extends findBetter{
	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	ArrayList<Integer> targetRow = new ArrayList<Integer>(2);
	ArrayList<Integer> targetCol = new ArrayList<Integer>(2);
	ArrayList<Integer> targetWay = new ArrayList<Integer>(2);

	Make4by4(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
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
		int size=0;
		int row=0,col=0, way=0;
		
		colTargetCheck();
		rowTargetCheck();
		dia1TargetCheck();
		dia2TargetCheck();
		size = targetRow.size();
		
		if(size>=2) {
			for(int i=0;i<size;i++) {
				row = targetRow.get(i);
				col = targetCol.get(i);
				way = targetWay.get(i);
				
				makeStone(row, col, way);
			}
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
				if(isEnemy == false && count == 3) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(0);
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

				if(isEnemy == false && count == 3) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(1);
					
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

				if(isEnemy == false && count == 3) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(2);
					

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

				if(isEnemy == false && count == 3) {
					targetRow.add(i);
					targetCol.add(j);
					targetWay.add(3);
					
				}
			}


		}
		return;

	}

	void makeStone(int i, int j, int way) throws IOException{
		
		int[] point1 = new int[2];

		double score1=0;
			
		double score2=0;
	
		int[] point2 = new int[2];
	
		int index=0;

		if(way==0) {//col]
				index = findStone(i,j,way);

				point1 = colNext(i,j,index+1);
				score1 = getScore(point1[0],point1[1]);

				point2 = colNext(i,j, index-3);//
				score2 = getScore(point2[0],point2[1]);
				

				if(score1>score2) {
					setStone(point1[0],point1[1],7.0,0);
					return;
					
				}
				
				else {
				
					setStone(point2[0],point2[1],7.0,0);
					return;
				}


		}
		
		else if(way==1) {//row
			index = findStone(i,j,way);

			point1 = rowNext(i,j,index+1);
			score1 = getScore(point1[0],point1[1]);

			point2 = rowNext(i,j, index-3);
			score2 = getScore(point2[0],point2[1]);
			

			if(score1>score2) {
				setStone(point1[0],point1[1],7.0,1);
				return;
				
			}
			
			else {
			
				setStone(point2[0],point2[1],7.0,1);
				return;
			}
		}
		

		else if(way==2) {//dai1
			index = findStone(i,j,way);

			point1 = dia1Next(i,j,index+1);
			score1 = getScore(point1[0],point1[1]);

			point2 = dia1Next(i,j, index-3);
			score2 = getScore(point2[0],point2[1]);
			

			if(score1>score2) {
				setStone(point1[0],point1[1],7.0,2);
				return;
				
			}
			
			else {
			
				setStone(point2[0],point2[1],7.0,2);
				return;
			}

		}
		

		else {//dai2
			index = findStone(i,j,way);

			point1 = dia2Next(i,j,index+1);
			score1 = getScore(point1[0],point1[1]);

			point2 = dia2Next(i,j, index-3);
			score2 = getScore(point2[0],point2[1]);
			

			if(score1>score2) {
				setStone(point1[0],point1[1],7.0,3);
				return;
				
			}
			
			else {
			
				setStone(point2[0],point2[1],7.0,3);
				return;
			}
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

	void setStone(int row, int col, double score,int way) throws IOException{
		if(way==0&&checkMust(row,col,score)) {
			scoreMap[row][col]=scoreMust(scoreMap[row][col],score);
			writer.append("(" +row+ "," + col + ") col makeMy4 "+ score+"\n");
			return;
		}
		
		else if(way==1&&checkMust(row,col,score)) {
			scoreMap[row][col]=scoreMust(scoreMap[row][col],score);
			writer.append("(" +row+ "," + col + ") row makeMy4 "+ score+"\n");
			return;
		}
		
		else if(way==2&&checkMust(row,col,score)) {
			scoreMap[row][col]=scoreMust(scoreMap[row][col],score);
			writer.append("(" +row+ "," + col + ") dia1 makeMy4 "+ score+"\n");
			return;
		}
		
		else if(way==3&&checkMust(row,col,score)) {
			scoreMap[row][col]=scoreMust(scoreMap[row][col],score);
			writer.append("(" +row+ "," + col + ") dia2 makeMy4 "+ score+"\n");
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
			if(unit[k]==myColor) {
				stone=k;
			}
		}
		return stone;
	}





}
