func minMoves(rooks [][]int) (ans int) {
	sort.Slice(rooks, func(i, j int) bool { return rooks[i][0] < rooks[j][0] })
	for i, row := range rooks {
		ans += int(math.Abs(float64(row[0] - i)))
	}
	sort.Slice(rooks, func(i, j int) bool { return rooks[i][1] < rooks[j][1] })
	for j, col := range rooks {
		ans += int(math.Abs(float64(col[1] - j)))
	}
	return
}