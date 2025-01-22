func countOperations(num1 int, num2 int) (ans int) {
	for ; num1 != 0 && num2 != 0; ans++ {
		if num1 >= num2 {
			num1 -= num2
		} else {
			num2 -= num1
		}
	}
	return
}
