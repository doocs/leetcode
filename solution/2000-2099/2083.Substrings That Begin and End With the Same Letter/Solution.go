func numberOfSubstrings(s string) (ans int64) {
	cnt := [26]int{}
	for _, c := range s {
		c -= 'a'
		cnt[c]++
		ans += int64(cnt[c])
	}
	return ans
}