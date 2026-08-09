func minPrice(prices []int, discounts []int) float64 {
	sort.Ints(prices)
	sort.Ints(discounts)

	i := len(prices) - 1
	j := len(discounts) - 1

	var ans float64

	for i >= 0 && j >= 0 {
		ans += float64(prices[i]) * float64(100-discounts[j]) / 100.0
		i--
		j--
	}

	for i >= 0 {
		ans += float64(prices[i])
		i--
	}

	return ans
}
