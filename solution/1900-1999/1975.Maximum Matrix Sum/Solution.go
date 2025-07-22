func maxMatrixSum(matrix [][]int) int64 {
	var s int64
	mi, cnt := 1<<30, 0
	for _, row := range matrix {
		for _, x := range row {
			if x < 0 {
				cnt++
				x = -x
			}
			mi = min(mi, x)
			s += int64(x)
		}
	}
	if cnt%2 == 0 {
		return s
	}
	return s - int64(mi*2)
}
