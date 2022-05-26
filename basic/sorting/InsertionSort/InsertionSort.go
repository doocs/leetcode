package main

import "fmt"

func insertionSort(nums []int) {
	for i, n := 1, len(nums); i < n; i++ {
		j, num := i-1, nums[i]
		for ; j >= 0 && nums[j] > num; j-- {
			nums[j+1] = nums[j]
		}
		nums[j+1] = num
	}
}

func main() {
	nums := []int{1, 2, 7, 9, 5, 8}
	insertionSort(nums)
	fmt.Println(nums)
}
