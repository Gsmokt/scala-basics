package exercises

import "fmt"

func mainmainmain() {
	c := minus(2, 3, 4)

	for n := range receiver(c) {
		fmt.Println(n)
	}
}

func minus(arr ...int) chan int {
	c := make(chan int)

	go func() {
		for i := 0; i < len(arr); i++ {
			c <- arr[i]
		}
		close(c)
	}()

	return c
}

func receiver(in chan int) chan int {
	c := make(chan int)

	go func() {
		for v := range in {
			c <- v * v
		}
		close(c)
	}()

	return c
}
