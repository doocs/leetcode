func dailyTemperatures(T []int) []int {
	n := len(T)
	res := make([]int, n)
	stack := make([]int, 0)
	for i, v := range T {
		for len(stack) != 0 && T[stack[len(stack)-1]] < v {
			j := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			res[j] = i - j
		}
		stack = append(stack, i)
	}
	return res
}
