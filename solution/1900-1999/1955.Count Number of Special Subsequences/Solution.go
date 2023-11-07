func countSpecialSubsequences(nums []int) int {
	const mod = 1e9 + 7
	n := len(nums)
	f := [3]int{}
	if nums[0] == 0 {
		f[0] = 1
	}
	for i := 1; i < n; i++ {
		if nums[i] == 0 {
			f[0] = (2*f[0] + 1) % mod
		} else if nums[i] == 1 {
			f[1] = (f[0] + 2*f[1]) % mod
		} else {
			f[2] = (f[1] + 2*f[2]) % mod
		}
	}
	return f[2]
}