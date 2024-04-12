package main

import (
	"bufio"
	"fmt"
	"os"
	"sync"
)

// example of fan in / out pattern

func main() {
	for n := range merge(read("first.txt"), read("second.txt")) {
		fmt.Println(n)
	}
}

func read(path string) <-chan string {
	f, err := os.Open(path)
	if err != nil {
		fmt.Println(err.Error())
	}

	out := make(chan string)

	fileScanner := bufio.NewScanner(f)
	fileScanner.Split(bufio.ScanLines)

	go func() {
		for fileScanner.Scan() {
			val := fileScanner.Text()
			out <- val
		}
		close(out)

		err = f.Close()
		if err != nil {
			fmt.Println(err.Error())
			return
		}
	}()
	return out

}

func merge(file1, file2 <-chan string) <-chan string {
	out := make(chan string)
	var wg sync.WaitGroup

	wg.Add(2)

	go func() {
		defer wg.Done()

		for v := range file1 {
			out <- v
		}
	}()

	go func() {
		defer wg.Done()

		for v := range file2 {
			out <- v
		}
	}()

	go func() {
		wg.Wait()
		close(out)
	}()
	return out
}

// func main() {
// 	c := fanIn(boring("Joe"), boring("Ann"))
// 	for i := 0; i < 10; i++ {
// 		fmt.Println(<-c)
// 	}
// 	fmt.Println("Done")
// }

// func boring(msg string) <-chan string {
// 	c := make(chan string)

// 	go func() {
// 		for i := 0; ; i++ {
// 			c <- fmt.Sprintf("%s %d", msg, i)
// 			time.Sleep(time.Duration(rand.Intn(1e3)) * time.Millisecond)
// 		}
// 	}()

// 	return c
// }

// func fanIn(input1, input2 <-chan string) <-chan string {
// 	c := make(chan string)

// 	go func() {
// 		for {
// 			c <- <-input1
// 		}
// 	}()

// 	go func() {
// 		for {
// 			c <- <-input2
// 		}
// 	}()

// 	return c
// }

//

// func main2() {
// 	in := genn(2, 3)
// 	c1 := sq(in)
// 	c2 := sq(in)

// 	for v := range merge(c1, c2) {
// 		fmt.Println(v)
// 	}
// }

// func genn(nums ...int) chan int {
// 	out := make(chan int)

// 	go func() {
// 		for _, v := range nums {
// 			out <- v
// 		}
// 		close(out)
// 	}()

// 	return out
// }

// func sq(in chan int) chan int {
// 	out := make(chan int)

// 	go func() {
// 		for v := range in {
// 			out <- v * v
// 		}
// 		close(out)
// 	}()

// 	return out
// }

// func merge(cs ...<-chan int) <-chan int {
// 	var wg sync.WaitGroup
// 	out := make(chan int)

// 	output := func(c <-chan int) {
// 		for v := range c {
// 			fmt.Println(v)
// 			out <- v
// 		}
// 		wg.Done()
// 	}

// 	wg.Add(len(cs))
// 	for _, c := range cs {
// 		go output(c)
// 	}

// 	go func() {
// 		wg.Wait()
// 		close(out)
// 	}()

// 	return out
// }
