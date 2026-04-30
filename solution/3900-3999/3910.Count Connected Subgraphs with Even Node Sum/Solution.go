func evenSumSubgraphs(nums []int, edges [][]int) int {
	n := len(nums)
	g := make([][]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
		g[e[1]] = append(g[e[1]], e[0])
	}
	m := (1 << n) - 1
	ans := 0
	var vis int

	var dfs func(int)
	dfs = func(u int) {
		vis |= 1 << u
		for _, v := range g[u] {
			if (vis >> v & 1) == 0 {
				dfs(v)
			}
		}
	}

	for sub := 1; sub <= m; sub++ {
		s := 0
		for i := 0; i < n; i++ {
			if sub>>i&1 == 1 {
				s += nums[i]
			}
		}
		if s%2 != 0 {
			continue
		}
		vis = m ^ sub
		dfs(bits.Len(uint(sub)) - 1)
		if vis == m {
			ans++
		}
	}
	return ans
}
