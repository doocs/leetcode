func simulationResult(windows []int, queries []int) (ans []int) {
	n := len(windows)
	s := make([]bool, n+1)
	for i := len(queries) - 1; i >= 0; i-- {
		q := queries[i]
		if !s[q] {
			s[q] = true
			ans = append(ans, q)
		}
	}
	for _, w := range windows {
		if !s[w] {
			ans = append(ans, w)
		}
	}
	return
}
