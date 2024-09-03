func longestContinuousSubstring(s string) int {
	ans, cnt := 1, 1
	for i := range s[1:] {
		if s[i+1]-s[i] == 1 {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 1
		}
	}
	return ans
}
