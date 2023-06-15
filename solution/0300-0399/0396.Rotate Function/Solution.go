func maxRotateFunction(nums []int) int {
	f, s, n := 0, 0, len(nums)
	for i, v := range nums {
		f += i * v
		s += v
	}
	ans := f
	for i := 1; i < n; i++ {
		f = f + s - n*nums[n-i]
		if ans < f {
			ans = f
		}
	}
	return ans
}