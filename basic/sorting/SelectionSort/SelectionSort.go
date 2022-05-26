package main

import "fmt"

func selectionSort(nums []int) {
	for i, n := 0, len(nums); i < n-1; i++ {
		minIndex := i
		for j := i + 1; j < n; j++ {
			if nums[j] < nums[minIndex] {
				minIndex = j
			}
		}
		nums[minIndex], nums[i] = nums[i], nums[minIndex]
	}
}

func main() {
	nums := []int{1, 2, 7, 9, 5, 8}
	selectionSort(nums)
	fmt.Println(nums)
}
