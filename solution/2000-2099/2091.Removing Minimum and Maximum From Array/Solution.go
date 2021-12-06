func minimumDeletions(nums []int) int {
	mi, mx, n := 0, 0, len(nums)
	for i, num := range nums {
		if num < nums[mi] {
			mi = i
		}
		if num > nums[mx] {
			mx = i
		}
	}
	if mi > mx {
		mi, mx = mx, mi
	}
	return min(min(mx+1, n-mi), mi+1+n-mx)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}