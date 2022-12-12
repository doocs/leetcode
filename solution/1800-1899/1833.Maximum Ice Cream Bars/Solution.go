func maxIceCream(costs []int, coins int) int {
	sort.Ints(costs)
	for i, c := range costs {
		if coins < c {
			return i
		}
		coins -= c
	}
	return len(costs)
}