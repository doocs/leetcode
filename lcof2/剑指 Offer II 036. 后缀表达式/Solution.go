func evalRPN(tokens []string) int {
	// https://github.com/emirpasic/gods#arraystack
	stack := arraystack.New()
	for _, token := range tokens {
		if len(token) > 1 || token[0] >= '0' && token[0] <= '9' {
			num, _ := strconv.Atoi(token)
			stack.Push(num)
		} else {
			y := popInt(stack)
			x := popInt(stack)
			switch token {
			case "+":
				stack.Push(x + y)
			case "-":
				stack.Push(x - y)
			case "*":
				stack.Push(x * y)
			default:
				stack.Push(x / y)
			}
		}
	}
	return popInt(stack)
}

func popInt(stack *arraystack.Stack) int {
	v, _ := stack.Pop()
	return v.(int)
}
