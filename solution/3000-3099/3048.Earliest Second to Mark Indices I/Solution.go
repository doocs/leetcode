func earliestSecondToMarkIndices(nums []int, changeIndices []int) int {
	n, m := len(nums), len(changeIndices)
	l := sort.Search(m+1, func(t int) bool {
		last := make([]int, n+1)
		for s, i := range changeIndices[:t] {
			last[i] = s
		}
		decrement, marked := 0, 0
		for s, i := range changeIndices[:t] {
			if last[i] == s {
				if decrement < nums[i-1] {
					return false
				}
				decrement -= nums[i-1]
				marked++
			} else {
				decrement++
			}
		}
		return marked == n
	})
	if l > m {
		return -1
	}
	return l
}