func stoneGameIII(stoneValue []int) string {
	n := len(stoneValue)
	s := make([]int, n+1)
	for i, x := range stoneValue {
		s[i+1] = s[i] + x
	}
	const inf = 1 << 30
	f := make([]int, n)
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
		t := inf
		for j := 1; j <= 3; j++ {
			t = min(t, dfs(i+j))
		}
		f[i] = s[n] - s[i] - t
		return f[i]
	}
	a := dfs(0)
	b := s[n] - a
	if a == b {
		return "Tie"
	}
	if a > b {
		return "Alice"
	}
	return "Bob"
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}