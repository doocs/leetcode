func successfulPairs(spells []int, potions []int, success int64) []int {
	sort.Ints(potions)
	m := len(potions)
	var ans []int
	for _, s := range spells {
		left, right := 0, m
		for left < right {
			mid := (left + right) >> 1
			if int64(s*potions[mid]) >= success {
				right = mid
			} else {
				left = mid + 1
			}
		}
		ans = append(ans, m-left)
	}
	return ans
}