package basics;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class VectorField{

    double[][] xVector;
    double[][] yVector;

    public VectorField(double[] xCoords, double[] yCoords, double[] scalars){
        
	for(int i =0;i<xCoords.length;i++) {
        	xCoords[i]=xCoords[i]*800/6+400;
        	yCoords[i]=800-(yCoords[i]*800/6+400);
        }
	
    		this.xVector = generateXVector(xCoords, yCoords, scalars);
            this.yVector = generateYVector(xCoords, yCoords, scalars);
    }

    private double[][] generateXVector(double[] xCoords, double[] yCoords, double[] scalars){
            xVector = new double[800][800];
            for (int x = 0; x < xVector.length; x++) {
                    for (int y = 0; y < xVector[x].length; y++) {
                            for (int i = 0; i < xCoords.length; i++) {
                                if (xCoords[i]<x) {
                                	xVector[x][y] -= scalars[i] * (1 /(0.01+Math.sqrt(Math.pow(x - xCoords[i], 2) + Math.pow(y-yCoords[i], 2))));
                                }
                                else {
                                	xVector[x][y] += scalars[i] * (1 /(0.01+Math.sqrt(Math.pow(x - xCoords[i], 2) + Math.pow(y-yCoords[i], 2))));
                                }
                            	//xVector[x][y] += scalars[i] * (1 / (Math.pow((x - xCoords[i]), 2) + Math.pow((y - yCoords[i]), 2))) * (Math.cos(Math.atan((yCoords[i] - y) / (xCoords[i] - x)))) * Math.abs(xCoords[i] - x) / (xCoords[i] - x);
                                  
                            }
                    }
            }
            return xVector;
    }

    private double[][] generateYVector(double[] xCoords, double[] yCoords, double []scalars){
            yVector = new double[800][800];
            for (int x = 0; x < yVector.length; x++) {
                    for (int y = 0; y < yVector[x].length; y++) {
                            for (int i = 0; i < xCoords.length; i++) {
                            	if (yCoords[i]<y) {
                            		yVector[x][y] -= scalars[i] * (1 /(0.01+Math.sqrt(Math.pow(x - xCoords[i], 2) + Math.pow(y-yCoords[i], 2))));
                            	}
                            	else {
                            		yVector[x][y] += scalars[i] * (1 /(0.01+Math.sqrt(Math.pow(x - xCoords[i], 2) + Math.pow(y-yCoords[i], 2))));
                                }
                            	//yVector[x][y] += scalars[i] * (1 / (Math.pow((x - xCoords[i]), 2) + Math.pow((y - yCoords[i]), 2))) * (Math.sin(Math.atan((yCoords[i] - y) / (xCoords[i] - x)))) * Math.abs(xCoords[i] - x) / (xCoords[i] - x);
                                
                            }
                    }
            }
            return yVector;
    }

    public double getXVector(int x, int y){
    	if(x<0) {
            x=0;
            }
    	else if(x>789) {
    		x=789;
    	}
        if(y<0) {
            y=0;	
            } 
        else if(y>789) {
        	y=789;
        }
    	
    	return this.xVector[x][y];
    }

    public double getYVector(int x, int y){
    	if(x<0) {
            x=0;
            }
    	else if(x>789) {
    		x=789;
    	}
        if(y<0) {
            y=0;	
            } 
        else if(y>789) {
        	y=789;
        }
    		return this.yVector[x][y];
    }

}