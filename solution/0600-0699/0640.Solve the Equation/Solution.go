func solveEquation(equation string) string {
	f := func(s string) []int {
		x, y := 0, 0
		if s[0] != '-' {
			s = "+" + s
		}
		i, n := 0, len(s)
		for i < n {
			sign := 1
			if s[i] == '-' {
				sign = -1
			}
			i++
			j := i
			for j < n && s[j] != '+' && s[j] != '-' {
				j++
			}
			v := s[i:j]
			if s[j-1] == 'x' {
				a := 1
				if len(v) > 1 {
					a, _ = strconv.Atoi(v[:len(v)-1])
				}
				x += sign * a
			} else {
				a, _ := strconv.Atoi(v)
				y += sign * a
			}
			i = j
		}
		return []int{x, y}
	}

	es := strings.Split(equation, "=")
	a, b := f(es[0]), f(es[1])
	x1, y1 := a[0], a[1]
	x2, y2 := b[0], b[1]
	if x1 == x2 {
		if y1 == y2 {
			return "Infinite solutions"
		} else {
			return "No solution"
		}
	}
	return fmt.Sprintf("x=%d", (y2-y1)/(x1-x2))
}