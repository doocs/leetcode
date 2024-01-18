func minimumXORSum(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = 1 << 30
	}
	f[0] = 0
	for _, x := range nums1 {
		for j := (1 << n) - 1; j >= 0; j-- {
			for k := 0; k < n; k++ {
				if j>>k&1 == 1 {
					f[j] = min(f[j], f[j^(1<<k)]+(x^nums2[k]))
				}
			}
		}
	}
	return f[(1<<n)-1]
}