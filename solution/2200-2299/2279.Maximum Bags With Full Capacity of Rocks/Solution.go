func maximumBags(capacity []int, rocks []int, additionalRocks int) int {
	n := len(capacity)
	d := make([]int, n)
	for i, v := range capacity {
		d[i] = v - rocks[i]
	}
	sort.Ints(d)
	ans := 0
	for _, v := range d {
		if v > additionalRocks {
			break
		}
		ans++
		additionalRocks -= v
	}
	return ans
}