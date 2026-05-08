func minOperations(nums1 []int, nums2 []int, k int) int64 {
	var a, b int64
	for i, x := range nums1 {
		y := nums2[i]
		if x == y {
			continue
		}
		if k == 0 || (x-y)%k != 0 {
			return -1
		}
		t := (x - y) / k
		if t < 0 {
			a += int64(-t)
		} else {
			b += int64(t)
		}
	}
	if a == b {
		return a
	}
	return -1
}
