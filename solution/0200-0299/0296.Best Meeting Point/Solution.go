func minTotalDistance(grid [][]int) int {
	rows, cols := []int{}, []int{}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				rows = append(rows, i)
				cols = append(cols, j)
			}
		}
	}
	sort.Ints(cols)
	i := rows[len(rows)>>1]
	j := cols[len(cols)>>1]
	f := func(arr []int, x int) int {
		s := 0
		for _, v := range arr {
			s += abs(v - x)
		}
		return s
	}
	return f(rows, i) + f(cols, j)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}