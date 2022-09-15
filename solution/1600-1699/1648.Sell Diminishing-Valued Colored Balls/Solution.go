func maxProfit(inventory []int, orders int) int {
	var mod int = 1e9 + 7
	i, n, ans := 0, len(inventory), 0
	sort.Ints(inventory)
	for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
		inventory[i], inventory[j] = inventory[j], inventory[i]
	}
	for orders > 0 {
		for i < n && inventory[i] >= inventory[0] {
			i++
		}
		nxt := 0
		if i < n {
			nxt = inventory[i]
		}
		cnt := i
		x := inventory[0] - nxt
		tot := cnt * x
		if tot > orders {
			decr := orders / cnt
			a1, an := inventory[0]-decr+1, inventory[0]
			ans += (a1 + an) * decr / 2 * cnt
			ans += (a1 - 1) * (orders % cnt)
		} else {
			a1, an := nxt+1, inventory[0]
			ans += (a1 + an) * x / 2 * cnt
			inventory[0] = nxt
		}
		orders -= tot
		ans %= mod
	}
	return ans
}