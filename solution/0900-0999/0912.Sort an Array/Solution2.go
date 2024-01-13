func sortArray(nums []int) []int {
	mergeSort(nums, 0, len(nums)-1)
	return nums
}

func mergeSort(nums []int, l, r int) {
	if l >= r {
		return
	}
	mid := (l + r) >> 1
	mergeSort(nums, l, mid)
	mergeSort(nums, mid+1, r)
	i, j, k := l, mid+1, 0
	tmp := make([]int, r-l+1)
	for i <= mid && j <= r {
		if nums[i] <= nums[j] {
			tmp[k] = nums[i]
			i++
		} else {
			tmp[k] = nums[j]
			j++
		}
		k++
	}
	for ; i <= mid; i++ {
		tmp[k] = nums[i]
		k++
	}
	for ; j <= r; j++ {
		tmp[k] = nums[j]
		k++
	}
	for i = l; i <= r; i++ {
		nums[i] = tmp[i-l]
	}
}