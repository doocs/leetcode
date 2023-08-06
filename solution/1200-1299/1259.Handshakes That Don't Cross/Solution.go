func numberOfWays(numPeople int) int {
	const mod int = 1e9 + 7
	f := make([]int, numPeople+1)
	var dfs func(int) int
	dfs = func(i int) int {
		if i < 2 {
			return 1
		}
		if f[i] != 0 {
			return f[i]
		}
		for l := 0; l < i; l += 2 {
			r := i - l - 2
			f[i] = (f[i] + dfs(l)*dfs(r)) % mod
		}
		return f[i]
	}
	return dfs(numPeople)
}