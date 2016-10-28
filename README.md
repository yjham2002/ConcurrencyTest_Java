# Concurrency Test in Java
- This project refers (Concurrency in Go and Java - Naohiro Togashi, 2014).

## Dongguk University
### Dept. of Computer Science and Engineering
#### Advanced System Programming

### class main
- main functions : Generates random number based matrix and allocates these data to threads

### class ParallelMatrix
- Calculating multiple of matrices simply(Extending Thread) 

##### Calculation Code
```java
@Override
    public void run(){
        for(int i = indexNum; i < indexEnd; i ++)
            for(int j = 0; j < matrixC.length; j++)
                for(int k = 0; k < matrixC.length; k++)
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
    }
```

This is [on GitHub](https://github.com/yjham2002/ConcurrencyTest_Java).

### Team
* EuiJin Ham (Leader)
* Dongmin Jeong
* Seho Chun
* SungSoo Park
