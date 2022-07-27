func fractionAddition(expression string) string {
	x, y := 0, 6*7*8*9*10
	if unicode.IsDigit(rune(expression[0])) {
		expression = "+" + expression
	}
	i, n := 0, len(expression)
	for i < n {
		sign := 1
		if expression[i] == '-' {
			sign = -1
		}
		i++
		j := i
		for j < n && expression[j] != '+' && expression[j] != '-' {
			j++
		}
		s := expression[i:j]
		t := strings.Split(s, "/")
		a, _ := strconv.Atoi(t[0])
		b, _ := strconv.Atoi(t[1])
		x += sign * a * y / b
		i = j
	}
	z := gcd(abs(x), y)
	x /= z
	y /= z
	return fmt.Sprintf("%d/%d", x, y)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}