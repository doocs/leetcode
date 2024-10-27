func findSubtreeSizes(parent []int, s string) []int {
	n := len(s)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	d := [26][]int{}
	ans := make([]int, n)
	var dfs func(int, int)
	dfs = func(i, fa int) {
		ans[i] = 1
		idx := int(s[i] - 'a')
		d[idx] = append(d[idx], i)
		for _, j := range g[i] {
			dfs(j, i)
		}
		k := fa
		if len(d[idx]) > 1 {
			k = d[idx][len(d[idx])-2]
		}
		if k != -1 {
			ans[k] += ans[i]
		}
		d[idx] = d[idx][:len(d[idx])-1]
	}
	dfs(0, -1)
	return ans
}
