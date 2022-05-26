func maxProfit(prices []int) int {
	res := 0
	for i := 1; i < len(prices); i++ {
		t := prices[i] - prices[i-1]
		if t > 0 {
			res += t
		}
	}
	return res
}