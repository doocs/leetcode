func minimumCost(sentence string, k int) int {
	words := strings.Split(sentence, " ")
	n := len(words)
	inf := math.MaxInt32
	s := make([]int, n+1)
	for i, word := range words {
		s[i+1] = s[i] + len(word)
	}
	memo := make([]int, n)
	for i := range memo {
		memo[i] = inf
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if memo[i] != inf {
			return memo[i]
		}
		if s[n]-s[i]+n-i-1 <= k {
			memo[i] = 0
			return 0
		}
		ans := inf
		for j := i + 1; j < n; j++ {
			t := s[j] - s[i] + j - i - 1
			if t <= k {
				ans = min(ans, (k-t)*(k-t)+dfs(j))
			}
		}
		memo[i] = ans
		return ans
	}
	return dfs(0)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}