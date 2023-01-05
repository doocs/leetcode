func countEven(num int) (ans int) {
	ans = num/10*5 - 1
	s := 0
	for x := num / 10; x > 0; x /= 10 {
		s += x % 10
	}
	ans += (num%10 + 2 - (s & 1)) >> 1
	return
}