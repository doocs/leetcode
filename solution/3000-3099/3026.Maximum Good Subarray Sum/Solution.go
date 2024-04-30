func maximumSubarraySum(nums []int, k int) int64 {
	p := map[int]int64{nums[0]: 0}
	var s int64 = 0
	n := len(nums)
	var ans int64 = math.MinInt64
	for i, x := range nums {
		s += int64(x)
		if t, ok := p[nums[i]-k]; ok {
			ans = max(ans, s-t)
		}
		if t, ok := p[nums[i]+k]; ok {
			ans = max(ans, s-t)
		}
		if i+1 == n {
			break
		}
		if t, ok := p[nums[i+1]]; !ok || s < t {
			p[nums[i+1]] = s
		}
	}
	if ans == math.MinInt64 {
		return 0
	}
	return ans
}