func sumOfPower(nums []int, k int) int {
	const mod int = 1e9 + 7
	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j] = (f[i-1][j] * 2) % mod
			if j >= nums[i-1] {
				f[i][j] = (f[i][j] + f[i-1][j-nums[i-1]]) % mod
			}
		}
	}
	return f[n][k]
}
