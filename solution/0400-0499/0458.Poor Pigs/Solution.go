func poorPigs(buckets int, minutesToDie int, minutesToTest int) int {
	base := minutesToTest/minutesToDie + 1
	res := 0
	for p := 1; p < buckets; p *= base {
		res++
	}
	return res
}