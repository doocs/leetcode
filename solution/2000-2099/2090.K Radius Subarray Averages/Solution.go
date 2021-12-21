func getAverages(nums []int, k int) []int {
	n := len(nums)
	presum := make([]int64, n+1)
	for i, num := range nums {
		presum[i+1] = presum[i] + int64(num)
	}
	var ans []int
	for i := 0; i < n; i++ {
		if i-k < 0 || i+k >= n {
			ans = append(ans, -1)
		} else {
			ans = append(ans, int((presum[i+k+1]-presum[i-k])/int64(k*2+1)))
		}
	}
	return ans
}