func maxSubsequence(nums []int, k int) []int {
	idx := slices.Clone(make([]int, len(nums)))
	for i := range idx {
		idx[i] = i
	}
	slices.SortFunc(idx, func(i, j int) int { return nums[i] - nums[j] })
	slices.Sort(idx[len(idx)-k:])
	ans := make([]int, k)
	for i := range ans {
		ans[i] = nums[idx[len(idx)-k+i]]
	}
	return ans
}