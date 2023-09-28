func countSymmetricIntegers(low int, high int) (ans int) {
	f := func(x int) int {
		s := strconv.Itoa(x)
		n := len(s)
		if n&1 == 1 {
			return 0
		}
		a, b := 0, 0
		for i := 0; i < n/2; i++ {
			a += int(s[i] - '0')
			b += int(s[n/2+i] - '0')
		}
		if a == b {
			return 1
		}
		return 0
	}
	for x := low; x <= high; x++ {
		ans += f(x)
	}
	return
}