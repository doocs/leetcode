func nextGreaterElements(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	var stk []int
	for i := 0; i < (n << 1); i++ {
		for len(stk) > 0 && nums[stk[len(stk)-1]] < nums[i%n] {
			ans[stk[len(stk)-1]] = nums[i%n]
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i%n)
	}
	return ans
}