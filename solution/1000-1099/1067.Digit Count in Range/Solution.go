func digitsCount(d int, low int, high int) int {
	f := func(n int) int {
		a := make([]int, 11)
		dp := make([][]int, 11)
		for i := range dp {
			dp[i] = make([]int, 11)
			for j := range dp[i] {
				dp[i][j] = -1
			}
		}
		l := 0
		for n > 0 {
			l++
			a[l] = n % 10
			n /= 10
		}

		var dfs func(int, int, bool, bool) int
		dfs = func(pos, cnt int, lead, limit bool) int {
			if pos <= 0 {
				return cnt
			}
			if !lead && !limit && dp[pos][cnt] != -1 {
				return dp[pos][cnt]
			}
			up := 9
			if limit {
				up = a[pos]
			}
			ans := 0
			for i := 0; i <= up; i++ {
				if i == 0 && lead {
					ans += dfs(pos-1, cnt, lead, limit && i == up)
				} else {
					t := cnt
					if d == i {
						t++
					}
					ans += dfs(pos-1, t, false, limit && i == up)
				}
			}
			if !lead && !limit {
				dp[pos][cnt] = ans
			}
			return ans
		}

		return dfs(l, 0, true, true)
	}
	return f(high) - f(low-1)
}