func oddCells(m int, n int, indices [][]int) int {
	row := make([]int, m)
	col := make([]int, n)
	for _, e := range indices {
		r, c := e[0], e[1]
		row[r]++
		col[c]++
	}
	cnt1, cnt2 := 0, 0
	for _, v := range row {
		cnt1 += v % 2
	}
	for _, v := range col {
		cnt2 += v % 2
	}
	return cnt1*(n-cnt2) + cnt2*(m-cnt1)
}