func validSequence(word1 string, word2 string) []int {
	m, n := len(word1), len(word2)

	suf := make([]int, m+1)
	suf[m] = n

	j := n - 1
	for i := m - 1; i >= 0; i-- {
		if j >= 0 && word1[i] == word2[j] {
			j--
		}
		suf[i] = j + 1
	}

	ans := make([]int, 0, n)
	changed := false
	j = 0

	for i := 0; i < m; i++ {
		c := word1[i]
		if c == word2[j] || (!changed && suf[i+1] <= j+1) {
			if c != word2[j] {
				changed = true
			}
			ans = append(ans, i)
			j++

			if j == n {
				return ans
			}
		}
	}

	return []int{}
}
