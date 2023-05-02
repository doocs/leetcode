func numOfWays(nums []int) int {
	n := len(nums)
	const mod = 1e9 + 7
	c := make([][]int, n)
	for i := range c {
		c[i] = make([]int, n)
	}
	c[0][0] = 1
	for i := 1; i < n; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
		}
	}
	var dfs func(nums []int) int
	dfs = func(nums []int) int {
		if len(nums) < 2 {
			return 1
		}
		var left, right []int
		for _, x := range nums[1:] {
			if x < nums[0] {
				left = append(left, x)
			} else {
				right = append(right, x)
			}
		}
		m, n := len(left), len(right)
		a, b := dfs(left), dfs(right)
		return c[m+n][m] * a % mod * b % mod
	}
	return (dfs(nums) - 1 + mod) % mod
}