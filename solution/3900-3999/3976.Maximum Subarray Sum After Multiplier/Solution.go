func maxSubarraySum(nums []int, k int) int64 {
	n := len(nums)
	inf := int64(math.MinInt64 / 4)

	f := make([][4]int64, n+1)
	for i := range f {
		for j := 0; j < 4; j++ {
			f[i][j] = inf
		}
	}

	f[0][0] = 0
	ans := inf

	for i := 1; i <= n; i++ {
		x := int64(nums[i-1])

		f[i][0] = max(f[i-1][0], 0) + x
		f[i][1] = max(max(f[i-1][0], f[i-1][1]), 0) + x*int64(k)
		f[i][2] = max(max(f[i-1][0], f[i-1][2]), 0) + x/int64(k)
		f[i][3] = max(max(f[i-1][1], f[i-1][2]), f[i-1][3]) + x

		ans = max(ans, max(max(f[i][0], f[i][1]), max(f[i][2], f[i][3])))
	}

	return ans
}
