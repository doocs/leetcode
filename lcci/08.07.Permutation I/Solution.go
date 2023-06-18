func permutation(S string) []string {
	vis := make(map[byte]bool)
	var ans []string
	var t []byte
	var dfs func(u int, t []byte)
	dfs = func(u int, t []byte) {
		if u == len(S) {
			ans = append(ans, string(t))
			return
		}
		for i := range S {
			if vis[S[i]] {
				continue
			}
			vis[S[i]] = true
			t = append(t, S[i])
			dfs(u+1, t)
			vis[S[i]] = false
			t = t[:len(t)-1]
		}
	}
	dfs(0, t)
	return ans
}