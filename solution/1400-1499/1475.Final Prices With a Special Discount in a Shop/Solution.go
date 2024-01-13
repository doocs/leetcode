func finalPrices(prices []int) []int {
	n := len(prices)
	ans := make([]int, n)
	for i, v := range prices {
		ans[i] = v
		for j := i + 1; j < n; j++ {
			if prices[j] <= v {
				ans[i] -= prices[j]
				break
			}
		}
	}
	return ans
}