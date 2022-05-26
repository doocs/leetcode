func minimumRefill(plants []int, capacityA int, capacityB int) int {
	i, j := 0, len(plants)-1
	ans, a, b := 0, capacityA, capacityB
	for i <= j {
		if i == j {
			if max(capacityA, capacityB) < plants[i] {
				ans++
			}
			break
		}
		if capacityA < plants[i] {
			capacityA = a - plants[i]
			ans++
		} else {
			capacityA -= plants[i]
		}
		if capacityB < plants[j] {
			capacityB = b - plants[j]
			ans++
		} else {
			capacityB -= plants[j]
		}
		i++
		j--
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}