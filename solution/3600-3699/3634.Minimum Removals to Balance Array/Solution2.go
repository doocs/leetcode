func minRemoval(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	ans := n
	r := 0
	for l := 0; l < n; l++ {
		for r < n && nums[r] <= nums[l]*k {
			r++
		}
		ans = min(ans, n-(r-l))
	}
	return ans
}
