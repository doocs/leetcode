func dailyTemperatures(temperatures []int) []int {
	n := len(temperatures)
	ans := make([]int, n)
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && temperatures[stk[len(stk)-1]] <= temperatures[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[i] = stk[len(stk)-1] - i
		}
		stk = append(stk, i)
	}
	return ans
}
