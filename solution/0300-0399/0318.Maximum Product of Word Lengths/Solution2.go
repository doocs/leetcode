func maxProduct(words []string) (ans int) {
	mask := map[int]int{}
	for _, s := range words {
		a := len(s)
		x := 0
		for _, c := range s {
			x |= 1 << (c - 'a')
		}
		for y, b := range mask {
			if x&y == 0 {
				ans = max(ans, a*b)
			}
		}
		mask[x] = max(mask[x], a)
	}
	return
}