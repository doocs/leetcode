func vowelStrings(words []string, queries [][]int) []int {
	vowels := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	n := len(words)
	s := make([]int, n+1)
	for i, w := range words {
		x := 0
		if vowels[w[0]] && vowels[w[len(w)-1]] {
			x = 1
		}
		s[i+1] = s[i] + x
	}
	ans := make([]int, len(queries))
	for i, q := range queries {
		l, r := q[0], q[1]
		ans[i] = s[r+1] - s[l]
	}
	return ans
}