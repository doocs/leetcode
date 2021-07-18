func chalkReplacer(chalk []int, k int) int {
	n := len(chalk)
	preSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		preSum[i+1] = preSum[i] + chalk[i]
	}
	k %= preSum[n]
	left, right := 0, n-1
	for left < right {
		mid := left + ((right - left) >> 1)
		if preSum[mid+1] > k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}