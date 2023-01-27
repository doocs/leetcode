func maxPrice(items [][]int, capacity int) (ans float64) {
	sort.Slice(items, func(i, j int) bool { return items[i][1]*items[j][0] < items[i][0]*items[j][1] })
	for _, e := range items {
		p, w := e[0], e[1]
		v := min(w, capacity)
		ans += float64(v) / float64(w) * float64(p)
		capacity -= v
	}
	if capacity > 0 {
		return -1
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}