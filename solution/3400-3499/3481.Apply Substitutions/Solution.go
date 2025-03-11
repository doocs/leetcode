func applySubstitutions(replacements [][]string, text string) string {
	d := make(map[string]string)
	for _, e := range replacements {
		d[e[0]] = e[1]
	}
	var dfs func(string) string
	dfs = func(s string) string {
		i := strings.Index(s, "%")
		if i == -1 {
			return s
		}
		j := strings.Index(s[i+1:], "%")
		if j == -1 {
			return s
		}
		j += i + 1
		key := s[i+1 : j]
		replacement := dfs(d[key])
		return s[:i] + replacement + dfs(s[j+1:])
	}

	return dfs(text)
}
