func findReplaceString(s string, indices []int, sources []string, targets []string) string {
	n := len(s)
	d := make([]int, n)
	for i, j := range indices {
		source := sources[i]
		if s[j:min(j+len(source), n)] == source {
			d[j] = i + 1
		}
	}
	ans := &strings.Builder{}
	for i := 0; i < n; {
		if d[i] > 0 {
			ans.WriteString(targets[d[i]-1])
			i += len(sources[d[i]-1])
		} else {
			ans.WriteByte(s[i])
			i++
		}
	}
	return ans.String()
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}