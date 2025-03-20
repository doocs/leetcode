func predictTheWinner(nums []int) bool {
	n := len(nums)
	f := make([][]int, n)
	for i, x := range nums {
		f[i] = make([]int, n)
		f[i][i] = x
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = max(nums[i]-f[i+1][j], nums[j]-f[i][j-1])
		}
	}
	return f[0][n-1] >= 0
}