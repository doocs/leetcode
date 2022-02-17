func diStringMatch(s string) []int {
	n := len(s)
	low, high := 0, n
	var ans []int
	for i := 0; i < n; i++ {
		if s[i] == 'I' {
			ans = append(ans, low)
			low++
		} else {
			ans = append(ans, high)
			high--
		}
	}
	ans = append(ans, low)
	return ans
}
