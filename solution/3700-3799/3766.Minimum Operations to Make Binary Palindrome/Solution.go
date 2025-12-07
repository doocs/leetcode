var primes []int

func init() {
	N := 1 << 14
	for i := 0; i < N; i++ {
		s := strconv.FormatInt(int64(i), 2)
		if isPalindrome(s) {
			primes = append(primes, i)
		}
	}
}

func isPalindrome(s string) bool {
	runes := []rune(s)
	for i := 0; i < len(runes)/2; i++ {
		if runes[i] != runes[len(runes)-1-i] {
			return false
		}
	}
	return true
}

func minOperations(nums []int) []int {
	ans := make([]int, len(nums))
	for k, x := range nums {
		i := sort.SearchInts(primes, x)
		t := math.MaxInt32
		if i < len(primes) {
			t = primes[i] - x
		}
		if i >= 1 {
			t = min(t, x-primes[i-1])
		}
		ans[k] = t
	}
	return ans
}
