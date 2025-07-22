func permutation(S string) (ans []string) {
	t := []byte(S)
	n := len(t)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, string(t))
			return
		}
		for j := range S {
			if !vis[j] {
				vis[j] = true
				t[i] = S[j]
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}
