func maxMatrixSum(matrix [][]int) int64 {
	s := 0
	cnt, mi := 0, math.MaxInt32
	for _, row := range matrix {
		for _, v := range row {
			s += abs(v)
			mi = min(mi, abs(v))
			if v < 0 {
				cnt++
			}
		}
	}
	if cnt%2 == 1 {
		s -= mi * 2
	}
	return int64(s)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}