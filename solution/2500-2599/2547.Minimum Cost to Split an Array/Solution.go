func minCost(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		ans, one := 1<<30, 0
		cnt := make([]int, n)
		for j := i; j < n; j++ {
			cnt[nums[j]]++
			x := cnt[nums[j]]
			if x == 1 {
				one++
			} else if x == 2 {
				one--
			}
			ans = min(ans, k+j-i+1-one+dfs(j+1))
		}
		f[i] = ans
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