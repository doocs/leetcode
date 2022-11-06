func maximumSubarraySum(nums []int, k int) int64 {
	n := len(nums)
	cnt := map[int]int{}
	s, ans := 0, 0
	for i := 0; i < k; i++ {
		cnt[nums[i]]++
		s += nums[i]
	}
	for i := k; i < n; i++ {
		if len(cnt) == k {
			ans = max(ans, s)
		}
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		s += nums[i]
		s -= nums[i-k]
	}
	if len(cnt) == k {
		ans = max(ans, s)
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}