func countEven(num int) (ans int) {
	for i := 1; i <= num; i++ {
		s := 0
		for x := i; x > 0; x /= 10 {
			s += x % 10
		}
		if s%2 == 0 {
			ans++
		}
	}
	return
}