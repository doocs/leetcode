func minimumPartition(s string, k int) int {
	n := len(s)
	f := make([]int, n)
	const inf int = 1 << 30
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		res, v := inf, 0
		for j := i; j < n; j++ {
			v = v*10 + int(s[j]-'0')
			if v > k {
				break
			}
			res = min(res, dfs(j+1))
		}
		f[i] = res + 1
		return f[i]
	}
	ans := dfs(0)
	if ans < inf {
		return ans
	}
	return -1
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}