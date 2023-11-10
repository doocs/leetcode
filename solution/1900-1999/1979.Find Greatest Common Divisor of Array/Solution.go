func findGCD(nums []int) int {
	a, b := slices.Max(nums), slices.Min(nums)
	return gcd(a, b)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}