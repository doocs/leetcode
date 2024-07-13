func isLongPressedName(name string, typed string) bool {
	m, n := len(name), len(typed)
	i, j := 0, 0

	for i < m && j < n {
		if name[i] != typed[j] {
			return false
		}
		x, y := i+1, j+1

		for x < m && name[x] == name[i] {
			x++
		}

		for y < n && typed[y] == typed[j] {
			y++
		}

		if x-i > y-j {
			return false
		}

		i, j = x, y
	}

	return i == m && j == n
}