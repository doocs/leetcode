import "sort"

func maximumSaleItems(items [][]int, budget int) int {
    n := len(items)
    freq := make([]int, n+1)
    gain := make([]int, n+1)

    minPrice := int(^uint(0) >> 1)

    for _, item := range items {
        freq[item[0]]++
        minPrice = min(minPrice, item[1])
    }

    for f := 1; f <= n; f++ {
        for j := f; j <= n; j += f {
            gain[f] += freq[j]
        }
        gain[f]--
    }

    type Deal struct {
        price, count int
    }

    deals := []Deal{}

    for _, item := range items {
        f, p := item[0], item[1]
        if gain[f] > 0 && p <= 2*minPrice {
            deals = append(deals, Deal{p, gain[f]})
        }
    }

    sort.Slice(deals, func(i, j int) bool {
        return deals[i].price < deals[j].price
    })

    ans := 0

    for _, d := range deals {
        take := min(d.count, budget/d.price)
        ans += 2 * take
        budget -= take * d.price
    }

    return ans + budget/minPrice
}
