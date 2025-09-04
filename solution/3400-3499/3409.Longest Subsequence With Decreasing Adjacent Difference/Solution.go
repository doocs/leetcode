func longestSubsequence(nums []int) int {
	mx, mn := nums[0], nums[0]
	for _, v := range nums {
		if v > mx {
			mx = v
		}
		if v < mn {
			mn = v
		}
	}
	diff := mx - mn
	dp := make([][]int, mx+1)
	for i := range dp {
		dp[i] = make([]int, diff+1)
	}

	ans := 0

	for _, n := range nums {
		maxnum := 1
		for d := diff; d >= 0; d-- {
			if n+d <= mx && dp[n+d][d]+1 > maxnum {
				maxnum = dp[n+d][d] + 1
			}
			if n-d >= 0 && dp[n-d][d]+1 > maxnum {
				maxnum = dp[n-d][d] + 1
			}
			dp[n][d] = maxnum
			if maxnum > ans {
				ans = maxnum
			}
		}
	}

	return ans
}
