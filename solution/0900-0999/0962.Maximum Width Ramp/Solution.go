func maxWidthRamp(nums []int) int {
	n := len(nums)
	stk := []int{}
	for i, v := range nums {
		if len(stk) == 0 || nums[stk[len(stk)-1]] > v {
			stk = append(stk, i)
		}
	}
	ans := 0
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= nums[i] {
			ans = max(ans, i-stk[len(stk)-1])
			stk = stk[:len(stk)-1]
		}
		if len(stk) == 0 {
			break
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}