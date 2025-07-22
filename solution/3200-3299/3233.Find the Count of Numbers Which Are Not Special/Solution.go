const m = 31623

var primes [m + 1]bool

func init() {
	for i := range primes {
		primes[i] = true
	}
	primes[0] = false
	primes[1] = false
	for i := 2; i <= m; i++ {
		if primes[i] {
			for j := i * 2; j <= m; j += i {
				primes[j] = false
			}
		}
	}
}

func nonSpecialCount(l int, r int) int {
	lo := int(math.Ceil(math.Sqrt(float64(l))))
	hi := int(math.Floor(math.Sqrt(float64(r))))
	cnt := 0
	for i := lo; i <= hi; i++ {
		if primes[i] {
			cnt++
		}
	}
	return r - l + 1 - cnt
}