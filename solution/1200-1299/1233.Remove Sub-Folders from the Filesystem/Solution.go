func removeSubfolders(folder []string) []string {
	sort.Strings(folder)
	ans := []string{folder[0]}
	for _, f := range folder[1:] {
		m, n := len(ans[len(ans)-1]), len(f)
		if m >= n || !(ans[len(ans)-1] == f[:m] && f[m] == '/') {
			ans = append(ans, f)
		}
	}
	return ans
}