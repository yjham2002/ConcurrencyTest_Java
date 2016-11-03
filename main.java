import java.util.Random;

public class main{
    private static int NumOfThreads;
    private static long start;
    private static long stop;
    private static long avrTime;
    private static ParallelMatrix[] threads;
    private static int SizeOfMatrix = 256;
    private static final int REPEAT_TIME = 10;
    private static int[][] MatrixA, MatrixB, MatrixC;

    public static void main(String[] args) throws InterruptedException{
        NumOfThreads = Runtime.getRuntime().availableProcessors(); // Retrieving Available Processors
        for(int i = 1; i <= 64; i++) testUnit(NumOfThreads * i);
    }

    public static void testUnit(int size) throws InterruptedException{
        avrTime = 0;
        SizeOfMatrix = size;

        MatrixA = new int[SizeOfMatrix][SizeOfMatrix];
        MatrixB = new int[SizeOfMatrix][SizeOfMatrix];
        MatrixC = new int[SizeOfMatrix][SizeOfMatrix];

        for(int repeat = 0; repeat < REPEAT_TIME; repeat++) {
            Random mRandom = new Random();

            for(int i = 0; i < SizeOfMatrix; i++)
                for(int j = 0; j < SizeOfMatrix; j++) {
                    MatrixA[i][j] = mRandom.nextInt(SizeOfMatrix);
                    MatrixB[i][j] = mRandom.nextInt(SizeOfMatrix);
                }

            threads = new ParallelMatrix[NumOfThreads]; // Construction for thread array

            for(int i = 0; i < NumOfThreads; i++) threads[i] = new ParallelMatrix(MatrixA ,MatrixB, MatrixC, i * (SizeOfMatrix / NumOfThreads), (i+1) * (SizeOfMatrix / NumOfThreads)); // Allocating Part of MatrixA

            start = System.nanoTime(); // Getting Start Time
            for (int i = 0; i < NumOfThreads; i++) threads[i].start(); // starting whole threads
            for (int i = 0; i < NumOfThreads; i++) threads[i].join(); // Waiting for end of whole threads
            stop = System.nanoTime(); // Getting End Time
            avrTime += stop - start;
        }

        System.out.println("S : " + SizeOfMatrix + " / T :" + (((double)avrTime / (double)REPEAT_TIME) / 1000000d) + " ms");
    }

}
