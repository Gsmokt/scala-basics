package exercises

import "fmt"

// factorial on each iteretion

func main() {
	for v := range factorialList(factoriall()) {
		fmt.Println(v)
	}
}

func factoriall() <-chan int {
	c := make(chan int)

	go func() {
		for i := 0; i < 10; i++ {
			for j := 3; j < 13; j++ {
				c <- j
			}
		}
		close(c)
	}()

	return c
}

func factorialList(c <-chan int) <-chan int {
	out := make(chan int)

	go func() {
		for v := range c {
			out <- fact(v)
		}
		close(out)
	}()
	return out
}

func fact(num int) int {
	var n int = 1
	for i := num; i > 0; i-- {
		n *= i
	}

	return n
}
