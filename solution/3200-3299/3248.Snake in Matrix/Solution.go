func finalPositionOfSnake(n int, commands []string) int {
	x, y := 0, 0
	for _, c := range commands {
		switch c[0] {
		case 'U':
			x--
		case 'D':
			x++
		case 'L':
			y--
		case 'R':
			y++
		}
	}
	return x*n + y
}
