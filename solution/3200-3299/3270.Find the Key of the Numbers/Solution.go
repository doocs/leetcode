func generateKey(num1 int, num2 int, num3 int) (ans int) {
	k := 1
	for i := 0; i < 4; i++ {
		x := min(min(num1/k%10, num2/k%10), num3/k%10)
		ans += x * k
		k *= 10
	}
	return
}
