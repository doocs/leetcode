func maximumPopulation(logs [][]int) int {
	delta := make([]int, 101)
	offset := 1950
	for _, log := range logs {
		delta[log[0]-offset]++
		delta[log[1]-offset]--
	}
	res, mx, cur := 0, 0, 0
	for i := 0; i < len(delta); i++ {
		cur += delta[i]
		if cur > mx {
			mx = cur
			res = i
		}
	}
	return res + offset
}