func minimumNumbers(num int, k int) int {
	if num == 0 {
		return 0
	}
	for i := 1; i <= num; i++ {
		t := num - k*i
		if t >= 0 && t%10 == 0 {
			return i
		}
	}
	return -1
}