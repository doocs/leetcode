func finalPrices(prices []int) []int {
	stk := []int{}
	n := len(prices)
	ans := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		ans[i] = prices[i]
		for len(stk) > 0 && prices[stk[len(stk)-1]] > prices[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[i] -= prices[stk[len(stk)-1]]
		}
		stk = append(stk, i)
	}
	return ans
}