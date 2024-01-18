func minFlipsMonoIncr(s string) int {
	n := len(s)
	presum := make([]int, n+1)
	for i, c := range s {
		presum[i+1] = presum[i] + int(c-'0')
	}
	ans := presum[n]
	for i := range s {
		ans = min(ans, presum[i]+n-i-(presum[n]-presum[i]))
	}
	return ans
}