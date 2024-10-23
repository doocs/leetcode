func numberOfSubstrings(s string, k int) (ans int64) {
	l := 0
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
		for cnt[c-'a'] >= k {
			cnt[s[l]-'a']--
			l++
		}
		ans += int64(l)
	}
	return
}
