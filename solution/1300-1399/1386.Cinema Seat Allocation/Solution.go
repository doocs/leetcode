func maxNumberOfFamilies(n int, reservedSeats [][]int) int {
	m := map[int]int{}
	for _, e := range reservedSeats {
		i, j := e[0], 10-e[1]
		m[i] |= 1 << j
	}
	masks := []int{0b0111100000, 0b0000011110, 0b0001111000}
	ans := (n - len(m)) << 1
	for _, v := range m {
		for _, mask := range masks {
			if (v & mask) == 0 {
				v |= mask
				ans++
			}
		}
	}
	return ans
}