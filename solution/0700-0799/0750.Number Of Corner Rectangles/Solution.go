func countCornerRectangles(grid [][]int) (ans int) {
	n := len(grid[0])
	type pair struct{ x, y int }
	cnt := map[pair]int{}
	for _, row := range grid {
		for i, x := range row {
			if x == 1 {
				for j := i + 1; j < n; j++ {
					if row[j] == 1 {
						t := pair{i, j}
						ans += cnt[t]
						cnt[t]++
					}
				}
			}
		}
	}
	return
}