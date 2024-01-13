func sum(num1 int, num2 int) int {
	for num2 != 0 {
		carry := (num1 & num2) << 1
		num1 ^= num2
		num2 = carry
	}
	return num1
}