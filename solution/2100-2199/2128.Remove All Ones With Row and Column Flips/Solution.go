func removeOnes(grid [][]int) bool {
	s := map[string]bool{}
	for _, row := range grid {
		t := []byte{}
		for _, x := range row {
			if row[0] == 1 {
				x ^= 1
			}
			t = append(t, byte(x)+'0')
		}
		s[string(t)] = true
	}
	return len(s) == 1
}