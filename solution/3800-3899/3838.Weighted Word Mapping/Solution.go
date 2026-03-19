func mapWordWeights(words []string, weights []int) string {
	ans := make([]byte, 0, len(words))
	for _, w := range words {
		s := 0
		for i := 0; i < len(w); i++ {
			s = (s + weights[int(w[i]-'a')]) % 26
		}
		ans = append(ans, byte('a'+(25-s)))
	}
	return string(ans)
}
