func similarPairs(words []string) (ans int) {
	cnt := map[int]int{}
	for _, s := range words {
		x := 0
		for _, c := range s {
			x |= 1 << (c - 'a')
		}
		ans += cnt[x]
		cnt[x]++
	}
	return
}
