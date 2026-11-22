func assignUmbrella(rain []int) []string {
	n := len(rain)
	a, b := 0, 0
	ans := make([]string, n)
	for i, x := range rain {
		if x == 0 {
			ans[i] = "None"
		} else if a <= b && i < n-1 && rain[i+1] == 1 {
			ans[i] = "Alice"
			a++
		} else {
			ans[i] = "Bob"
			b++
		}
	}
	return ans
}
