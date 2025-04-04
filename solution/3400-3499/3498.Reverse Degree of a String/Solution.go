func reverseDegree(s string) (ans int) {
	for i, c := range s {
		x := 26 - int(c-'a')
		ans += (i + 1) * x
	}
	return
}