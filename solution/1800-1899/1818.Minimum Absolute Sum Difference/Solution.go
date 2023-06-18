func minAbsoluteSumDiff(nums1 []int, nums2 []int) int {
	n := len(nums1)
	nums := make([]int, n)
	copy(nums, nums1)
	sort.Ints(nums)
	s, mx := 0, 0
	const mod int = 1e9 + 7
	for i, a := range nums1 {
		b := nums2[i]
		s = (s + abs(a-b)) % mod
	}
	for i, a := range nums1 {
		b := nums2[i]
		d1, d2 := abs(a-b), 1<<30
		j := sort.Search(n, func(k int) bool { return nums[k] >= b })
		if j < n {
			d2 = min(d2, abs(nums[j]-b))
		}
		if j > 0 {
			d2 = min(d2, abs(nums[j-1]-b))
		}
		mx = max(mx, d1-d2)
	}
	return (s - mx + mod) % mod
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}