func countKConstraintSubstrings(s string, k int) (ans int) {
	cnt := [2]int{}
	l := 0
	for r, c := range s {
		cnt[c-'0']++
		for ; cnt[0] > k && cnt[1] > k; l++ {
			cnt[s[l]-'0']--
		}
		ans += r - l + 1
	}
	return
}
