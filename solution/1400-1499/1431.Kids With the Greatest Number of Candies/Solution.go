func kidsWithCandies(candies []int, extraCandies int) (ans []bool) {
	mx := slices.Max(candies)
	for _, candy := range candies {
		ans = append(ans, candy+extraCandies >= mx)
	}
	return
}