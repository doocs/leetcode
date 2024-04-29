func permutation(S string) (ans []string) {
	t := []byte{}
	vis := make([]bool, len(S))
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(S) {
			ans = append(ans, string(t))
			return
		}
		for j := range S {
			if vis[j] {
				continue
			}
			vis[j] = true
			t = append(t, S[j])
			dfs(i + 1)
			t = t[:len(t)-1]
			vis[j] = false
		}
	}
	dfs(0)
	return
}