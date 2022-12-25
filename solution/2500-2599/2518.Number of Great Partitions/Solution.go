func countPartitions(nums []int, k int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s < k*2 {
		return 0
	}
	const mod int = 1e9 + 7
	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k)
	}
	f[0][0] = 1
	ans := 1
	for i := 1; i <= n; i++ {
		v := nums[i-1]
		ans = ans * 2 % mod
		for j := 0; j < k; j++ {
			f[i][j] = f[i-1][j]
			if j >= v {
				f[i][j] = (f[i][j] + f[i-1][j-v]) % mod
			}
		}
	}
	for j := 0; j < k; j++ {
		ans = (ans - f[n][j]*2%mod + mod) % mod
	}
	return ans
}