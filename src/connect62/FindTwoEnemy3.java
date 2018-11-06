package connect62;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FindTwoEnemy3 extends findBetter{
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

		targetRow.clear();
		targetCol.clear();
		targetWay.clear();

		find();
		return scoreMap;
	}

	void find() throws IOException{

		int ColTarget = colTargetCheck();
		int RowTarget = rowTargetCheck();
		int Dia1Target = dia1TargetCheck();
		int Dia2Target = dia2TargetCheck();


		int targetCount = ColTarget + RowTarget + Dia1Target + Dia2Target;
		System.out.println("targetcount  : " + targetCount);
		if(targetCount>=2) {
			int row = targetRow.get(0);
			int col = targetCol.get(0);
			int way = targetWay.get(0);
			System.out.println("targetrow: targetcol  = " + row +" : " +col);
			setStone(row, col, way);

		}
	}





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

		double[] score = new double[6];
		int[] frontRow = new int[3];
		int[] frontCol = new int[3];
		int[] rearRow = new int[3];
		int[] rearCol = new int[3];
		int blankRow=0;
		int blankCol=0;
		int blank=0;


		if(way==0) {//col
			blank=findBlank(i,j,0);

			if(blank!=0) {
				blankRow = i;
				blankCol = j+blank;
				stone3(blankRow, blankCol, 5.1);
				return;
			}

			if(blank==0) {//연속 3개


				System.out.println("0");
				frontRow[0] = i;	frontRow[1] = i; frontRow[2] = i;
				frontCol[0] = j-1;	frontCol[1] = j-2; frontCol[2] = j-3;

				rearRow[0] = i;	rearRow[1] = i; rearRow[2] = i;
				rearCol[0] = j+3;	rearCol[1] = j+4; rearCol[2] = j+5;
			
				if(map[i][j-1]==myColor) {
					return;
				}
				

				score=makeScore(frontRow, frontCol, rearRow, rearCol);


				int maxCase = findMax(score);

				make_stone3(maxCase, frontRow, frontCol, rearRow, rearCol);

				return;			
			}
		}
		if(way==1) {//row
			System.out.println("row!");
			blank=findBlank(i,j,1);
			System.out.println("blank : " + blank);
			if(blank!=0) {
				blankRow = i+blank;
				blankCol = j;
				stone3(blankRow, blankCol, 5.2);
				return;
			}

			if(blank==0) {//연속 3개
				System.out.println("1");
				frontRow[0] = i-1;	frontRow[1] = i-2; frontRow[2] = i-3;
				frontCol[0] = j;	frontCol[1] = j; frontCol[2] = j;

				rearRow[0] = i+3;	rearRow[1] = i+4; rearRow[2] = i+5;
				rearCol[0] = j;	rearCol[1] = j; rearCol[2] = j;
				
				if(map[i-1][j]==myColor) {
					return;
				}

				score=makeScore(frontRow, frontCol, rearRow, rearCol);


				int maxCase = findMax(score);

				make_stone3(maxCase, frontRow, frontCol, rearRow, rearCol);

				return;			
			}


		}

		if(way==2) {//dai1
			blank=findBlank(i,j,2);

			if(blank!=0) {
				blankRow = i-blank;
				blankCol = j+blank;
				stone3(blankRow, blankCol, 5.3);
				return;
			}

			if(blank==0) {//연속 3개
				System.out.println("3");
				frontRow[0] = i+1;	frontRow[1] = i+2; frontRow[2] = i+3;
				frontCol[0] = j-1;	frontCol[1] = j-2; frontCol[2] = j-3;

				rearRow[0] = i-3;	rearRow[1] = i-4; rearRow[2] = i-5;
				rearCol[0] = j+3;	rearCol[1] = j+4; rearCol[2] = j+5;
				
				if(map[i+1][j-1]==myColor) {
					return;
				}
				

				score=makeScore(frontRow, frontCol, rearRow, rearCol);


				int maxCase = findMax(score);

				make_stone3(maxCase, frontRow, frontCol, rearRow, rearCol);

				return;			
			}

		}

		if(way==3) {//dai1
			blank=findBlank(i,j,3);

			if(blank!=0) {
				blankRow = i-blank;
				blankCol = j-blank;
				stone3(blankRow, blankCol, 5.4);
				return;
			}

			if(blank==0) {//연속 3개
				System.out.println("4");
				frontRow[0] = i+1;	frontRow[1] = i+2; frontRow[2] = i+3;
				frontCol[0] = j+1;	frontCol[1] = j+2; frontCol[2] = j+3;

				rearRow[0] = i-3;	rearRow[1] = i-4; rearRow[2] = i-5;
				rearCol[0] = j-3;	rearCol[1] = j-4; rearCol[2] = j-5;
				
				if(map[i+1][j+1]==enemyColor) {
					return;
				}
				
				score=makeScore(frontRow, frontCol, rearRow, rearCol);


				int maxCase = findMax(score);
				
				make_stone3(maxCase, frontRow, frontCol, rearRow, rearCol);

				return;			
			}

		}
	}

	void make_stone3(int mcase , int[] fr, int[] fc, int[]rr, int[] rc) throws IOException {
		int r1,c1,r2=0,c2=0;

		if(mcase<3) {
			r1 = fr[0]; c1 = fc[0];
		}
		else if(mcase<5) {
			r1 = fr[1]; c1 = fc[1];
		}
		else {
			r1 = fr[2]; c1 = fc[2];
		}


		if(mcase ==0&& mcase ==3 && mcase == 5) {
			r2 = rr[0]; c2 = rc[0];
		}
		if(mcase==1 && mcase==4) {
			r2 = rr[1]; c2 = rc[1];
		}
		if(mcase==2) {
			r2 = rr[2]; c2 = rc[2];
		}

		stone3(r1,c1,5);
		stone3(r2,c2,5);
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
		System.out.println("blank : " + blank);
		return blank;
	}

	void stone3(int row, int col ,double score) throws IOException {
		if(checkMust(row,col,score)) {
			scoreMap[row][col]=scoreMust(scoreMap[row][col],score);
			writer.append("(" +row+ "," + col + ") aboutEne3 "+ score+"\n");
			return;
		}
	}



	int findMax(double[] score) {
		int index=0;
		double max=0;

		for(int i=0;i<6;i++) {
			if(max<score[i]) {
				max = score[i];
				index = i;
			}
		}
		return index;
	}

	double[] makeScore(int[] fr, int[] fc, int[] rr, int[] rc) {
		double[] s = new double[6];
		s[0]=scoreCase(fr[0],fc[0])+scoreCase(rr[0],rc[0]);
		s[1]=scoreCase(fr[0],fc[0])+scoreCase(rr[1],rc[1]);
		s[2]=scoreCase(fr[0],fc[0]) + scoreCase(rr[2],rc[2]);
		s[3]=scoreCase(fr[1],fc[1]) + scoreCase(rr[0],rc[0]);
		s[4]=scoreCase(fr[1],fc[1]) + scoreCase(rr[1],rc[1]);
		s[5]=scoreCase(fr[2],fc[2]) + scoreCase(rr[0],rc[0]);

		return s;
	}



}
