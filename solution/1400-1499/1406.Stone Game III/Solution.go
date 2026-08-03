func stoneGameIII(stoneValue []int) string {
	n := len(stoneValue)
	f := make([]int, n)

	for i := range f {
		f[i] = -1 << 30
	}

	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}

		if f[i] != -1<<30 {
			return f[i]
		}

		ans := -1 << 30
		s := 0

		for j := i; j < i+3 && j < n; j++ {
			s += stoneValue[j]
			ans = max(ans, s-dfs(j+1))
		}

		f[i] = ans
		return ans
	}

	res := dfs(0)

	if res == 0 {
		return "Tie"
	}
	if res > 0 {
		return "Alice"
	}
	return "Bob"
}
