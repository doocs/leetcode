func survivedRobotsHealths(positions []int, healths []int, directions string) []int {
	n := len(positions)
	indices := make([]int, n)
	for i := range indices {
		indices[i] = i
	}

	sort.Slice(indices, func(i, j int) bool {
		return positions[indices[i]] < positions[indices[j]]
	})

	stack := []int{}

	for _, currentIndex := range indices {
		if directions[currentIndex] == 'R' {
			stack = append(stack, currentIndex)
		} else {
			for len(stack) > 0 && healths[currentIndex] > 0 {
				topIndex := stack[len(stack)-1]
				stack = stack[:len(stack)-1]

				if healths[topIndex] > healths[currentIndex] {
					healths[topIndex] -= 1
					healths[currentIndex] = 0
					stack = append(stack, topIndex)
				} else if healths[topIndex] < healths[currentIndex] {
					healths[currentIndex] -= 1
					healths[topIndex] = 0
				} else {
					healths[currentIndex] = 0
					healths[topIndex] = 0
				}
			}
		}
	}

	result := []int{}
	for _, health := range healths {
		if health > 0 {
			result = append(result, health)
		}
	}

	return result
}