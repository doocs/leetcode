func makeEqual(words []string) bool {
	cnt := [26]int{}
	for _, w := range words {
		for _, c := range w {
			cnt[c-'a']++
		}
	}
	n := len(words)
	for _, v := range cnt {
		if v%n != 0 {
			return false
		}
	}
	return true
}
