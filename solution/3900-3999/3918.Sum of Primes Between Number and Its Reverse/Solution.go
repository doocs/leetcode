var isPrime [1001]bool

func init() {
	for i := 0; i <= 1000; i++ {
		isPrime[i] = true
	}
	isPrime[0], isPrime[1] = false, false
	for i := 2; i*i <= 1000; i++ {
		if isPrime[i] {
			for j := i * i; j <= 1000; j += i {
				isPrime[j] = false
			}
		}
	}
}

func sumOfPrimesInRange(n int) (ans int) {
	r := 0
	for x := n; x > 0; x /= 10 {
		r = r*10 + x%10
	}
	low := min(n, r)
	high := max(n, r)
	for x := low; x <= high; x++ {
		if isPrime[x] {
			ans += x
		}
	}
	return
}
