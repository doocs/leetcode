func countSubgraphsForEachDiameter(n int, edges [][]int) []int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0]-1, e[1]-1
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	ans := make([]int, n-1)
	var msk, nxt, mx int
	var dfs func(int, int)
	dfs = func(u, d int) {
		msk ^= 1 << u
		if mx < d {
			mx, nxt = d, u
		}
		for _, v := range g[u] {
			if msk>>v&1 == 1 {
				dfs(v, d+1)
			}
		}
	}
	for mask := 1; mask < 1<<n; mask++ {
		if mask&(mask-1) == 0 {
			continue
		}
		msk, mx = mask, 0
		cur := bits.Len(uint(msk)) - 1
		dfs(cur, 0)
		if msk == 0 {
			msk, mx = mask, 0
			dfs(nxt, 0)
			ans[mx-1]++
		}
	}
	return ans
}