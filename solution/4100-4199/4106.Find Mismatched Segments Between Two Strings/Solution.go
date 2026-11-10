func findMismatchedSegments(s1 string, s2 string) [][]string {
	ans := [][]string{}
	n := len(s1)
	i := 0
	for i < n {
		if s1[i] == s2[i] {
			i++
			continue
		}
		j := i + 1
		for j < n && s1[j] != s2[j] {
			j++
		}
		ans = append(ans, []string{s1[i:j], s2[i:j]})
		i = j
	}
	return ans
}
