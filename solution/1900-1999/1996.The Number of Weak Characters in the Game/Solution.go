func numberOfWeakCharacters(properties [][]int) int {
	sort.Slice(properties, func(i, j int) bool {
		if properties[i][0] == properties[j][0] {
			return properties[i][1] < properties[j][1]
		}
		return properties[i][0] > properties[j][0]
	})
	ans, mx := 0, 0
	for _, p := range properties {
		if mx > p[1] {
			ans++
		} else {
			mx = p[1]
		}
	}
	return ans
}