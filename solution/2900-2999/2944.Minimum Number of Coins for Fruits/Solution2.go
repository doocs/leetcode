func minimumCoins(prices []int) int {
	for i := (len(prices) - 1) / 2; i > 0; i-- {
		prices[i-1] += slices.Min(prices[i : i*2+1])
	}
	return prices[0]
}