func numSpecialEquivGroups(words []string) int {
	s := map[string]bool{}
	for _, word := range words {
		a, b := []rune{}, []rune{}
		for i, c := range word {
			if i&1 == 1 {
				a = append(a, c)
			} else {
				b = append(b, c)
			}
		}
		sort.Slice(a, func(i, j int) bool {
			return a[i] < a[j]
		})
		sort.Slice(b, func(i, j int) bool {
			return b[i] < b[j]
		})
		s[string(a)+string(b)] = true
	}
	return len(s)
}