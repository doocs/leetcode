func leastBricks(wall [][]int) int {
	cnt := map[int]int{}
	for _, row := range wall {
		s := 0
		for _, x := range row[:len(row)-1] {
			s += x
			cnt[s]++
		}
	}
	mx := 0
	for _, x := range cnt {
		mx = max(mx, x)
	}
	return len(wall) - mx
}
