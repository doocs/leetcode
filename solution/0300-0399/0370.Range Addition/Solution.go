func getModifiedArray(length int, updates [][]int) []int {
	delta := make([]int, length)
	for _, e := range updates {
		delta[e[0]] += e[2]
		if e[1]+1 < length {
			delta[e[1]+1] -= e[2]
		}
	}
	for i := 1; i < length; i++ {
		delta[i] += delta[i-1]
	}
	return delta
}