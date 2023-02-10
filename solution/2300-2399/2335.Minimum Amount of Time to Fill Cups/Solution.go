func fillCups(amount []int) int {
	sort.Ints(amount)
	if amount[0]+amount[1] <= amount[2] {
		return amount[2]
	}
	return (amount[0] + amount[1] + amount[2] + 1) / 2
}