func distributeCookies(cookies []int, k int) int {
	cnt := make([]int, k)
	ans := 0x3f3f3f3f
	var dfs func(int)
	dfs = func(u int) {
		if u == len(cookies) {
			mx := cnt[0]
			for _, v := range cnt {
				mx = max(mx, v)
			}
			ans = min(ans, mx)
			return
		}
		for i := 0; i < k; i++ {
			if cnt[i]+cookies[u] < ans {
				cnt[i] += cookies[u]
				dfs(u + 1)
				cnt[i] -= cookies[u]
			}
		}
	}
	dfs(0)
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