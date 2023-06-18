func winnerOfGame(colors string) bool {
	n := len(colors)
	a, b := 0, 0
	for i, j := 0, 0; i < n; i = j {
		for j < n && colors[j] == colors[i] {
			j++
		}
		m := j - i - 2
		if m > 0 {
			if colors[i] == 'A' {
				a += m
			} else {
				b += m
			}
		}
	}
	return a > b
}