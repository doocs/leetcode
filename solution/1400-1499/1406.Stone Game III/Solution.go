func stoneGameIII(stoneValue []int) string {
	n := len(stoneValue)
	f := make([]int, n)
	const inf = 1 << 30
	for i := range f {
		f[i] = inf
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != inf {
			return f[i]
		}
		ans, s := -(1 << 30), 0
		for j := 0; j < 3 && i+j < n; j++ {
			s += stoneValue[i+j]
			ans = max(ans, s-dfs(i+j+1))
		}
		f[i] = ans
		return ans
	}
	ans := dfs(0)
	if ans == 0 {
		return "Tie"
	}
	if ans > 0 {
		return "Alice"
	}
	return "Bob"
}