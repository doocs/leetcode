func findWinningPlayer(skills []int, k int) int {
	n := len(skills)
	k = min(k, n-1)
	i, cnt := 0, 0
	for j := 1; j < n; j++ {
		if skills[i] < skills[j] {
			i = j
			cnt = 1
		} else {
			cnt++
		}
		if cnt == k {
			break
		}
	}
	return i
}