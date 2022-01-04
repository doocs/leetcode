func catMouseGame(graph [][]int) int {
	n := len(graph)
	memo := make([][][]int, n)
	for i := range memo {
		memo[i] = make([][]int, n)
		for j := range memo[i] {
			memo[i][j] = make([]int, 2*n+10)
			for k := range memo[i][j] {
				memo[i][j][k] = -1
			}
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if memo[i][j][k] != -1 {
			return memo[i][j][k]
		}
		if k >= 2*len(graph) {
			memo[i][j][k] = 0
		} else if i == 0 {
			memo[i][j][k] = 1
		} else if i == j {
			memo[i][j][k] = 2
		} else if k%2 == 1 {
			tie, win := false, false
			for _, next := range graph[j] {
				if next == 0 {
					continue
				}
				x := dfs(i, next, k+1)
				if x == 2 {
					win = true
					memo[i][j][k] = 2
					break
				}
				if x == 0 {
					tie = true
				}
			}
			if !win {
				if tie {
					memo[i][j][k] = 0
				} else {
					memo[i][j][k] = 1
				}
			}
		} else {
			tie, win := false, false
			for _, next := range graph[i] {
				x := dfs(next, j, k+1)
				if x == 1 {
					win = true
					memo[i][j][k] = 1
					break
				}
				if x == 0 {
					tie = true
				}
			}
			if !win {
				if tie {
					memo[i][j][k] = 0
				} else {
					memo[i][j][k] = 2
				}
			}
		}
		return memo[i][j][k]
	}
	return dfs(1, 2, 0)
}