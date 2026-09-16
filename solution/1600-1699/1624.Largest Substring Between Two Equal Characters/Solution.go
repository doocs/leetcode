func maxLengthBetweenEqualCharacters(s string) int {
	d := make([]int, 26)
	for i := range d {
		d[i] = -1
	}
	ans := -1
	for i := range s {
		j := int(s[i] - 'a')
		if d[j] == -1 {
			d[j] = i
		} else {
			ans = max(ans, i-d[j]-1)
		}
	}
	return ans
}
