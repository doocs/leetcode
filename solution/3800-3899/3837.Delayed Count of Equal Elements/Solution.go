func delayedCount(nums []int, k int) []int {
	n := len(nums)
	cnt := map[int]int{}
	ans := make([]int, n)
	for i := n - k - 2; i >= 0; i-- {
		cnt[nums[i+k+1]]++
		ans[i] = cnt[nums[i]]
	}
	return ans
}
