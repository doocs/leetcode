func maxScore(parent []int, op []int, score []int) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		if len(g[i]) == 0 {
			return score[i]
		}
		tot := score[i]
		mn := int(^uint(0) >> 1)
		for _, j := range g[i] {
			t := dfs(j)
			if t < mn {
				mn = t
			}
			tot += t
		}
		if op[i] == 0 {
			return tot - min(0, mn)
		}
		return tot - mn
	}
	return dfs(0)
}
