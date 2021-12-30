func checkPerfectNumber(num int) bool {
	if num == 1 {
		return false
	}
	s := 1
	for i := 2; i*i <= num; i++ {
		if num%i == 0 {
			s += i
			if i != num/i {
				s += num / i
			}
		}
	}
	return s == num
}