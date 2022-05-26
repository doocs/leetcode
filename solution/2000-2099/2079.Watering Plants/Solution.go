func wateringPlants(plants []int, capacity int) int {
	ans, cap := 0, capacity
	for i, x := range plants {
		if cap >= x {
			cap -= x
			ans++
		} else {
			cap = capacity - x
			ans += i*2 + 1
		}
	}
	return ans
}