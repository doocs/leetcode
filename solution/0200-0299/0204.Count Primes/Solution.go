func countPrimes(n int) int {
	primes := make([]bool, n)
	for i := range primes {
		primes[i] = true
	}
	ans := 0
	for i := 2; i < n; i++ {
		if primes[i] {
			ans++
			for j := i + i; j < n; j += i {
				primes[j] = false
			}
		}
	}
	return ans
}