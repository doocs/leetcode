func similarPairs(words []string) (ans int) {
	cnt := map[int]int{}
	for _, w := range words {
		v := 0
		for _, c := range w {
			v |= 1 << (c - 'a')
		}
		ans += cnt[v]
		cnt[v]++
	}
	return
}