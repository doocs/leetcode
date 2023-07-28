func getKth(lo int, hi int, k int) int {
	f := func(x int) (ans int) {
		for ; x != 1; ans++ {
			if x%2 == 0 {
				x /= 2
			} else {
				x = 3*x + 1
			}
		}
		return
	}
	nums := make([]int, hi-lo+1)
	for i := range nums {
		nums[i] = lo + i
	}
	sort.Slice(nums, func(i, j int) bool {
		fx, fy := f(nums[i]), f(nums[j])
		if fx != fy {
			return fx < fy
		}
		return nums[i] < nums[j]
	})
	return nums[k-1]
}