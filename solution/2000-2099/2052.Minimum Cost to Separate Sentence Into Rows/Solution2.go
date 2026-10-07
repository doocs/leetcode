func minimumCost(sentence string, k int) int {
	s := []int{0}
	for _, w := range strings.Split(sentence, " ") {
		s = append(s, s[len(s)-1]+len(w))
	}
	n := len(s) - 1
	f := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		if s[n]-s[i]+n-i-1 <= k {
			continue
		}
		ans := math.MaxInt32
		for j := i + 1; j < n && s[j]-s[i]+j-i-1 <= k; j++ {
			m := s[j] - s[i] + j - i - 1
			ans = min(ans, f[j]+(k-m)*(k-m))
		}
		f[i] = ans
	}
	return f[0]
}
