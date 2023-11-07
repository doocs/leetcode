func countPairs(coordinates [][]int, k int) (ans int) {
	cnt := map[[2]int]int{}
	for _, c := range coordinates {
		x2, y2 := c[0], c[1]
		for a := 0; a <= k; a++ {
			b := k - a
			x1, y1 := a^x2, b^y2
			ans += cnt[[2]int{x1, y1}]
		}
		cnt[[2]int{x2, y2}]++
	}
	return
}