func leastBricks(wall [][]int) int {
	cnt := make(map[int]int)
	for _, row := range wall {
        width := 0
		for _, brick := range row[:len(row)-1] {
            width += brick
			cnt[width]++
		}
	}
	max := 0
	for _, v := range cnt {
		if v > max {
			max = v
		}
	}
	return len(wall) - max
}
