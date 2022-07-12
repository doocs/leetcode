func mostVisited(n int, rounds []int) []int {
	m := len(rounds) - 1
	var ans []int
	if rounds[0] <= rounds[m] {
		for i := rounds[0]; i <= rounds[m]; i++ {
			ans = append(ans, i)
		}
	} else {
		for i := 1; i <= rounds[m]; i++ {
			ans = append(ans, i)
		}
		for i := rounds[0]; i <= n; i++ {
			ans = append(ans, i)
		}
	}
	return ans
}