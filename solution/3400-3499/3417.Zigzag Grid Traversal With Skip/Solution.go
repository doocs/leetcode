func zigzagTraversal(grid [][]int) (ans []int) {
	ok := true
	for i, row := range grid {
		if i%2 != 0 {
			slices.Reverse(row)
		}
		for _, x := range row {
			if ok {
				ans = append(ans, x)
			}
			ok = !ok
		}
	}
	return
}
