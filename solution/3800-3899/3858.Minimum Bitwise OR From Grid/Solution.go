func minimumOR(grid [][]int) int {
	mx := 0
	for _, row := range grid {
		mx = max(mx, slices.Max(row))
	}

	m := bits.Len(uint(mx))
	ans := 0

	for i := m - 1; i >= 0; i-- {
		mask := ans | ((1 << i) - 1)
		for _, row := range grid {
			found := false
			for _, x := range row {
				if (x | mask) == mask {
					found = true
					break
				}
			}
			if !found {
				ans |= 1 << i
				break
			}
		}
	}

	return ans
}
