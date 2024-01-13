func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	ans := n + 1
	for i, x := range s {
		j := sort.SearchInts(s, x+target)
		if j <= n {
			ans = min(ans, j-i)
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}