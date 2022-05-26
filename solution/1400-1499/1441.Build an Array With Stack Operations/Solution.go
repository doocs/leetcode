func buildArray(target []int, n int) []string {
	var ans []string
	cur := 1
	for _, t := range target {
		for i := cur; i <= n; i++ {
			ans = append(ans, "Push")
			if t == i {
				cur = i + 1
				break
			}
			ans = append(ans, "Pop")
		}
	}
	return ans
}