func minimumNumbers(num int, k int) int {
	if num == 0 {
		return 0
	}
	for i := 1; i <= 10; i++ {
		if (k*i)%10 == num%10 && k*i <= num {
			return i
		}
	}
	return -1
}