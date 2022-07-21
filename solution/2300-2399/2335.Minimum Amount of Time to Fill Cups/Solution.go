func fillCups(amount []int) int {
	ans := 0
	for amount[0]+amount[1]+amount[2] > 0 {
		sort.Ints(amount)
		ans++
		amount[2]--
		if amount[1] > 0 {
			amount[1]--
		}
	}
	return ans
}