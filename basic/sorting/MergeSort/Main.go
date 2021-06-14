package main

import "fmt"

func mergeSort(nums []int, low, high int) {
	if low >= high {
		return
	}
	mid := (low + high) >> 1
	mergeSort(nums, low, mid)
	mergeSort(nums, mid+1, high)
	i, j := low, mid+1
	tmp := make([]int, 0)
	for i <= mid && j <= high {
		if nums[i] <= nums[j] {
			tmp = append(tmp, nums[i])
			i++
		} else {
			tmp = append(tmp, nums[j])
			j++
		}
	}
	for i <= mid {
		tmp = append(tmp, nums[i])
		i++
	}
	for j <= high {
		tmp = append(tmp, nums[j])
		j++
	}
	for i, j = low, 0; i <= high; i, j = i+1, j+1 {
		nums[i] = tmp[j]
	}
}

func main() {
	var n int
	fmt.Scanf("%d\n", &n)
	nums := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &nums[i])
	}

	mergeSort(nums, 0, n-1)

	for _, v := range nums {
		fmt.Printf("%d ", v)
	}
}