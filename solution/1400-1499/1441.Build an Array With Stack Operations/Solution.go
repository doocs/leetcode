func buildArray(target []int, n int) []string {
	cur := 0
	ans := []string{}
	for _, v := range target {
		for cur = cur + 1; cur < v; cur++ {
			ans = append(ans, "Push", "Pop")
		}
		ans = append(ans, "Push")
	}
	return ans
}