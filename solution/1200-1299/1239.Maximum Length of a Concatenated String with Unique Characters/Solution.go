func maxLength(arr []string) (ans int) {
	masks := []int{0}
	for _, s := range arr {
		mask := 0
		for _, c := range s {
			i := int(c - 'a')
			if mask>>i&1 == 1 {
				mask = 0
				break
			}
			mask |= 1 << i
		}
		if mask == 0 {
			continue
		}
		n := len(masks)
		for _, m := range masks[:n] {
			if m&mask == 0 {
				masks = append(masks, m|mask)
				ans = max(ans, bits.OnesCount(uint(m|mask)))
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}