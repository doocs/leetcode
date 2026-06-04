func maximumSaleItems(items [][]int, budget int) int {
	f := make([]int, budget+1)
	mn := math.MaxInt32

	for _, item := range items {
		factor := item[0]
		price := item[1]
		mn = min(mn, price)

		cnt := 0
		for _, jItem := range items {
			if jItem[0]%factor == 0 {
				cnt++
			}
		}

		for j := budget; j >= price; j-- {
			if f[j-price]+cnt > f[j] {
				f[j] = f[j-price] + cnt
			}
		}
	}

	ans := 0
	for i, x := range f {
		extra := (budget - i) / mn
		ans = max(ans, x+extra)
	}

	return ans
}