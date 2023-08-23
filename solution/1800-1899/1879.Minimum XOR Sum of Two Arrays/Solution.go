func minimumXORSum(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = 1 << 30
	}
	f[0] = 0
	for i := 0; i < 1<<n; i++ {
		k := bits.OnesCount(uint(i)) - 1
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				f[i] = min(f[i], f[i^1<<j]+(nums1[k]^nums2[j]))
			}
		}
	}
	return f[(1<<n)-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}