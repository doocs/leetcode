func maxSubsequence(nums []int, k int) []int {
	idx := make([]int, len(nums))
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return nums[idx[i]] > nums[idx[j]] })
	sort.Ints(idx[:k])
	ans := make([]int, k)
	for i, j := range idx[:k] {
		ans[i] = nums[j]
	}
	return ans
}