const MX = 200000

var isPrime = func() []bool {
	p := make([]bool, MX+1)
	for i := range p {
		p[i] = true
	}
	p[0], p[1] = false, false
	for i := 2; i <= MX/i; i++ {
		if p[i] {
			for j := i * i; j <= MX; j += i {
				p[j] = false
			}
		}
	}
	return p
}()

var primes = func() []int {
	var res []int
	for i := 2; i <= MX; i++ {
		if isPrime[i] {
			res = append(res, i)
		}
	}
	return res
}()

func minOperations(nums []int) (ans int) {
	for i, x := range nums {
		if i%2 == 0 {
			j := sort.SearchInts(primes, x)
			ans += primes[j] - x
		} else if isPrime[x] {
			if x == 2 {
				ans += 2
			} else {
				ans++
			}
		}
	}
	return
}
