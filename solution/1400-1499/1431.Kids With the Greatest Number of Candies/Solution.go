func kidsWithCandies(candies []int, extraCandies int) []bool {
	mx := 0
	for _, candy := range candies {
		mx = max(mx, candy)
	}
	var res []bool
	for _, candy := range candies {
		res = append(res, candy+extraCandies >= mx)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}