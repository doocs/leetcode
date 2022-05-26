func getSumAbsoluteDifferences(nums []int) []int {
	n := len(nums)
	presum := make([]int, n+1)
	for i := 0; i < n; i++ {
		presum[i+1] = presum[i] + nums[i]
	}
	var res []int
	for i := 0; i < n; i++ {
		t := nums[i]*i - presum[i] + presum[n] - presum[i+1] - nums[i]*(n-i-1)
		res = append(res, t)
	}
	return res
}