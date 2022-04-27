func countPrimeSetBits(left int, right int) int {
	primes := map[int]bool{2: true, 3: true, 5: true, 7: true, 11: true, 13: true, 17: true, 19: true}
	ans := 0
	for i := left; i <= right; i++ {
		if primes[bits.OnesCount(uint(i))] {
			ans++
		}
	}
	return ans
}