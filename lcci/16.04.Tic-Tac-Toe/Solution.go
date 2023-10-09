func tictactoe(board []string) string {
	n := len(board)
	rows := make([]int, n)
	cols := make([]int, n)
	dg, udg := 0, 0
	hasEmptyGrid := false
	for i, row := range board {
		for j, c := range row {
			if c == ' ' {
				hasEmptyGrid = true
				continue
			}
			v := 1
			if c == 'O' {
				v = -1
			}
			rows[i] += v
			cols[j] += v
			if i == j {
				dg += v
			}
			if i+j == n-1 {
				udg += v
			}
			if abs(rows[i]) == n || abs(cols[j]) == n || abs(dg) == n || abs(udg) == n {
				return string(c)
			}
		}
	}
	if hasEmptyGrid {
		return "Pending"
	}
	return "Draw"
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}