func minimumRefill(plants []int, capacityA int, capacityB int) (ans int) {
	a, b := capacityA, capacityB
	i, j := 0, len(plants)-1
	for ; i < j; i, j = i+1, j-1 {
		if a < plants[i] {
			ans++
			a = capacityA
		}
		a -= plants[i]
		if b < plants[j] {
			ans++
			b = capacityB
		}
		b -= plants[j]
	}
	if i == j && max(a, b) < plants[i] {
		ans++
	}
	return
}