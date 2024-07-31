func maxProfit(prices []int) (ans int) {
    mi, ans := prices[0] ,0
	for _, v := range prices {
		ans = max(ans, v-mi)
		mi = min(mi, v)
	}
	return ans
}