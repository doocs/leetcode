func minSubsequence(nums []int) []int {
	sort.Ints(nums)
	s, t := 0, 0
	for _, v := range nums {
		s += v
	}
	ans := []int{}
	for i := len(nums) - 1; i >= 0; i-- {
		t += nums[i]
		ans = append(ans, nums[i])
		if t > s-t {
			break
		}
	}
	return ans
}