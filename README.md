# Concurrency Testing code in Go and Java
- This project refers (Concurrency in Go and Java - Naohiro Togashi, 2014).

## Dongguk University
### Dept. of Computer Science and Engineering
#### Advanced System Programming

### class main
- main functions : Generates random number based matrix and allocates these data to threads

### class ParallelMatrix
- Calculating multiple of matrices simply(Extending Thread) 

##### Calculation Code - Java
```java
@Override
    public void run(){
        for(int i = indexNum; i < indexEnd; i ++)
            for(int j = 0; j < matrixC.length; j++)
                for(int k = 0; k < matrixC.length; k++)
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
    }
```

##### Calculation Code - Go
```go
func multiply(index int, size int, ch chan<- int){
	for i:=0; i<size/threadCount; i++{
		for j:=0; j<size/threadCount; j++{
			var sum int=0
			for k:=0; k<size; k++{
				sum = sum + MatrixA[index + i][k] * MatrixB[k][index + j]
			}
			MatrixC[index+i][j] = sum
		}
	}
	ch <- 0
}
```

This is [on GitHub](https://github.com/yjham2002/ConcurrencyTest_Java).

### Team
* EuiJin Ham (Leader)
* Dongmin Jeong
* Seho Chun
* SungSoo Park
