const mx = 1000000

var primes = func() []int {
	isPrime := make([]bool, mx+1)
	for i := 2; i <= mx; i++ {
		isPrime[i] = true
	}

	primes := make([]int, 0)
	for i := 2; i <= mx; i++ {
		if isPrime[i] {
			primes = append(primes, i)
			if i <= mx/i {
				for j := i * i; j <= mx; j += i {
					isPrime[j] = false
				}
			}
		}
	}
	return primes
}()

func maxPrimes(n int, s int) []int {
	ans := make([]int, 0)
	for _, p := range primes {
		if p > n || p > s {
			break
		}
		ans = append(ans, p)
		s -= p
	}
	return ans
}
