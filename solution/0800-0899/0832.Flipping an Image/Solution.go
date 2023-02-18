func flipAndInvertImage(image [][]int) [][]int {
	for _, row := range image {
		i, j := 0, len(row)-1
		for ; i < j; i, j = i+1, j-1 {
			if row[i] == row[j] {
				row[i] ^= 1
				row[j] ^= 1
			}
		}
		if i == j {
			row[i] ^= 1
		}
	}
	return image
}