func characterReplacement(s string, k int) int {
	cnt := [26]int{}
	l, mx := 0, 0
	for r, c := range s {
		cnt[c-'A']++
		mx = max(mx, cnt[c-'A'])
		if r-l+1-mx > k {
			cnt[s[l]-'A']--
			l++
		}
	}
	return len(s) - l
}