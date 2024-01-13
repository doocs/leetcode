func minimumXORSum(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 1<<n)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j < 1<<n; j++ {
			for k := 0; k < n; k++ {
				if j>>k&1 == 1 {
					f[i][j] = min(f[i][j], f[i-1][j^(1<<k)]+(nums1[i-1]^nums2[k]))
				}
			}
		}
	}
	return f[n][(1<<n)-1]
}