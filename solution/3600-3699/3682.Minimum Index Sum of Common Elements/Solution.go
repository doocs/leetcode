func minimumSum(nums1 []int, nums2 []int) int {
	const inf = 1 << 30
	d := make(map[int]int)
	for i, x := range nums2 {
		if _, ok := d[x]; !ok {
			d[x] = i
		}
	}
	ans := inf
	for i, x := range nums1 {
		if j, ok := d[x]; ok {
            ans = min(ans, i + j)
		}
	}
	if ans == inf {
		return -1
	}
	return ans
}
