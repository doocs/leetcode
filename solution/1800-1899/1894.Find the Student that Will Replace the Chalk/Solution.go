func chalkReplacer(chalk []int, k int) int {
	n := len(chalk)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + chalk[i]
	}
	k %= s[n]
	return sort.Search(n, func(i int) bool { return s[i+1] > k })
}