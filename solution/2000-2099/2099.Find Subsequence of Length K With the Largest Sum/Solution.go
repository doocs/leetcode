func maxSubsequence(nums []int, k int) []int {
	n := len(nums)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return nums[idx[i]] < nums[idx[j]] })
	sort.Ints(idx[n-k:])
	ans := make([]int, k)
	for i := n - k; i < n; i++ {
		ans[i-(n-k)] = nums[idx[i]]
	}
	return ans
}