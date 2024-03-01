func lengthOfLongestSubstring(s string) (ans int) {
	ss := [128]bool{}
	for i, j := 0, 0; j < len(s); j++ {
		for ss[s[j]] {
			ss[s[i]] = false
			i++
		}
		ss[s[j]] = true
		ans = max(ans, j-i+1)
	}
	return
}