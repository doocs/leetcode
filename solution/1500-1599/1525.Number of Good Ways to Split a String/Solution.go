func numSplits(s string) (ans int) {
	cnt := map[rune]int{}
	for _, c := range s {
		cnt[c]++
	}
	vis := map[rune]bool{}
	for _, c := range s {
		vis[c] = true
		cnt[c]--
		if cnt[c] == 0 {
			delete(cnt, c)
		}
		if len(vis) == len(cnt) {
			ans++
		}
	}
	return
}