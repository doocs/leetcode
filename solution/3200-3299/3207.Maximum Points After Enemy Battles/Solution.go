func maximumPoints(enemyEnergies []int, currentEnergy int) (ans int64) {
	sort.Ints(enemyEnergies)
	if currentEnergy < enemyEnergies[0] {
		return 0
	}
	for i := len(enemyEnergies) - 1; i >= 0; i-- {
		ans += int64(currentEnergy / enemyEnergies[0])
		currentEnergy %= enemyEnergies[0]
		currentEnergy += enemyEnergies[i]
	}
	return
}