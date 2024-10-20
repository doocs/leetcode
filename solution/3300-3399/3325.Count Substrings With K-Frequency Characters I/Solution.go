func numberOfSubstrings(s string, k int) (ans int) {
	l := 0
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
		for cnt[c-'a'] >= k {
			cnt[s[l]-'a']--
			l++
		}
		ans += l
	}
	return
}
