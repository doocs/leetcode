func largestMerge(word1 string, word2 string) string {
	m, n := len(word1), len(word2)
	i, j := 0, 0
	var ans strings.Builder
	for i < m && j < n {
		if word1[i:] > word2[j:] {
			ans.WriteByte(word1[i])
			i++
		} else {
			ans.WriteByte(word2[j])
			j++
		}
	}
	ans.WriteString(word1[i:])
	ans.WriteString(word2[j:])
	return ans.String()
}