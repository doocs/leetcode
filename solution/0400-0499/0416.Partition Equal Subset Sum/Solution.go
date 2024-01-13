func canPartition(nums []int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%2 == 1 {
		return false
	}
	n, m := len(nums), s>>1
	f := make([][]bool, n+1)
	for i := range f {
		f[i] = make([]bool, m+1)
	}
	f[0][0] = true
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j <= m; j++ {
			f[i][j] = f[i-1][j] || (j >= x && f[i-1][j-x])
		}
	}
	return f[n][m]
}