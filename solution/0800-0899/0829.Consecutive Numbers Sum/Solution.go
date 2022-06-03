func consecutiveNumbersSum(n int) int {
	n <<= 1
	ans := 0
	for k := 1; k*(k+1) <= n; k++ {
		if n%k == 0 && (n/k+1-k)%2 == 0 {
			ans++
		}
	}
	return ans
}