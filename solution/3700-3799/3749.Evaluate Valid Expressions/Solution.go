func evaluateExpression(expression string) int64 {
	var parse func(int) (int64, int)
	parse = func(i int) (int64, int) {
		if expression[i] >= '0' && expression[i] <= '9' || expression[i] == '-' {
			j := i
			if expression[j] == '-' {
				j++
			}
			for j < len(expression) && expression[j] >= '0' && expression[j] <= '9' {
				j++
			}
			num, _ := strconv.ParseInt(expression[i:j], 10, 64)
			return num, j
		}

		j := i
		for expression[j] != '(' {
			j++
		}
		op := expression[i:j]
		j++

		val1, nextJ1 := parse(j)
		j = nextJ1 + 1

		val2, nextJ2 := parse(j)
		j = nextJ2 + 1

		var res int64
		switch op {
		case "add":
			res = val1 + val2
		case "sub":
			res = val1 - val2
		case "mul":
			res = val1 * val2
		case "div":
			res = val1 / val2
		}

		return res, j
	}

	result, _ := parse(0)
	return result
}
