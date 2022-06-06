func finalPrices(prices []int) []int {
	var stk []int
	n := len(prices)
	ans := make([]int, n)
	for i, v := range prices {
		ans[i] = v
		for len(stk) > 0 && prices[stk[len(stk)-1]] >= v {
			ans[stk[len(stk)-1]] -= v
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}