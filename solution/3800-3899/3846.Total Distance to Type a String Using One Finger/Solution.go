var pos map[byte][2]int

func init() {
	pos = make(map[byte][2]int)
	keys := []string{"qwertyuiop", "asdfghjkl", "zxcvbnm"}
	for i, row := range keys {
		for j := 0; j < len(row); j++ {
			pos[row[j]] = [2]int{i, j}
		}
	}
}

func totalDistance(s string) int {
	pre := byte('a')
	ans := 0

	for i := 0; i < len(s); i++ {
		cur := s[i]
		p1 := pos[pre]
		p2 := pos[cur]
		ans += abs(p1[0]-p2[0]) + abs(p1[1]-p2[1])
		pre = cur
	}

	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}