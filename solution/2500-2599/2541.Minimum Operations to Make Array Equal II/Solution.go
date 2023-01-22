func minOperations(nums1 []int, nums2 []int, k int) int64 {
	ans, x := 0, 0
	for i, a := range nums1 {
		b := nums2[i]
		if k == 0 {
			if a != b {
				return -1
			}
			continue
		}
		if (a-b)%k != 0 {
			return -1
		}
		y := (a - b) / k
		ans += abs(y)
		x += y
	}
	if x != 0 {
		return -1
	}
	return int64(ans / 2)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}