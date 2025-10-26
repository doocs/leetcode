func minOperations(nums1 []int, nums2 []int) int64 {
	var ans int64 = 1
	n := len(nums1)
	ok := false
	d := 1 << 30
	for i := 0; i < n; i++ {
		x := max(nums1[i], nums2[i])
		y := min(nums1[i], nums2[i])
		ans += int64(x - y)
		d = min(d, min(abs(x-nums2[n]), abs(y-nums2[n])))
		if nums2[n] >= y && nums2[n] <= x {
			ok = true
		}
	}
	if !ok {
		ans += int64(d)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
