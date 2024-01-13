func maxScore(s string) int {
	ans := 0
	for i, n := 1, len(s); i < n; i++ {
		t := 0
		for j := 0; j < i; j++ {
			if s[j] == '0' {
				t++
			}
		}
		for j := i; j < n; j++ {
			if s[j] == '1' {
				t++
			}
		}
		ans = max(ans, t)
	}
	return ans
}