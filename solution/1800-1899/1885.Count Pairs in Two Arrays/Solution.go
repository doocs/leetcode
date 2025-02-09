func countPairs(nums1 []int, nums2 []int) (ans int64) {
	n := len(nums1)
	nums := make([]int, n)
	for i, x := range nums1 {
		nums[i] = x - nums2[i]
	}
	sort.Ints(nums)
	l, r := 0, n-1
	for l < r {
		for l < r && nums[l]+nums[r] <= 0 {
			l++
		}
		ans += int64(r - l)
		r--
	}
	return
}
