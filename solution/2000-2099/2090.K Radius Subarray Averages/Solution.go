func getAverages(nums []int, k int) []int {
	ans := make([]int, len(nums))
	for i := range ans {
		ans[i] = -1
	}
	s := 0
	for i, x := range nums {
		s += x
		if i >= k*2 {
			ans[i-k] = s / (k*2 + 1)
			s -= nums[i-k*2]
		}
	}
	return ans
}
