func carFleet(target int, position []int, speed []int) (ans int) {
	n := len(position)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return position[idx[j]] < position[idx[i]] })
	var pre float64
	for _, i := range idx {
		t := float64(target-position[i]) / float64(speed[i])
		if t > pre {
			ans++
			pre = t
		}
	}
	return
}