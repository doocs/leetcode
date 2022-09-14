func flipLights(n int, presses int) int {
	if n > 6 {
		n = 6
	}
	ops := []int{0b111111, 0b010101, 0b101010, 0b100100}
	vis := map[int]bool{}
	for mask := 0; mask < 1<<4; mask++ {
		cnt := bits.OnesCount(uint(mask))
		if cnt <= presses && cnt%2 == presses%2 {
			t := 0
			for i, op := range ops {
				if mask>>i&1 == 1 {
					t ^= op
				}
			}
			t &= 1<<6 - 1
			t >>= (6 - n)
			vis[t] = true
		}
	}
	return len(vis)
}