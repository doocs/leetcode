func maximumTotalCost(nums []int) int64 {
	n := len(nums)
	f := make([][2]int64, n+1)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 2; j++ {
			f[i][j] = int64(nums[i]) + f[i+1][1]
			if j == 1 {
				f[i][j] = max(f[i][j], int64(-nums[i])+f[i+1][0])
			}
		}
	}
	return f[0][0]
}
