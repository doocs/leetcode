func stoneGameVI(aliceValues []int, bobValues []int) int {
	arr := make([][]int, len(aliceValues))
	for i, a := range aliceValues {
		b := bobValues[i]
		arr[i] = []int{a + b, i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] > arr[j][0] })
	a, b := 0, 0
	for i, v := range arr {
		if i%2 == 0 {
			a += aliceValues[v[1]]
		} else {
			b += bobValues[v[1]]
		}
	}
	if a == b {
		return 0
	}
	if a > b {
		return 1
	}
	return -1
}