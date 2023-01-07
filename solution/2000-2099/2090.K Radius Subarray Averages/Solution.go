func getAverages(nums []int, k int) []int {
	ans := make([]int, len(nums))
	s := 0
	for i, v := range nums {
		ans[i] = -1
		s += v
		if i >= k*2 {
			ans[i-k] = s / (k*2 + 1)
			s -= nums[i-k*2]
		}
	}
	return ans
}