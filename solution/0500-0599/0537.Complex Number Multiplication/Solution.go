func complexNumberMultiply(num1 string, num2 string) string {
	x, _ := strconv.ParseComplex(num1, 64)
	y, _ := strconv.ParseComplex(num2, 64)
	return fmt.Sprintf("%d+%di", int(real(x*y)), int(imag(x*y)))
}