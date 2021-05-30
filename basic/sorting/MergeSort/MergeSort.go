package main

import "fmt"

func merge(nums, temp []int, low, mid, high int) {
	for i, j, k := low, mid+1, low; k <= high; k++ {
		if i > mid {
			temp[k] = nums[j]
			j++
		} else if j > high {
			temp[k] = nums[i]
			i++
		} else if nums[i] <= nums[j] {
			temp[k] = nums[i]
			i++
		} else {
			temp[k] = nums[j]
			j++
		}
	}
	for i := low; i <= high; i++ {
		nums[i] = temp[i]
	}
}

func _mergeSort(nums, temp []int, low, high int) {
	if low >= high {
		return
	}
	mid := low + (high-low)/2
	_mergeSort(nums, temp, low, mid)
	_mergeSort(nums, temp, mid+1, high)
	merge(nums, temp, low, mid, high)
}

func mergeSort(nums []int) {
	n := len(nums)
	temp := make([]int, n)
	_mergeSort(nums, temp, 0, n-1)
}

func main() {
	nums := []int{1, 2, 7, 4, 5, 3}
	mergeSort(nums)
	fmt.Println(nums)
}
