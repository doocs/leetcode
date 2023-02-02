func permutation(s string) (ans []string) {
	cs := []byte(s)
	var dfs func(int)
	dfs = func(i int) {
		if i == len(s)-1 {
			ans = append(ans, string(cs))
			return
		}
		vis := map[byte]bool{}
		for j := i; j < len(s); j++ {
			if !vis[cs[j]] {
				vis[cs[j]] = true
				cs[i], cs[j] = cs[j], cs[i]
				dfs(i + 1)
				cs[i], cs[j] = cs[j], cs[i]
			}
		}
	}
	dfs(0)
	return
}