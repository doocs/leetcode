func canTransform(source []int, target []int) bool {
	var s, t int64
	for _, x := range source {
		s += int64(x)
	}
	for _, x := range target {
		t += int64(x)
	}
	return s == t
}
