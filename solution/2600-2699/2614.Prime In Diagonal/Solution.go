func diagonalPrime(nums [][]int) (ans int) {
	n := len(nums)
	for i, row := range nums {
		if isPrime(row[i]) {
			ans = max(ans, row[i])
		}
		if isPrime(row[n-i-1]) {
			ans = max(ans, row[n-i-1])
		}
	}
	return
}

func isPrime(x int) bool {
	if x < 2 {
		return false
	}
	for i := 2; i <= x/i; i++ {
		if x%i == 0 {
			return false
		}
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}