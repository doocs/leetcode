func squareIsWhite(coordinates string) bool {
	x := coordinates[0] - 'a' + 1
	y := coordinates[1] - '0'
	return ((x + y) & 1) == 1
}