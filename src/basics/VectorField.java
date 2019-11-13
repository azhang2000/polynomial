package basics;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class VectorField{

    double[][] xVector;
    double[][] yVector;

    public VectorField(double[] xCoords, double[] yCoords, double[] scalars){
        
	for(int i =0;i<xCoords.length;i++) {
        	xCoords[i]=xCoords[i]*800/6+400;
        	yCoords[i]=yCoords[i]*800/6+400;
        }
	
    		this.xVector = generateXVector(xCoords, yCoords, scalars);
            this.yVector = generateYVector(xCoords, yCoords, scalars);
    }

    private double[][] generateXVector(double[] xCoords, double[] yCoords, double[] scalars){
            xVector = new double[800][800];
            for (int x = 0; x < xVector.length; x++) {
                    for (int y = 0; y < xVector[x].length; y++) {
                            for (int i = 0; i < xCoords.length; i++) {
                                    xVector[x][y] += scalars[i] * (1 / (Math.pow((x - xCoords[i]), 2) + Math.pow((y - yCoords[i]), 2))) * (Math.cos(Math.atan((yCoords[i] - y) / (xCoords[i] - x)))) * Math.abs(xCoords[i] - x) / (xCoords[i] - x);
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
                                    yVector[x][y] += scalars[i] * (1 / (Math.pow((x - xCoords[i]), 2) + Math.pow((y - yCoords[i]), 2))) * (Math.sin(Math.atan((yCoords[i] - y) / (xCoords[i] - x)))) * Math.abs(xCoords[i] - x) / (xCoords[i] - x);
                            }
                    }
            }
            return yVector;
    }

    public double getXVector(int x, int y){
    	if(x<0 || x>800) {
            x=0;
            }
        if(y<0 || y>800) {
            y=0;	
            }    
    	
    	return this.xVector[x][y];
    }

    public double getYVector(int x, int y){
            if(x<0 || x>800) {
            x=400;
            	
            }
            if(y<0 || y>800) {
            y=400;	
            	
            }
    		return this.yVector[x][y];
    }

}