func longestContinuousSubstring(s string) int {
	ans := 0
	i, j := 0, 1
	for ; j < len(s); j++ {
		ans = max(ans, j-i)
		if s[j]-s[j-1] != 1 {
			i = j
		}
	}
	ans = max(ans, j-i)
	return ans
}