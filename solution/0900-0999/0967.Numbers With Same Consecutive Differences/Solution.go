func numsSameConsecDiff(n int, k int) []int {
	var ans []int
	var dfs func(n, k, t int)
	dfs = func(n, k, t int) {
		if n == 0 {
			ans = append(ans, t)
			return
		}
		last := t % 10
		if last+k <= 9 {
			dfs(n-1, k, t*10+last+k)
		}
		if last-k >= 0 && k != 0 {
			dfs(n-1, k, t*10+last-k)
		}
	}

	for i := 1; i < 10; i++ {
		dfs(n-1, k, i)
	}
	return ans
}