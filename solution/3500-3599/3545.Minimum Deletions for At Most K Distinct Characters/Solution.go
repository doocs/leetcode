func minDeletion(s string, k int) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	for i := 0; i+k < len(cnt); i++ {
		ans += cnt[i]
	}
	return
}