func stoneGameIII(stoneValue []int) string {
	n := len(stoneValue)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		ans := -1 << 30
		s := 0
		for j := i; j < i+3 && j < n; j++ {
			s += stoneValue[j]
			ans = max(ans, s-f[j+1])
		}
		f[i] = ans
	}
	res := f[0]
	if res == 0 {
		return "Tie"
	}
	if res > 0 {
		return "Alice"
	}
	return "Bob"
}
