func maxWeight(pizzas []int) (ans int64) {
	n := len(pizzas)
	days := n / 4
	sort.Ints(pizzas)
	odd := (days + 1) / 2
	even := days - odd
	for i := n - odd; i < n; i++ {
		ans += int64(pizzas[i])
	}
	for i := n - odd - 2; even > 0; even-- {
		ans += int64(pizzas[i])
		i -= 2
	}
	return
}
