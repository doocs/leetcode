func permutation(S string) (ans []string) {
	cs := []byte(S)
	sort.Slice(cs, func(i, j int) bool { return cs[i] < cs[j] })
	t := []byte{}
	n := len(cs)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, string(t))
			return
		}
		for j := 0; j < n; j++ {
			if vis[j] || (j > 0 && !vis[j-1] && cs[j] == cs[j-1]) {
				continue
			}
			vis[j] = true
			t = append(t, cs[j])
			dfs(i + 1)
			t = t[:len(t)-1]
			vis[j] = false
		}
	}
	dfs(0)
	return
}