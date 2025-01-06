func calculateScore(s string) (ans int64) {
	d := make(map[rune][]int)
	for i, x := range s {
		y := 'a' + 'z' - x
		if ls, ok := d[y]; ok {
			j := ls[len(ls)-1]
			d[y] = ls[:len(ls)-1]
			if len(d[y]) == 0 {
				delete(d, y)
			}
			ans += int64(i - j)
		} else {
			d[x] = append(d[x], i)
		}
	}
	return
}
