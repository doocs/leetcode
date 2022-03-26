func winnerOfGame(colors string) bool {
	var a, b, cnt1, cnt2 int
	for _, c := range colors {
		if c == 'A' {
			a++
			if a > 2 {
				cnt1++
			}
			b = 0
		} else {
			b++
			if b > 2 {
				cnt2++
			}
			a = 0
		}
	}
	return cnt1 > cnt2
}