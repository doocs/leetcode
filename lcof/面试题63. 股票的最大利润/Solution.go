func maxProfit(prices []int) int {
	mi, mx := math.MaxInt32, 0
	for _, price := range prices {
		mx = max(mx, price-mi)
		mi = min(mi, price)
	}
	return mx
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
