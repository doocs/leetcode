func numEquivDominoPairs(dominoes [][]int) int {
	counter := make([]int, 100)
	for _, d := range dominoes {
		if d[1] < d[0] {
			d[0], d[1] = d[1], d[0]
		}
		v := d[0]*10 + d[1]
		counter[v]++
	}
	ans := 0
	for _, c := range counter {
		ans += c * (c - 1) / 2
	}
	return ans
}