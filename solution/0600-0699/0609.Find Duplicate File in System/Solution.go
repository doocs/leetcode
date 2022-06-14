func findDuplicate(paths []string) [][]string {
	m := make(map[string][]string)
	for _, path := range paths {
		a := strings.Split(path, " ")
		for i := 1; i < len(a); i++ {
			j := strings.Index(a[i], "(")
			content := a[i][j+1 : len(a[i])-1]
			name := a[0] + "/" + a[i][:j]
			m[content] = append(m[content], name)
		}
	}

	var ans [][]string
	for _, names := range m {
		if len(names) > 1 {
			ans = append(ans, names)
		}
	}
	return ans
}
