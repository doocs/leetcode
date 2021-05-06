package main

import "fmt"

func bubbleSort(nums []int) {
	hasChange := true
	for i, n := 0, len(nums); i < n-1 && hasChange; i++ {
		hasChange = false
		for j := 0; j < n-i-1; j++ {
			if nums[j] > nums[j+1] {
				nums[j], nums[j+1] = nums[j+1], nums[j]
				hasChange = true
			}
		}
	}
}

func main() {
	nums := []int{1, 2, 7, 9, 5, 8}
	bubbleSort(nums)
	fmt.Println(nums)
}
