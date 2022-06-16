func maximumSubsequenceCount(text string, pattern string) int64 {
	ans := 0
	cnt := make([]int, 26)
	a, b := pattern[0], pattern[1]
	for i := range text {
		c := text[i]
		if c == b {
			ans += cnt[a-'a']
		}
		cnt[c-'a']++
	}
	ans += max(cnt[a-'a'], cnt[b-'a'])
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}