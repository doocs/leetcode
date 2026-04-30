func findValidElements(nums []int) []int {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]
	for i := n - 2; i >= 0; i-- {
		right[i] = max(right[i+1], nums[i])
	}
	left := 0
	ans := []int{}
	for i, x := range nums {
		if x > left || i == n-1 || x > right[i+1] {
			ans = append(ans, x)
		}
		left = max(left, x)
	}
	return ans
}
