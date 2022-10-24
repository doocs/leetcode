func maximumWhiteTiles(tiles [][]int, carpetLen int) int {
	sort.Slice(tiles, func(i, j int) bool { return tiles[i][0] < tiles[j][0] })
	n := len(tiles)
	s, ans := 0, 0
	for i, j := 0, 0; i < n; i++ {
		for j < n && tiles[j][1]-tiles[i][0]+1 <= carpetLen {
			s += tiles[j][1] - tiles[j][0] + 1
			j++
		}
		if j < n && tiles[i][0]+carpetLen > tiles[j][0] {
			ans = max(ans, s+tiles[i][0]+carpetLen-tiles[j][0])
		} else {
			ans = max(ans, s)
		}
		s -= (tiles[i][1] - tiles[i][0] + 1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}