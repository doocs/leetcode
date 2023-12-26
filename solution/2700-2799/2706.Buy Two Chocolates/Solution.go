func buyChoco(prices []int, money int) int {
	a, b := 1001, 1001
	for _, x := range prices {
		if x < a {
			a, b = x, a
		} else if x < b {
			b = x
		}
	}
	cost := a + b
	if money < cost {
		return money
	}
	return money - cost
}