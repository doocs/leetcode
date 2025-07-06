var primes []int

func init() {
	x := 2
	M := 1000
	for len(primes) < M {
		is_prime := true
		for _, p := range primes {
			if p*p > x {
				break
			}
			if x%p == 0 {
				is_prime = false
				break
			}
		}
		if is_prime {
			primes = append(primes, x)
		}
		x++
	}
}

func minNumberOfPrimes(n int, m int) int {
	const inf = int(1e9)
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = inf
	}
	f[0] = 0

	for _, x := range primes[:m] {
		for i := x; i <= n; i++ {
			if f[i-x] < inf && f[i-x]+1 < f[i] {
				f[i] = f[i-x] + 1
			}
		}
	}

	if f[n] < inf {
		return f[n]
	}
	return -1
}
