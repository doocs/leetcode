func chalkReplacer(chalk []int, k int) int {
	n := len(chalk)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + chalk[i]
	}
	k %= s[n]
	left, right := 0, n-1
	for left < right {
		mid := (left + right) >> 1
		if s[mid+1] > k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}