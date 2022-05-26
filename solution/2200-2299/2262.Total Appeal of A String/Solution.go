func appealSum(s string) int64 {
	var ans, t int64
	pos := make([]int, 26)
	for i := range pos {
		pos[i] = -1
	}
	for i, c := range s {
		c -= 'a'
		t += int64(i - pos[c])
		ans += t
		pos[c] = i
	}
	return ans
}