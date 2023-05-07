func distinctDifferenceArray(nums []int) []int {
	n := len(nums)
	suf := make([]int, n+1)
	s := map[int]bool{}
	for i := n - 1; i >= 0; i-- {
		s[nums[i]] = true
		suf[i] = len(s)
	}
	ans := make([]int, n)
	s = map[int]bool{}
	for i, x := range nums {
		s[x] = true
		ans[i] = len(s) - suf[i+1]
	}
	return ans
}