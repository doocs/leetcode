func permutation(S string) (ans []string) {
	s := []byte(S)
	sort.Slice(s, func(i, j int) bool { return s[i] < s[j] })
	t := slices.Clone(s)
	vis := make([]bool, len(s))
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(s) {
			ans = append(ans, string(t))
			return
		}
		for j := range s {
			if !vis[j] && (j == 0 || s[j] != s[j-1] || vis[j-1]) {
				vis[j] = true
				t[i] = s[j]
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}
