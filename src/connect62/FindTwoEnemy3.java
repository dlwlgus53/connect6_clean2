package connect62;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FindTwoEnemy3 extends CheckNScore{
	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	ArrayList<Integer> targetRow = new ArrayList<Integer>(2);
	ArrayList<Integer> targetCol = new ArrayList<Integer>(2);
	ArrayList<Integer> targetWay = new ArrayList<Integer>(2);

	FindTwoEnemy3(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);
	}


	double[][] execute(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException {
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

		find();
		return scoreMap;
	}

	void find() throws IOException{

		int ColTarget = colTargetCheck();
		int RowTarget = rowTargetCheck();
		int Dia1Target = dia1TargetCheck();
		int Dia2Target = dia2TargetCheck();

		//target = in unit enemy : 3 mine : 0


		/*
		if(isColTarget==true)	targetCount++;
		if(isRowTarget==true)	targetCount++;
		if(isDia1Target==true)	targetCount++;
		if(isDia2Target==true)	targetCount++;
		 */

		int targetCount = ColTarget + RowTarget + Dia1Target + Dia2Target;

		if(targetCount>=2) {
			int row = targetRow.get(0);
			int col = targetCol.get(0);
			int way = targetWay.get(0);
			setStone(row, col, way);
			row = targetRow.get(1);
			col = targetCol.get(1);
			way = targetWay.get(1);
			setStone(row, col, way);
		
		}
	}


	void findResult() throws IOException{

		//setStone(targetRow, targetCol, targetWay);
		//System.out.println("what is target Way : " + targetWay);
		/*int tempi= targetRow;
		int tempj = targetCol;
		int way = targetWay;


		if(way==0) {
			setStone(tempi, tempj, 0);
		}

		if(way==1) {
			setStone(tempi, tempj,1);
		}
		if(way==2) {
			setStone(tempi, tempj, 2);
		}

		if(way==3) {
			setStone(tempi,tempj,3);
		}*/

	}


	/*
	 * target의 수를 int로 받아와.
	 * 그래서
	 * 아니 그냥 그렇게 하면 될듯.!
	 */
	int colTargetCheck() {
		int isTarget = 0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {
					int[] unit = new int[6];
					boolean isMine = false;
					unit=copyToColUnit(unit,i,j);
					int count = 0;

					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}
					if(isMine == false && count == 3) {
						targetRow.add(i);
						targetCol.add(j);
						targetWay.add(0);
						isTarget++;
					}
				}
			}

		}

		return isTarget;
	}

	int rowTargetCheck() {
		int isTarget = 0;


		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==enemyColor) {

					int[] unit = new int[6];
					boolean isMine = false;
					unit=copyToRowUnit(unit,i,j);

					int count = 0;
					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}

					if(isMine == false && count == 3) {
						targetRow.add(i);
						targetCol.add(j);
						targetWay.add(1);
						isTarget++;
					}
				}
			}

		}

		return isTarget;
	}


	int dia1TargetCheck() {
		int isTarget = 0;


		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {

					int[] unit = new int[6];
					boolean isMine = false;
					unit=copyToDia1Unit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}

					if(isMine == false && count == 3) {
						targetRow.add(i);
						targetCol.add(j);
						targetWay.add(2);
						isTarget++;
					}
				}
			}

		}
		return isTarget;
	}

	int dia2TargetCheck() {
		int isTarget = 0;



		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {
				if(map[i][j]==enemyColor) {
					int[] unit = new int[6];
					boolean isMine = false;
					unit=copyToDia2Unit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}

					if(isMine == false && count == 3) {
						targetRow.add(i);
						targetCol.add(j);
						targetWay.add(3);
						isTarget++;
					}
				}
			}

		}
		return isTarget;

	}


	int littleUnitCounter(int[]unit) {
		int result=0;
		for(int k=0;k<6;k++) {
			if(unit[k]==myColor) {
				result++;
			}
			if(unit[k]==enemyColor) {
				result=0;
				break;
			}
		}

		return result;
	}
	int makeLittleUnitAndScore(int row,int col){

		int[] littleUnit= new int[6];
		int result=0;

		littleUnit = copyToColUnit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		littleUnit = copyToRowUnit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		littleUnit = copyToDia1Unit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		littleUnit = copyToDia2Unit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		return result;

	}

	void setStone(int i, int j, int way) throws IOException{
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);
		int index =0;
		listRow.clear();
		listCol.clear();
		int tempi = i;
		int tempj =j;
		if(way==0) {//col
			for(tempj=j;tempj<j+6;tempj++) {
				if(scoreMap[i][tempj]==-10000&&tempj>=1) {
					listRow.add(i);
					listCol.add(tempj-1);
				}
				if(scoreMap[i][tempj]==-10000&&tempj<=map.length-1) {
					listRow.add(i);
					listCol.add(tempj+1);
				}
			}
			while(index<listRow.size()) {
				if(checkMust(listRow.get(index),listCol.get(index),5.1)){
					scoreMap[listRow.get(index)][listCol.get(index)]
							=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)],5.1);
					writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") col twothree "+ 5.1 +"\n");
				}
				index++;
			}
		}

		if(way==1) {//row

			for(tempi=i;tempi<i+6;tempi++) {
				if(scoreMap[tempi][j]==-10000&&tempi>=1) {
					listRow.add(tempi-1);
					listCol.add(j);
				}
				if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {
					listRow.add(tempi+1);
					listCol.add(j);
				}
			}



			while(index<listRow.size()) {
				if(checkMust(listRow.get(index),listCol.get(index),5.2)){
					scoreMap[listRow.get(index)][listCol.get(index)]
							=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)], 5.2);
					writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") row twothree"+ 5.2 +"\n");
				}
				index++;
			}
		}
		if(way==2) {
			for(tempi=i, tempj=j;tempi>i-6;tempj++,tempi--) {
				if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi-1>=0) {
					listRow.add(tempi-1);
					listCol.add(tempj+1);
				}
				if(scoreMap[tempi][tempj]==-10000&&tempj+1>=0&&tempj+1<map.length) {
					listRow.add(tempi+1);
					listCol.add(tempj-1);
				}
			}


			while(index<listRow.size()) {

				if(checkMust(listRow.get(index),listCol.get(index),5.3)){
					scoreMap[listRow.get(index)][listCol.get(index)]
							=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)],5.3);
					writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") dia1 twoThree "+ 5.3 +"\n");
				}
				index++;
			}
		}
		if(way==3) {

			for(tempi=i, tempj=j;tempi>i-6;tempj--,tempi--) {
				if(scoreMap[tempi][tempj]==-10000&&tempj-1>=0&&tempi-1>=0) {
					listRow.add(tempi-1);//�밢�� ���� ���� �Ʒ���
					listCol.add(tempj-1);
				}
				if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi+1<map.length) {
					listRow.add(tempi+1);//�밢�� ���� ������ �� ��
					listCol.add(tempj+1);
				}
			}



			while(index<listRow.size()) {

				if(checkMust(listRow.get(index), listCol.get(index), 5.4)){
					scoreMap[listRow.get(index)][listCol.get(index)]
							=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)],5.4);
					writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") dia2 twoThree "+ 5.4 +"\n");
				}

				index++;
			}
		}
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
}
