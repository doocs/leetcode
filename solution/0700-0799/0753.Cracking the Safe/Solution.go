func crackSafe(n int, k int) string {
	mod := int(math.Pow(10, float64(n-1)))
	vis := map[int]bool{}
	ans := &strings.Builder{}
	var dfs func(int)
	dfs = func(u int) {
		for x := 0; x < k; x++ {
			e := u*10 + x
			if !vis[e] {
				vis[e] = true
				v := e % mod
				dfs(v)
				ans.WriteByte(byte('0' + x))
			}
		}
	}
	dfs(0)
	ans.WriteString(strings.Repeat("0", n-1))
	return ans.String()
}