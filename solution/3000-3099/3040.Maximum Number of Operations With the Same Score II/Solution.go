func maxOperations(nums []int) int {
	n := len(nums)
	var g func(i, j, s int) int
	g = func(i, j, s int) int {
		f := make([][]int, n)
		for i := range f {
			f[i] = make([]int, n)
			for j := range f {
				f[i][j] = -1
			}
		}
		var dfs func(i, j int) int
		dfs = func(i, j int) int {
			if j-i < 1 {
				return 0
			}
			if f[i][j] != -1 {
				return f[i][j]
			}
			ans := 0
			if nums[i]+nums[i+1] == s {
				ans = max(ans, 1+dfs(i+2, j))
			}

			if nums[i]+nums[j] == s {
				ans = max(ans, 1+dfs(i+1, j-1))
			}

			if nums[j-1]+nums[j] == s {
				ans = max(ans, 1+dfs(i, j-2))
			}
			f[i][j] = ans
			return ans
		}
		return dfs(i, j)
	}
	a := g(2, n-1, nums[0]+nums[1])
	b := g(0, n-3, nums[n-1]+nums[n-2])
	c := g(1, n-2, nums[0]+nums[n-1])
	return 1 + max(a, b, c)
}