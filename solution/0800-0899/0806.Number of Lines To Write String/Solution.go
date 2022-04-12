func numberOfLines(widths []int, s string) []int {
	last, row := 0, 1
	for _, c := range s {
		w := widths[c-'a']
		if last+w <= 100 {
			last += w
		} else {
			row++
			last = w
		}
	}
	return []int{row, last}
}