func maxNumberOfFamilies(n int, reservedSeats [][]int) int {
	d := map[int]int{}
	for _, e := range reservedSeats {
		i, j := e[0], e[1]
		d[i] |= 1 << (10 - j)
	}
	ans := (n - len(d)) * 2
	masks := [3]int{0b0111100000, 0b0000011110, 0b0001111000}
	for _, x := range d {
		for _, mask := range masks {
			if x&mask == 0 {
				x |= mask
				ans++
			}
		}
	}
	return ans
}