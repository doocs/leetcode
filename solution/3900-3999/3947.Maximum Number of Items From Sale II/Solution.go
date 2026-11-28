func maximumSaleItems(items [][]int, budget int) int {
	n := len(items)
	freq := make([]int, n+1)
	minPrice := int(2e9)

	for _, item := range items {
		freq[item[0]]++
		if item[1] < minPrice {
			minPrice = item[1]
		}
	}

	divCount := make([]int, n+1)
	for f := 1; f <= n; f++ {
		if freq[f] == 0 {
			continue
		}
		for m := f; m <= n; m += f {
			divCount[f] += freq[m]
		}
	}

	type pair struct {
		price int
		gain  int
	}
	sortedItems := make([]pair, n)
	for i, item := range items {
		sortedItems[i] = pair{price: item[1], gain: divCount[item[0]] - 1}
	}

	sort.Slice(sortedItems, func(i, j int) bool {
		return sortedItems[i].price < sortedItems[j].price
	})

	rem := int64(budget)
	var ans int64 = 0
	limit := int64(2) * int64(minPrice)

	for _, item := range sortedItems {
		if int64(item.price) >= limit {
			break
		}
		if item.gain <= 0 {
			continue
		}

		canBuy := rem / int64(item.price)
		if int64(item.gain) < canBuy {
			canBuy = int64(item.gain)
		}

		ans += canBuy * 2
		rem -= canBuy * int64(item.price)

		if canBuy < int64(item.gain) {
			break
		}
	}

	ans += rem / int64(minPrice)
	return int(ans)
}
