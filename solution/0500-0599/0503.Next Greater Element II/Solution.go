func nextGreaterElements(nums []int) []int {
	n := len(nums)
	res := make([]int, n)
	for i := range res {
		res[i] = -1
	}
	var stk []int
	for i := 0; i < (n << 1); i++ {
		for len(stk) > 0 && nums[stk[len(stk)-1]] < nums[i%n] {
			res[stk[len(stk)-1]] = nums[i%n]
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i%n)
	}
	return res
}