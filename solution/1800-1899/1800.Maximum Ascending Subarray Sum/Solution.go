func maxAscendingSum(nums []int) int {
	ans, t := 0, 0
	for i, v := range nums {
		if i == 0 || v > nums[i-1] {
			t += v
			if ans < t {
				ans = t
			}
		} else {
			t = v
		}
	}
	return ans
}