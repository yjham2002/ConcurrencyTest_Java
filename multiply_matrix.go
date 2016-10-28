package main

import (
	"fmt"
	"runtime"
	"time"
	"math/rand"
)
const maxSize int = 256
const threadCount int = 4
var MatrixA = [maxSize][maxSize]int{
}
var MatrixB = [maxSize][maxSize]int{
}
var MatrixC = [maxSize][maxSize]int{}
func main() {
	rand.Seed(time.Now().UTC().UnixNano())
	numCPUs:=runtime.NumCPU()
	runtime.GOMAXPROCS(threadCount)
	size := 4
	c := make(chan int)
	for i:=0; i<1; i++ {
		for j:=0; j<maxSize; j++ {
			MatrixA[i][j] = rand.Intn(100)
			MatrixB[i][j] = rand.Intn(100)
		}
	}
	for k:=0; k<64; k++ {
		start:=time.Now()
		for i:=0; i<numCPUs; i++ {
			go multiply(i*size/threadCount, size, c)
		}
		for i:=0; i<numCPUs; i++{
			<-c
		}
		stop:=time.Now()
		fmt.Printf("Size : %d / Time : ",size)
		fmt.Println(stop.Sub(start))
		size+=4
	}
}

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
