func minimumSumSubarray(nums []int, l int, r int) int {
	const inf int = 1 << 30
	ans := inf
	for i := range nums {
		s := 0
		for j := i; j < len(nums); j++ {
			s += nums[j]
			k := j - i + 1
			if k >= l && k <= r && s > 0 {
				ans = min(ans, s)
			}
		}
	}
	if ans == inf {
		return -1
	}
	return ans
}
