func findScore(nums []int) (ans int64) {
	n := len(nums)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		i, j = idx[i], idx[j]
		return nums[i] < nums[j] || (nums[i] == nums[j] && i < j)
	})
	vis := make([]bool, n+2)
	for _, i := range idx {
		if !vis[i+1] {
			ans += int64(nums[i])
			vis[i], vis[i+2] = true, true
		}
	}
	return
}