func canSeePersonsCount(heights []int) []int {
	n := len(heights)
	ans := make([]int, n)
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && stk[len(stk)-1] < heights[i] {
			ans[i]++
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[i]++
		}
		stk = append(stk, heights[i])
	}
	return ans
}