func areOccurrencesEqual(s string) bool {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	ss := map[int]bool{}
	for _, v := range cnt {
		if v == 0 {
			continue
		}
		ss[v] = true
	}
	return len(ss) == 1
}