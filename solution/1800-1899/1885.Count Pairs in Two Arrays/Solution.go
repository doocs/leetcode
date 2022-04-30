func countPairs(nums1 []int, nums2 []int) int64 {
	n := len(nums1)
	d := make([]int, n)
	for i, v := range nums1 {
		d[i] = v - nums2[i]
	}
	sort.Ints(d)
	var ans int64
	for i, v := range d {
		left, right := i+1, n
		for left < right {
			mid := (left + right) >> 1
			if d[mid] > -v {
				right = mid
			} else {
				left = mid + 1
			}
		}
		ans += int64(n - left)
	}
	return ans
}