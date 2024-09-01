func checkTwoChessboards(coordinate1 string, coordinate2 string) bool {
	x := coordinate1[0] - coordinate2[0]
	y := coordinate1[1] - coordinate2[1]
	return (x+y)%2 == 0
}
