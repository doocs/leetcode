func reversePairs(nums []int) int {
	n := len(nums)
	t := make([]int, n)
	var mergeSort func(l, r int) int
	mergeSort = func(l, r int) int {
		if l >= r {
			return 0
		}
		mid := (l + r) >> 1
		ans := mergeSort(l, mid) + mergeSort(mid+1, r)
		i, j, k := l, mid+1, 0
		for i <= mid && j <= r {
			if nums[i] <= nums[j] {
				t[k] = nums[i]
				k, i = k+1, i+1
			} else {
				ans += mid - i + 1
				t[k] = nums[j]
				k, j = k+1, j+1
			}
		}
		for ; i <= mid; i, k = i+1, k+1 {
			t[k] = nums[i]
		}
		for ; j <= r; j, k = j+1, k+1 {
			t[k] = nums[j]
		}
		for i = l; i <= r; i++ {
			nums[i] = t[i-l]
		}
		return ans
	}
	return mergeSort(0, n-1)
}