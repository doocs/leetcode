func addRungs(rungs []int, dist int) (ans int) {
	prev := 0
	for _, x := range rungs {
		ans += (x - prev - 1) / dist
		prev = x
	}
	return
}