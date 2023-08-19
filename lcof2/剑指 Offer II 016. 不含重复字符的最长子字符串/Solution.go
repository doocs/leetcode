func lengthOfLongestSubstring(s string) (ans int) {
	ss := make([]bool, 128)
	j := 0
	for i, c := range s {
		for ss[c] {
			ss[s[j]] = false
			j++
		}
		ss[c] = true
		ans = max(ans, i-j+1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}