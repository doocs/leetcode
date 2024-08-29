func minLargest(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	nxt := func(x, y int) int {
		if (x&1 ^ y) == 1 {
			return x + 1
		}
		return x + 2
	}
	for i := 1; i <= m; i++ {
		f[i][0] = nxt(f[i-1][0], nums1[i-1])
	}
	for j := 1; j <= n; j++ {
		f[0][j] = nxt(f[0][j-1], nums2[j-1])
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			x := nxt(f[i-1][j], nums1[i-1])
			y := nxt(f[i][j-1], nums2[j-1])
			f[i][j] = min(x, y)
		}
	}
	return f[m][n]
}
