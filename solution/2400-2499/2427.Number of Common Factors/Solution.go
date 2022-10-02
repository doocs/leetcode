func commonFactors(a int, b int) int {
	ans := 0
	for i := 1; i <= a && i <= b; i++ {
		if a%i == 0 && b%i == 0 {
			ans++
		}
	}
	return ans
}