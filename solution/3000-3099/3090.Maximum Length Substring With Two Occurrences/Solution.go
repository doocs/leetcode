func maximumLengthSubstring(s string) (ans int) {
	cnt := [26]int{}
	i := 0
	for j, c := range s {
		idx := c - 'a'
		cnt[idx]++
		for cnt[idx] > 2 {
			cnt[s[i]-'a']--
			i++
		}
		ans = max(ans, j-i+1)
	}
	return
}