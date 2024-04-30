func stoneGameVI(aliceValues []int, bobValues []int) int {
	vals := make([][2]int, len(aliceValues))
	for i, a := range aliceValues {
		vals[i] = [2]int{a + bobValues[i], i}
	}
	slices.SortFunc(vals, func(a, b [2]int) int { return b[0] - a[0] })
	a, b := 0, 0
	for k, v := range vals {
		i := v[1]
		if k%2 == 0 {
			a += aliceValues[i]
		} else {
			b += bobValues[i]
		}
	}
	if a > b {
		return 1
	}
	if a < b {
		return -1
	}
	return 0
}