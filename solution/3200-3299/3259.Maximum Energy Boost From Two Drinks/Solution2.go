func maxEnergyBoost(energyDrinkA []int, energyDrinkB []int) int64 {
	n := len(energyDrinkA)
	f, g := energyDrinkA[0], energyDrinkB[0]
	for i := 1; i < n; i++ {
		f, g = max(f+energyDrinkA[i], g), max(g+energyDrinkB[i], f)
	}
	return int64(max(f, g))
}
