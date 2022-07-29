func maximumBooks(books []int) int64 {
	n := len(books)
	nums := make([]int, n)
	left := make([]int, n)
	for i, v := range books {
		nums[i] = v - i
		left[i] = -1
	}
	stk := []int{}
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	dp := make([]int, n)
	dp[0] = books[0]
	ans := 0
	for i, v := range books {
		j := left[i]
		cnt := min(v, i-j)
		u := v - cnt + 1
		s := (u + v) * cnt / 2
		dp[i] = s
		if j != -1 {
			dp[i] += dp[j]
		}
		ans = max(ans, dp[i])
	}
	return int64(ans)
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