func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans := 1
	s := 0
	for i, j := 1, 0; i < len(nums); i++ {
		s += (nums[i] - nums[i-1]) * (i - j)
		for ; s > k; j++ {
			s -= nums[i] - nums[j]
		}
		ans = max(ans, i-j+1)
	}
	return ans
}