func maxDifference(nums []int) int64 {
	n := len(nums)
	const INF int64 = 1 << 62
	memo := make([]int64, n)
	for i := range memo {
		memo[i] = -INF
	}
	var dfs func(i int) int64
	dfs = func(i int) int64 {
		if i >= n {
			return 0
		}
		if memo[i] != -INF {
			return memo[i]
		}
		x := nums[i]
		var res int64
		if x%3 == 0 {
			res = int64(x) - dfs(i+1)
		} else {
			res = int64(x) + dfs(i+1)
		}
		if i+1 < n {
			s := int64(x + nums[i+1])
			var t int64
			if s%3 == 0 {
				t = s - dfs(i+2)
			} else {
				t = s + dfs(i+2)
			}
			res = max(res, t)
		}
		memo[i] = res
		return res
	}
	return dfs(0)
}
