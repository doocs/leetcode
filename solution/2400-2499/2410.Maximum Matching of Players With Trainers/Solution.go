func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	ans, j := 0, 0
	for _, p := range players {
		for j < len(trainers) && trainers[j] < p {
			j++
		}
		if j < len(trainers) {
			ans++
			j++
		}
	}
	return ans
}