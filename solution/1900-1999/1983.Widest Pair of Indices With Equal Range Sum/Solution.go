func widestPairOfIndices(nums1 []int, nums2 []int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i := range nums1 {
		s += nums1[i] - nums2[i]
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}