func hasIncreasingSubarrays(nums []int, k int) bool {
	mx, pre, cur := 0, 0, 0
	for i, x := range nums {
		cur++
		if i == len(nums)-1 || x >= nums[i+1] {
			mx = max(mx, max(cur/2, min(pre, cur)))
			pre, cur = cur, 0
		}
	}
	return mx >= k
}
