func countSubTrees(n int, edges [][]int, labels string) []int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	cnt := [26]int{}
	var dfs func(int, int)
	dfs = func(i, fa int) {
		k := labels[i] - 'a'
		ans[i] -= cnt[k]
		cnt[k]++
		for _, j := range g[i] {
			if j != fa {
				dfs(j, i)
			}
		}
		ans[i] += cnt[k]
	}
	dfs(0, -1)
	return ans
}