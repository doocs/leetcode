func maximumPrimeDifference(nums []int) int {
	for i := 0; ; i++ {
		if isPrime(nums[i]) {
			for j := len(nums) - 1; ; j-- {
				if isPrime(nums[j]) {
					return j - i
				}
			}
		}
	}
}

func isPrime(n int) bool {
	if n < 2 {
		return false
	}
	for i := 2; i <= n/i; i++ {
		if n%i == 0 {
			return false
		}
	}
	return true
}