func numOfSubarrays(arr []int) int {
	const MOD = 1e9 + 7
	counter := make([]int, 2)
	s, ans := 0, 0
	for _, v := range arr {
		s += v
		counter[s%2]++
		if s%2 == 1 {
			ans = (ans + 1 + counter[0]) % MOD
		} else {
			ans = (ans + counter[1]) % MOD
		}
	}
	return ans
}