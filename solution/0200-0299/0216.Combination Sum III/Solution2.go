func combinationSum3(k int, n int) (ans [][]int) {
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			if len(t) == k {
				ans = append(ans, slices.Clone(t))
			}
			return
		}
		if i > 9 || i > s || len(t) >= k {
			return
		}
		for j := i; j <= 9; j++ {
			t = append(t, j)
			dfs(j+1, s-j)
			t = t[:len(t)-1]
		}
	}
	dfs(1, n)
	return
}