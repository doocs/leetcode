func numberOfPoints(nums [][]int) (ans int) {
	d := map[int]int{}
	for _, e := range nums {
		start, end := e[0], e[1]
		d[start]++
		d[end+1]--
	}
	keys := []int{}
	for k := range d {
		keys = append(keys, k)
	}
	s, last := 0, 0
	sort.Ints(keys)
	for _, cur := range keys {
		if s > 0 {
			ans += cur - last
		}
		s += d[cur]
		last = cur
	}
	return
}
