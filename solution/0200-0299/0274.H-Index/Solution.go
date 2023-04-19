func hIndex(citations []int) int {
	n := len(citations)
	cnt := make([]int, n+1)
	for _, x := range citations {
		cnt[min(x, n)]++
	}
	for h, s := n, 0; ; h-- {
		s += cnt[h]
		if s >= h {
			return h
		}
	}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}