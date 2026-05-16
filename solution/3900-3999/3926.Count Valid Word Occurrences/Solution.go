func countWordOccurrences(chunks []string, queries []string) []int {
	s := strings.Join(chunks, "")
	n := len(s)
	cnt := make(map[string]int)
	i := 0
	for i < n {
		if s[i] == ' ' || s[i] == '-' {
			i++
			continue
		}
		j := i
		for j < n && s[j] != ' ' && (s[j] != '-' || (j+1 < n && s[j+1] != ' ' && s[j+1] != '-')) {
			j++
		}
		cnt[s[i:j]]++
		i = j
	}
	ans := make([]int, len(queries))
	for k, q := range queries {
		ans[k] = cnt[q]
	}
	return ans
}
