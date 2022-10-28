func countMatches(items [][]string, ruleKey string, ruleValue string) (ans int) {
	i := map[byte]int{'t': 0, 'c': 1, 'n': 2}[ruleKey[0]]
	for _, v := range items {
		if v[i] == ruleValue {
			ans++
		}
	}
	return
}