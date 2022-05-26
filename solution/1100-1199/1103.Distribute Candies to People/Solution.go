func distributeCandies(candies int, num_people int) []int {
	ans := make([]int, num_people)
	for i := 0; candies > 0; i++ {
		ans[i%num_people] += min(candies, i+1)
		candies -= min(candies, i+1)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}