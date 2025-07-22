func buildArray(target []int, n int) (ans []string) {
	cur := 1
	for _, x := range target {
		for ; cur < x; cur++ {
			ans = append(ans, "Push", "Pop")
		}
		ans = append(ans, "Push")
		cur++
	}
	return
}
