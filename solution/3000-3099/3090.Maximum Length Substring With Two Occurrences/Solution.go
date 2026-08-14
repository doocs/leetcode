func maximumLengthSubstring(s string) (ans int) {
	l := 0
	cnt := [26]int{}
	for r, c := range s {
		idx := int(c - 'a')
		cnt[idx]++
		for cnt[idx] > 2 {
			cnt[s[l]-'a']--
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
