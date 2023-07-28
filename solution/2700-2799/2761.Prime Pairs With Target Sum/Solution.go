func findPrimePairs(n int) (ans [][]int) {
	primes := make([]bool, n)
	for i := range primes {
		primes[i] = true
	}
	for i := 2; i < n; i++ {
		if primes[i] {
			for j := i + i; j < n; j += i {
				primes[j] = false
			}
		}
	}
	for x := 2; x <= n/2; x++ {
		y := n - x
		if primes[x] && primes[y] {
			ans = append(ans, []int{x, y})
		}
	}
	return
}