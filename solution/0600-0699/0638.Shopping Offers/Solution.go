func shoppingOffers(price []int, special [][]int, needs []int) int {
	total := func(price, needs []int) int {
		s := 0
		for i := 0; i < len(needs); i++ {
			s += price[i] * needs[i]
		}
		return s
	}

	min := func(a, b int) int {
		if a < b {
			return a
		}
		return b
	}

	ans := total(price, needs)
	var t []int
	for _, offer := range special {
		t = t[:0]
		for j := 0; j < len(needs); j++ {
			if offer[j] > needs[j] {
				t = t[:0]
				break
			}
			t = append(t, needs[j]-offer[j])
		}
		if len(t) > 0 {
			ans = min(ans, offer[len(offer)-1]+shoppingOffers(price, special, t))
		}
	}
	return ans
}