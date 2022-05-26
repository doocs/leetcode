func combinationSum3(k int, n int) [][]int {
	var ans [][]int
	var t []int
	var dfs func(i, n int, t []int)
	dfs = func(i, n int, t []int) {
		if i > 9 || n < 0 || len(t) > k {
			return
		}
		if n == 0 && len(t) == k {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		i++
		t = append(t, i)
		dfs(i, n-i, t)
		t = t[:len(t)-1]
		dfs(i, n, t)
	}

	dfs(0, n, t)
	return ans
}