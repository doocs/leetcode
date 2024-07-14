func wateringPlants(plants []int, capacity int) (ans int) {
	water := capacity
	for i, p := range plants {
		if water >= p {
			water -= p
			ans++
		} else {
			water = capacity - p
			ans += i*2 + 1
		}
	}
	return
}