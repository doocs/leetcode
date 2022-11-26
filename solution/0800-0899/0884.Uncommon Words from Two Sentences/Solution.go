func uncommonFromSentences(s1 string, s2 string) (ans []string) {
	cnt := map[string]int{}
	for _, s := range strings.Split(s1, " ") {
		cnt[s]++
	}
	for _, s := range strings.Split(s2, " ") {
		cnt[s]++
	}
	for s, v := range cnt {
		if v == 1 {
			ans = append(ans, s)
		}
	}
	return
}