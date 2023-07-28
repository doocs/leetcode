package main

import (
	"fmt"
	"log"
	"time"
)

func CountingSort(nums []int, min, max int) {
	n := len(nums)
	k := max - min + 1
	c := make([]int, k)
	for _, v := range nums {
		c[v-min]++
	}
	log.Println(c)

	for i := 1; i < k; i++ {
		c[i] += c[i-1]
	}
	log.Println(c)

	r := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		a := c[v-min]
		r[a-1] = v
		c[v-min]--
		log.Println(r)
	}

	for i, v := range r {
		nums[i] = v
	}
	log.Println(nums)
}

func main() {
	// uncomment following line to enable log print
	// log.SetOutput(io.Discard)

	// test case 1
	nums := []int{1, 2, 7, 9, 5, 5, 8}
	CountingSort(nums, 1, 9)
	fmt.Println(nums)

	// wait complete output to terminal
	time.Sleep(time.Second)

	// test case 2
	nums = []int{3, 7, 9, 5, 5, 8, 11}
	CountingSort(nums, 3, 11)
	fmt.Println(nums)
}