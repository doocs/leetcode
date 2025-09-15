func minArrivalsToDiscard(arrivals []int, w int, m int) (ans int) {
	cnt := make(map[int]int)
	n := len(arrivals)
	marked := make([]int, n)
	for i, x := range arrivals {
		if i >= w {
			cnt[arrivals[i-w]] -= marked[i-w]
		}
		if cnt[x] >= m {
			ans++
		} else {
			marked[i] = 1
			cnt[x]++
		}
	}
	return
}
