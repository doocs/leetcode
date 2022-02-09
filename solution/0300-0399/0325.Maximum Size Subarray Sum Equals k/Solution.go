func maxSubArrayLen(nums []int, k int) int {
	mp := map[int]int{0: -1}
	s, ans := 0, 0
	for i, v := range nums {
		s += v
		if j, ok := mp[s-k]; ok {
			ans = max(ans, i-j)
		}
		if _, ok := mp[s]; !ok {
			mp[s] = i
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}