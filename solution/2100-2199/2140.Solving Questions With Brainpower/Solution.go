func mostPoints(questions [][]int) int64 {
	n := len(questions)
	memo := make([]int, n)
	for i := range memo {
		memo[i] = -1
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if memo[i] != -1 {
			return memo[i]
		}
		ans := max(questions[i][0]+dfs(i+questions[i][1]+1), dfs(i+1))
		memo[i] = ans
		return ans
	}
	return int64(dfs(0))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}