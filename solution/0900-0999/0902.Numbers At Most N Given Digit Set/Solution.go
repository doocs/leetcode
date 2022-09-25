func atMostNGivenDigitSet(digits []string, n int) int {
	s := map[int]bool{}
	for _, d := range digits {
		i, _ := strconv.Atoi(d)
		s[i] = true
	}
	a := make([]int, 12)
	dp := make([][2]int, 12)
	for i := range a {
		dp[i] = [2]int{-1, -1}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n % 10
		n /= 10
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, lead int, limit bool) int {
		if pos <= 0 {
			return lead ^ 1
		}
		if !limit && lead == 0 && dp[pos][lead] != -1 {
			return dp[pos][lead]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if i == 0 && lead == 1 {
				ans += dfs(pos-1, lead, limit && i == up)
			} else if s[i] {
				ans += dfs(pos-1, 0, limit && i == up)
			}
		}
		if !limit {
			dp[pos][lead] = ans
		}
		return ans
	}
	return dfs(l, 1, true)
}