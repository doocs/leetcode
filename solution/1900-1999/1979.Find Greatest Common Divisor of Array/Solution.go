func findGCD(nums []int) int {
	a, b := 0, 1000
	for _, num := range nums {
		a = max(a, num)
		b = min(b, num)
	}
	return gcd(a, b)
}

func gcd(a, b int) int {
	if b > 0 {
		return gcd(b, a%b)
	}
	return a
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}