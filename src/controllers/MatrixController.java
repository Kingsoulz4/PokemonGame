/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 *
 * @author DatHoang
 */
public class MatrixController 
{
    int column;
    int row;
    static int mat[][];
    int numOfIcon;
    static Line pointLine;
    static Vector<Point> listPoint;
    public MatrixController()
    {
        
    }
    public MatrixController(int column, int row, int[][] mat, int numOfIcon) {
		super();
		this.column = column;
		this.row = row;
		this.mat = mat;
		this.numOfIcon = numOfIcon;
	}
    //TODO show matrix to console
	public void showMatrix()
    {
        for (int i = 0; i <row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.printf("%3d",mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public boolean CheckPoint(int col, int ro, Vector<Point> listPoint)
    {
        boolean flag = true;
        for (Point point : listPoint) {
               if (col == point.x && ro == point.y) {
                   flag = false;
               }
           }
        return flag;
    }
    public void establishMatrix()
    {
       // TODO provide value to matrix
       Random rand = new Random();
       listPoint = new Vector<Point>();
       int maxRepeat = (column*row)/numOfIcon +2 ;
       //JOptionPane.showMessageDialog(null, " "+row+"  "+column);
       int[] arrCheckRepeat = new int[numOfIcon+1];
       int i=0;
       while(i<((row-2)*(column-2)))
       {
           int indx = rand.nextInt(numOfIcon)+1;
           //Check repeat
           if (arrCheckRepeat[indx]<maxRepeat) {
           int j=0;
           do {             
               
               int col = rand.nextInt(column-2)+1;
               int ro = rand.nextInt(row-2)+1;
               boolean flag=CheckPoint(col, ro, listPoint);
               if (flag==true) 
               {
                  arrCheckRepeat[indx]+=1;
                  mat[ro][col] = indx;
                  listPoint.add(new Point(col,ro));
                  j++;
                  i++;
               }
               
           } while (j<2);
          }
       }
       for (int j = 0; j < row; j++) {
		for (int k = 0; k <column ; k++) {
			if (j==0||k==0||j==row-1||k==column-1) {
				mat[j][k]=0;
			}
		}
	}
    }
    public void setNumOfIcon(int numOfIcon)
    {
        this.numOfIcon = numOfIcon;
    }
    public int getNumOfIcon()
    {
        return this.numOfIcon;
    }
    public void setColumn(int column)
    {
        this.column = column;
    }
    public int getColumn()
    {
        return this.column;
    }
    public void setRow(int row)
    {
        this.row = row;
    }
    public int getRow()
    {
        return this.row;
    }
    public void setMat(int mat[][])
    {
        this.mat = mat;
    }
    public int[][] getMat()
    {
        return this.mat;
    }
    private boolean checkLineX(int y1, int y2, int x, Point p1, Point p2)
    {
    	int maxY = Math.max(y1,y2);
    	int minY = Math.min(y1, y2);
    	System.out.print("Check LineX:");
    	for (int i = minY; i <= maxY; i++) {
    		System.out.print("("+x+","+i+")"+"-->");
			if (mat[x][i]!=0&&(x!=p1.x||i!=p1.y)&&(x!=p2.x||i!=p2.y)) {
				return false;
			}
		}
    	System.out.println();
    	return true;
    	
    }
    private boolean checkLineY(int x1, int x2, int y, Point p1, Point p2 )
    {
    	int maxX = Math.max(x1,x2);
    	int minX = Math.min(x1, x2);
    	System.out.print("Check line Y");
    	for (int i =minX; i <= maxX; i++) {
    		System.out.print("("+i+","+y+")"+"-->");
			if (mat[i][y]!=0&&(i!=p1.x||y!=p1.y)&&(i!=p2.x||y!=p2.y)) {
				return false;
			}
		}
    	System.out.println();
    	return true;
    	
    }
    private boolean CheckRectX(Point p1, Point p2)
    {
    	for (int i = p1.x; i < row; i++) {
			if (checkLineY(p1.x, i, p1.y, p1, p2)&&checkLineX(p1.y, p2.y, i, p1, p2)&&checkLineY(p2.x, i, p2.y, p1, p2)) {
				//JOptionPane.showMessageDialog(null, "cuc cu"+p1.x+"   "+p1.y+"   "+p2.x+"   "+p2.y);
				return true;
			}
		}
    	for (int i = p1.x; i >= 0; i--) {
			if (checkLineY(p1.x, i, p1.y, p1, p2)&&checkLineX(p1.y, p2.y, i, p1, p2)&&checkLineY(p2.x, i, p2.y, p1, p2)) {
				//JOptionPane.showMessageDialog(null, "he lu");
				return true;
			}
		}
    	for(int i= p1.y; i<column;i++)
        {
    		if (checkLineX(p1.y, i, p1.x, p1, p2)&&checkLineY(p1.x, p2.x, i, p1, p2)&&checkLineX(i, p2.y, p2.x, p1, p2)) {
				return true;
			}
        }
    	for (int i = p1.y; i >=0 ; i--) {
    		if (checkLineX(p1.y, i, p1.x, p1, p2)&&checkLineY(p1.x, p2.x, i, p1, p2)&&checkLineX(i, p2.y, p2.x, p1, p2)) {
				return true;
			}
		}
       
    	return false;
    }
    
    
    public  Line checkPointLine(Point p1, Point p2)
    {
    	
    	if ((p1.x!= p2.x|| p1.y!=p2.y)&& mat[p1.x][p1.y]==mat[p2.x][p2.y]) {
    		
			if (CheckRectX(p1, p2)==true) {
				System.out.println("Similar:"+p1.x+"  "+ p1.y+"   "+p2.x+"    "+p2.y+"   "+mat[p1.x][p1.y]+ "    "+ mat[p2.x][p2.y]);
				return new Line(p1,p2);
			}
		}
    	System.out.println("Different:"+p1.x+"  "+ p1.y+"   "+p2.x+"    "+p2.y+"   "+mat[p1.x][p1.y]+ "    "+ mat[p2.x][p2.y]);
    	return null;
    }
}
