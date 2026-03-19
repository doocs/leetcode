func minCost(s string, encCost int, flatCost int) int64 {
	n := len(s)
	pre := make([]int, n+1)

	for i := 1; i <= n; i++ {
		pre[i] = pre[i-1] + int(s[i-1]-'0')
	}

	var dfs func(int, int) int64
	dfs = func(l, r int) int64 {
		x := pre[r] - pre[l]

		var res int64
		if x != 0 {
			res = int64(r-l) * int64(x) * int64(encCost)
		} else {
			res = int64(flatCost)
		}

		if (r-l)%2 == 0 {
			m := (l + r) / 2
			res = min(res, dfs(l, m)+dfs(m, r))
		}

		return res
	}

	return dfs(0, n)
}
