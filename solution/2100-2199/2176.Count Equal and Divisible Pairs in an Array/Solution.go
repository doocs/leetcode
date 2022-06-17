func countPairs(nums []int, k int) int {
	n := len(nums)
	ans := 0
	for i, v := range nums {
		for j := i + 1; j < n; j++ {
			if v == nums[j] && (i*j)%k == 0 {
				ans++
			}
		}
	}
	return ans
}