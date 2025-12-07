const MX = 500000

var (
	isPrime = make([]bool, MX+1)
	primes  []int
	S       []int
)

func init() {
	for i := range isPrime {
		isPrime[i] = true
	}
	isPrime[0] = false
	isPrime[1] = false

	for i := 2; i <= MX; i++ {
		if isPrime[i] {
			primes = append(primes, i)
			if i*i <= MX {
				for j := i * i; j <= MX; j += i {
					isPrime[j] = false
				}
			}
		}
	}

	S = append(S, 0)
	t := 0
	for _, x := range primes {
		t += x
		if t > MX {
			break
		}
		if isPrime[t] {
			S = append(S, t)
		}
	}
}

func largestPrime(n int) int {
	i := sort.SearchInts(S, n+1)
	return S[i-1]
}
