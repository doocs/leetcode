func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := 0
	ans := n + 1
	for i, j := 0, 0; i < n; i++ {
		s += nums[i]
		for s >= target {
			ans = min(ans, i-j+1)
			s -= nums[j]
			j++
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}