func sortThreats(threats [][]int) [][]int {
	sort.Slice(threats, func(i, j int) bool {
		score1 := 2*int64(threats[i][1]) + int64(threats[i][2])
		score2 := 2*int64(threats[j][1]) + int64(threats[j][2])
		if score1 == score2 {
			return threats[i][0] < threats[j][0]
		}
		return score2 < score1
	})
	return threats
}
