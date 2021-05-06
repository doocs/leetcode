package main

import "fmt"

func partition(nums []int, low, high int) int {
	pivot := nums[low]
	for low < high {
		for low < high && nums[high] >= pivot {
			high--
		}
		nums[low] = nums[high]
		for low < high && nums[low] < pivot {
			low++
		}
		nums[high] = nums[low]
	}
	nums[low] = pivot
	return low
}

func _quickSort(nums []int, low, high int) {
	if low >= high {
		return
	}
	mid := partition(nums, low, high)
	_quickSort(nums, low, mid)
	_quickSort(nums, mid+1, high)
}

func quickSort(nums []int) {
	_quickSort(nums, 0, len(nums)-1)
}

func main() {
	nums := []int{1, 2, 7, 4, 5, 3}
	quickSort(nums)
	fmt.Println(nums)
}
