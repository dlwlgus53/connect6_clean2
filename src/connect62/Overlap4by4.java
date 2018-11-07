package connect62;

import java.io.FileWriter;
import java.io.IOException;

public class Overlap4by4 extends CheckNScore{

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 


	Overlap4by4 (int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);
	
	}

	double[][] execute(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException {
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;
		
		findMine();
		return scoreMap;
	}
	
	void col() {
		
	}


	void findMine() throws IOException {

		
		int[] colUnit = new int[6];
		int[] rowUnit = new int[6];
		int[] dia1Unit = new int[6];
		int[] dia2Unit = new int[6];

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {

				colUnit=copyToColUnit(colUnit,i,j);//col���� �޾ƿ�
				rowUnit=copyToRowUnit(rowUnit,i,j);//row���� �޾ƿ�
				dia1Unit=copyToDia1Unit(dia1Unit,i,j);//dia1�������� �޾ƿ�
				dia2Unit=copyToDia2Unit(dia2Unit,i,j);//dia2�������� �޾ƿ�

				int colCount=0;
				int rowCount=0;
				int dia1Count=0;
				int dia2Count=0;

				boolean isEnemyCol=false;
				boolean isEnemyRow=false;
				boolean isEnemyDia1=false;
				boolean isEnemyDia2=false;

				isEnemyCol = enemyCheckInUnit(colUnit);
				isEnemyRow = enemyCheckInUnit(rowUnit);
				isEnemyDia1 = enemyCheckInUnit(dia1Unit);
				isEnemyDia2 = enemyCheckInUnit(dia2Unit);

				colCount=countUnit(colUnit);
				rowCount=countUnit(rowUnit);
				dia1Count=countUnit(dia1Unit);
				dia2Count=countUnit(dia2Unit);


				int tempi=i;
				int tempj=j;
				int targetNumber=0;
				boolean target[]= new boolean[4];

				if(isEnemyCol==false&&colCount==2) target[0]=true; else target[0]=false;
				if(isEnemyRow==false&&rowCount==2) target[1]=true; else target[1]=false;
				if(isEnemyDia1==false&&dia1Count==2) target[2]=true; else target[2]=false;
				if(isEnemyDia2==false&&dia2Count==2) target[3]=true; else target[3]=false;


				int temp;//variable for iterate
				
				
				for(temp=0;temp<4;temp++) {
					if(target[temp]==true) targetNumber++;
				}

				if(targetNumber>=2) {
					
					if(target[0]==true) {//column
						for(tempi=i,tempj=j;tempj<j+6;tempj++) {
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=1000;//
								writer.append("(" + tempi + "," + tempj + ") col4by4 " + 1000 +"\n");
							}
						}//this is for targetColumn
					}
					if(target[1]==true) {//row
						for(tempj=j,tempi=i;tempi<i+6;tempi++) {
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=1000;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") row4by4 " + 1000 +"\n");
							}
						}

					}
					if(target[2]==true) {//dia1
						for(tempj=j,tempi=i ;tempj<j+6;tempi--,tempj++) {
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=1000;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") dia14by4 " + 1000 +"\n");
							}
						}

					}
					if(target[3]==true) {//dai2
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=1000;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") dia24by4 " + 1000 +"\n");
							}
			
						}
					}
				}
			}
		}
	}



	boolean enemyCheckInUnit(int[]unit) {
		boolean result = false;
		int k=0;
		for(k=0;k<6;k++) {
			if(unit[k]==enemyColor)
				return true;
		}
		return result;
	}

	int countUnit(int[]unit) {
		int count=0;
		for(int k=0;k<6;k++) {
			if(unit[k]==enemyColor)	count++;
		}
		return count;
	}
	
	

}
