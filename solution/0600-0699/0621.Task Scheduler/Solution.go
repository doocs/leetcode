func leastInterval(tasks []byte, n int) int {
	cnt := make([]int, 26)
	x := 0
	for _, c := range tasks {
		c -= 'A'
		cnt[c]++
		x = max(x, cnt[c])
	}
	s := 0
	for _, v := range cnt {
		if v == x {
			s++
		}
	}
	return max(len(tasks), (x-1)*(n+1)+s)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}