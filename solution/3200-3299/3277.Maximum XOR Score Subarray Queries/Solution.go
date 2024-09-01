func maximumSubarrayXor(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := 0; i < n; i++ {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		f[i][i] = nums[i]
		g[i][i] = nums[i]
		for j := i + 1; j < n; j++ {
			f[i][j] = f[i][j-1] ^ f[i+1][j]
			g[i][j] = max(f[i][j], max(g[i][j-1], g[i+1][j]))
		}
	}
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, g[l][r])
	}
	return
}
