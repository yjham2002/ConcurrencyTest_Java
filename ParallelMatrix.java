public class ParallelMatrix extends Thread{

    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] matrixC;
    private int indexNum = 0;
    private int indexEnd = 0;

    public ParallelMatrix(int[][] matrixA, int[][] matrixB, int[][] matrixC, int indexNum, int indexEnd){
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.indexNum = indexNum;
        this.indexEnd = indexEnd;

    }

    @Override
    public void run(){
        for(int i = indexNum; i < indexEnd; i ++)
            for(int j = 0; j < matrixC.length; j++)
                for(int k = 0; k < matrixC.length; k++)
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
    }

}
