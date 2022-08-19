func validWordAbbreviation(word string, abbr string) bool {
	i, j := 0, 0
	m, n := len(word), len(abbr)
	for i < m {
		if j >= n {
			return false
		}
		if word[i] == abbr[j] {
			i++
			j++
			continue
		}
		k := j
		for k < n && abbr[k] >= '0' && abbr[k] <= '9' {
			k++
		}
		if k == j || abbr[j] == '0' {
			return false
		}
		x, _ := strconv.Atoi(abbr[j:k])
		if x == 0 {
			return false
		}
		i += x
		j = k
	}
	return i == m && j == n
}