func countSpecialSubsequences(nums []int) int {
	const mod = 1e9 + 7
	n := len(nums)
	f := make([][3]int, n)
	if nums[0] == 0 {
		f[0][0] = 1
	}
	for i := 1; i < n; i++ {
		if nums[i] == 0 {
			f[i][0] = (2*f[i-1][0] + 1) % mod
			f[i][1] = f[i-1][1]
			f[i][2] = f[i-1][2]
		} else if nums[i] == 1 {
			f[i][0] = f[i-1][0]
			f[i][1] = (f[i-1][0] + 2*f[i-1][1]) % mod
			f[i][2] = f[i-1][2]
		} else {
			f[i][0] = f[i-1][0]
			f[i][1] = f[i-1][1]
			f[i][2] = (f[i-1][1] + 2*f[i-1][2]) % mod
		}
	}
	return f[n-1][2]
}