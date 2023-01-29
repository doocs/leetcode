func findGCD(nums []int) int {
	a, b := 1, 1000
	for _, x := range nums {
		if a < x {
			a = x
		}
		if b > x {
			b = x
		}
	}
	return gcd(a, b)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}