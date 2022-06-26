func minimumScore(nums []int, edges [][]int) int {
	n := len(nums)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	s := 0
	for _, v := range nums {
		s ^= v
	}
	s1 := 0
	ans := math.MaxInt32
	var dfs func(int, int, int) int
	var dfs2 func(int, int, int) int
	dfs = func(i, fa, x int) int {
		res := nums[i]
		for _, j := range g[i] {
			if j != fa && j != x {
				res ^= dfs(j, i, x)
			}
		}
		return res
	}
	dfs2 = func(i, fa, x int) int {
		res := nums[i]
		for _, j := range g[i] {
			if j != fa && j != x {
				a := dfs2(j, i, x)
				res ^= a
				b := s1 ^ a
				c := s ^ s1
				t := max(max(a, b), c) - min(min(a, b), c)
				ans = min(ans, t)
			}
		}
		return res
	}
	for i := 0; i < n; i++ {
		for _, j := range g[i] {
			s1 = dfs(i, -1, j)
			dfs2(i, -1, j)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}