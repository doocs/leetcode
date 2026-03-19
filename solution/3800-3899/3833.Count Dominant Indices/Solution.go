func dominantIndices(nums []int) int {
	n := len(nums)
	ans := 0
	suf := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i]*(n-i-1) > suf {
			ans++
		}
		suf += nums[i]
	}
	return ans
}
