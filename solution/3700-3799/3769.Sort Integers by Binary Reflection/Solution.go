func sortByReflection(nums []int) []int {
	f := func(x int) int {
		y := 0
		for x != 0 {
			y = (y << 1) | (x & 1)
			x >>= 1
		}
		return y
	}

	sort.Slice(nums, func(i, j int) bool {
		fi := f(nums[i])
		fj := f(nums[j])
		if fi != fj {
			return fi < fj
		}
		return nums[i] < nums[j]
	})

	return nums
}
