func countHighestScoreNodes(parents []int) int {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		p := parents[i]
		g[p] = append(g[p], i)
	}
	maxScore, ans := 0, 0
	var dfs func(int) int
	dfs = func(u int) int {
		size, score := 1, 1
		for _, v := range g[u] {
			t := dfs(v)
			size += t
			score *= t
		}
		if u > 0 {
			score *= n - size
		}
		if score > maxScore {
			maxScore, ans = score, 1
		} else if score == maxScore {
			ans++
		}
		return size
	}
	dfs(0)
	return ans
}