package first_mission;

import first_mission.SquareEquation.RootsSqEquation;

public class TestSquareEquation {
	final static double ACCURACY = 0.0001; 
	static public void main(String[] args) {
		final double[][] rations = {{  0,   0,   0}, {  0,   0,   12},
					                {  0, -21,   4}, {  0, 2.2,    0},
					                {4.1,   0,   0}, { -3,   0,    0},
					                {  8,  -4,   0}, {7.7,   9,    0},
					                { -1,   0,  16}, {  6,   0,    3},
					                {  1,   4,   3}, {  2,  -4,   -3},  // D > 0
					                {  1,  -4,   4}, {  2,  16,   32},  // D = 0
					                { 11,  -1, 3.2}, {1.9,   5, 66.6}}; // D < 0
		//I use Double.NaN if roots are not defined
		final double[][] answs = { {Double.NaN, Double.NaN},
					               {Double.NaN, Double.NaN},
					               {    0.1904,     0.1904},
					               {         0,          0},
					               {         0,          0},
					               {         0,          0},
					               {       0.5,          0},
					               {         0,    -1.1688},
					               {         4,         -4},
					               {Double.NaN, Double.NaN},
					               {        -3,         -1},
					               {    2.5811,    -0.5811},
					               {         2,          2},
					               {        -8,         -8},
					               {Double.NaN, Double.NaN},
					               {Double.NaN, Double.NaN}};
		
		final Roots[] nAnsws = {Roots.INFINITY, Roots.ZERO, Roots.ONE, Roots.ONE,  Roots.ONE, 
				                Roots.ONE,      Roots.TWO,  Roots.TWO, Roots.TWO,  Roots.ZERO, 
				                Roots.TWO,      Roots.TWO,  Roots.ONE, Roots.ONE,  Roots.ZERO, 
				                Roots.ZERO};		             
				             
				             
		for (int i = 0; i < rations.length; ++i) {
			SquareEquation  sqEq  = new SquareEquation(rations[i][0], rations[i][1], rations[i][2]);
			RootsSqEquation roots = new RootsSqEquation();
			
			roots = sqEq.getRoots();
			
			if (roots.nRoots == nAnsws[i] && (roots.nRoots == Roots.ZERO || roots.nRoots == Roots.INFINITY)) {
				System.out.printf("(%d) Correct\n", i);
				continue;
			}
			//Много копипаста
			if (roots.nRoots == nAnsws[i] && (roots.x1 < (answs[i][0] + ACCURACY) && roots.x1 > (answs[i][0] - ACCURACY) 
										  &&  roots.x2 < (answs[i][1] + ACCURACY) && roots.x2 > (answs[i][1] - ACCURACY) 
										  ||  roots.x2 < (answs[i][0] + ACCURACY) && roots.x2 > (answs[i][0] - ACCURACY) 
										  &&  roots.x1 < (answs[i][1] + ACCURACY) && roots.x1 > (answs[i][1] - ACCURACY))) {
				System.out.printf("(%d) Correct\n", i);
				continue;
			}
			System.out.printf("******************************\n");
			System.out.printf("(%d) Incorrect! |%fx^2 + %fx + %f = 0\nx1 = %f, answX1 = %f;\nx2 = %f, answX2 = %f;\n", 
					            i, rations[i][0], rations[i][1], rations[i][2], roots.x1, answs[i][0], roots.x2, answs[i][1]);
			System.out.printf("******************************\n");
			
		}
		
	}

}
