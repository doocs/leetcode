func maxEnergyBoost(energyDrinkA []int, energyDrinkB []int) int64 {
	n := len(energyDrinkA)
	f := make([][2]int64, n)
	f[0][0] = int64(energyDrinkA[0])
	f[0][1] = int64(energyDrinkB[0])
	for i := 1; i < n; i++ {
		f[i][0] = max(f[i-1][0]+int64(energyDrinkA[i]), f[i-1][1])
		f[i][1] = max(f[i-1][1]+int64(energyDrinkB[i]), f[i-1][0])
	}
	return max(f[n-1][0], f[n-1][1])
}
