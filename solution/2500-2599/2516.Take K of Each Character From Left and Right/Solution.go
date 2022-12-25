func takeCharacters(s string, k int) int {
	cnt := [3]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	if cnt[0] < k || cnt[1] < k || cnt[2] < k {
		return -1
	}
	ans, j := 0, 0
	for i, c := range s {
		c -= 'a'
		cnt[c]--
		for cnt[c] < k {
			cnt[s[j]-'a']++
			j++
		}
		ans = max(ans, i-j+1)
	}
	return len(s) - ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}