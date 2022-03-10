func complexNumberMultiply(num1, num2 string) string {
	parse := func(num string) (a, b int) {
		i := strings.IndexByte(num, '+')
		a, _ = strconv.Atoi(num[:i])
		b, _ = strconv.Atoi(num[i+1 : len(num)-1])
		return
	}
	a, b := parse(num1)
	c, d := parse(num2)
	return fmt.Sprintf("%d+%di", a*c-b*d, a*d+b*c)
}