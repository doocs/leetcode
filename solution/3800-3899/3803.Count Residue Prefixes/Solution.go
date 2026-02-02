func residuePrefixes(s string) int {
	st := make(map[rune]struct{})
	ans := 0
	for i, c := range s {
		idx := i + 1
		st[c] = struct{}{}
		if len(st) == idx%3 {
			ans++
		}
	}
	return ans
}
