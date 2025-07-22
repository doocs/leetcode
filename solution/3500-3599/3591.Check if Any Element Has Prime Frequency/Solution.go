func checkPrimeFrequency(nums []int) bool {
	cnt := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
	}
	for _, x := range cnt {
		if isPrime(x) {
			return true
		}
	}
	return false
}

func isPrime(x int) bool {
	if x < 2 {
		return false
	}
	for i := 2; i*i <= x; i++ {
		if x%i == 0 {
			return false
		}
	}
	return true
}
