func maxSubArray(nums []int) int {
	ans := nums[0]
	sum := 0
	for _, n := range nums {
		if sum > 0 {
			sum += n
		} else {
			sum = n
		}
		if sum > ans {
			ans = sum
		}
	}
	return ans
}
