func rampartDefensiveLine(rampart [][]int) int {
	check := func(w int) bool {
		last := rampart[0][1]
		for i := 1; i < len(rampart)-1; i++ {
			x, y := rampart[i][0], rampart[i][1]
			a := x - last
			b := max(w-a, 0)
			if y+b > rampart[i+1][0] {
				return false
			}
			last = y + b
		}
		return true
	}

	left, right := 0, rampart[1][0]-rampart[0][1]+rampart[2][0]-rampart[1][1]
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}