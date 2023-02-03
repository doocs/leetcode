func lengthOfLongestSubstring(s string) (ans int) {
	vis := map[byte]bool{}
	j := 0
	for i := range s {
		for vis[s[i]] {
			vis[s[j]] = false
			j++
		}
		vis[s[i]] = true
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