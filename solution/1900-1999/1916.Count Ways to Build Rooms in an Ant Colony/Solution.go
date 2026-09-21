func waysToBuildRooms(prevRoom []int) int {
	const mod = 1_000_000_007
	n := len(prevRoom)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[prevRoom[i]] = append(g[prevRoom[i]], i)
	}
	fact := make([]int, n+1)
	invFact := make([]int, n+1)
	fact[0] = 1
	for i := 1; i <= n; i++ {
		fact[i] = fact[i-1] * i % mod
	}
	qpow := func(a, n int) int {
		res := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				res = res * a % mod
			}
			a = a * a % mod
		}
		return res
	}
	invFact[n] = qpow(fact[n], mod-2)
	for i := n; i > 0; i-- {
		invFact[i-1] = invFact[i] * i % mod
	}
	comb := func(n, k int) int {
		return fact[n] * invFact[k] % mod * invFact[n-k] % mod
	}
	ans := 1
	var dfs func(int) int
	dfs = func(u int) int {
		merged := 0
		for _, v := range g[u] {
			cn := dfs(v)
			if merged != 0 {
				ans = ans * comb(merged+cn, cn) % mod
			}
			merged += cn
		}
		return merged + 1
	}
	dfs(0)
	return ans
}
