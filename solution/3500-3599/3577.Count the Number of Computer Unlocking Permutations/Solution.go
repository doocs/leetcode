func countPermutations(complexity []int) int {
	mod := int64(1e9 + 7)
	ans := int64(1)
	for i := 1; i < len(complexity); i++ {
		if complexity[i] <= complexity[0] {
			return 0
		}
		ans = ans * int64(i) % mod
	}
	return int(ans)
}
