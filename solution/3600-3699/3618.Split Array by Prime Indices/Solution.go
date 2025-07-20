const M = 100000 + 10

var primes [M]bool

func init() {
	for i := 0; i < M; i++ {
		primes[i] = true
	}
	primes[0], primes[1] = false, false

	for i := 2; i < M; i++ {
		if primes[i] {
			for j := i + i; j < M; j += i {
				primes[j] = false
			}
		}
	}
}

func splitArray(nums []int) (ans int64) {
	for i, num := range nums {
		if primes[i] {
			ans += int64(num)
		} else {
			ans -= int64(num)
		}
	}
	return max(ans, -ans)
}
