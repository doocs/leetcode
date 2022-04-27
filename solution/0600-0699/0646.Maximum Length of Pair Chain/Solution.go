func findLongestChain(pairs [][]int) int {
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][1] < pairs[j][1]
	})
	ans, cur := 0, math.MinInt32
	for _, p := range pairs {
		if cur < p[0] {
			cur = p[1]
			ans++
		}
	}
	return ans
}