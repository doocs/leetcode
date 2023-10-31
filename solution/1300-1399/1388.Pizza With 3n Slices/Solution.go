func maxSizeSlices(slices []int) int {
	n := len(slices) / 3
	g := func(nums []int) int {
		m := len(nums)
		f := make([][]int, m+1)
		for i := range f {
			f[i] = make([]int, n+1)
		}
		for i := 1; i <= m; i++ {
			for j := 1; j <= n; j++ {
				f[i][j] = max(f[i-1][j], nums[i-1])
				if i >= 2 {
					f[i][j] = max(f[i][j], f[i-2][j-1]+nums[i-1])
				}
			}
		}
		return f[m][n]
	}
	a, b := g(slices[:len(slices)-1]), g(slices[1:])
	return max(a, b)
}