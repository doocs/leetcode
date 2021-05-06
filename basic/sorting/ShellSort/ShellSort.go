package main

import "fmt"

func shellSort(nums []int) {
	n := len(nums)
	for gap := n / 2; gap > 0; gap /= 2 {
		for i := gap; i < n; i++ {
			j, num := i-gap, nums[i]
			for ; j >= 0 && nums[j] > num; j -= gap {
				nums[j+gap] = nums[j]
			}
			nums[j+gap] = num
		}
	}
}

func main() {
	nums := []int{1, 2, 7, 9, 5, 8}
	shellSort(nums)
	fmt.Println(nums)
}
