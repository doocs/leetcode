func numKLenSubstrNoRepeats(s string, k int) (ans int) {
	n := len(s)
	if k > n {
		return 0
	}
	cnt := map[byte]int{}
	for i := 0; i < k; i++ {
		cnt[s[i]]++
	}
	if len(cnt) == k {
		ans++
	}
	for i := k; i < n; i++ {
		cnt[s[i]]++
		cnt[s[i-k]]--
		if cnt[s[i-k]] == 0 {
			delete(cnt, s[i-k])
		}
		if len(cnt) == k {
			ans++
		}
	}
	return
}