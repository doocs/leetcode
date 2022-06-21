func numPrimeArrangements(n int) int {
	count := func(n int) int {
		cnt := 0
		primes := make([]bool, n+1)
		for i := range primes {
			primes[i] = true
		}
		for i := 2; i <= n; i++ {
			if primes[i] {
				cnt++
				for j := i + i; j <= n; j += i {
					primes[j] = false
				}
			}
		}
		return cnt
	}

	mod := int(1e9) + 7
	f := func(n int) int {
		ans := 1
		for i := 2; i <= n; i++ {
			ans = (ans * i) % mod
		}
		return ans
	}

	cnt := count(n)
	ans := f(cnt) * f(n-cnt)
	return ans % mod
}