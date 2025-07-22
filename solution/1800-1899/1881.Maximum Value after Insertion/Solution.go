func maxValue(n string, x int) string {
	i := 0
	y := byte('0' + x)
	if n[0] == '-' {
		i++
		for i < len(n) && n[i] <= y {
			i++
		}
	} else {
		for i < len(n) && n[i] >= y {
			i++
		}
	}
	return n[:i] + string(y) + n[i:]
}
