func maxLengthBetweenEqualCharacters(s string) int {
	d := make([]int, 26)
	for i := range d {
		d[i] = -1
	}
	ans := -1
	for i, c := range s {
		c -= 'a'
		if d[c] == -1 {
			d[c] = i
		} else {
			ans = max(ans, i-d[c]-1)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}