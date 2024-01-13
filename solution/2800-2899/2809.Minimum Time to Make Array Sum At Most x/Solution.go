func minimumTime(nums1 []int, nums2 []int, x int) int {
	n := len(nums1)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	type pair struct{ a, b int }
	nums := make([]pair, n)
	var s1, s2 int
	for i := range nums {
		s1 += nums1[i]
		s2 += nums2[i]
		nums[i] = pair{nums1[i], nums2[i]}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i].b < nums[j].b })
	for i := 1; i <= n; i++ {
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if j > 0 {
				a, b := nums[i-1].a, nums[i-1].b
				f[i][j] = max(f[i][j], f[i-1][j-1]+a+b*j)
			}
		}
	}
	for j := 0; j <= n; j++ {
		if s1+s2*j-f[n][j] <= x {
			return j
		}
	}
	return -1
}