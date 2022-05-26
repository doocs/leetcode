func maxLength(arr []string) int {

	max := func(x, y int) int {
		if x > y {
			return x
		}
		return y
	}

	ans := 0
	masks := []int{0}

loop:
	for _, s := range arr {
		mask := 0
		for _, ch := range s {
			ch -= 'a'
			if (mask>>ch)&1 == 1 {
				continue loop
			}
			mask |= 1 << ch
		}
		for _, m := range masks {
			if m&mask == 0 {
				masks = append(masks, m|mask)
				ans = max(ans, bits.OnesCount(uint(m|mask)))
			}
		}
	}

	return ans
}
