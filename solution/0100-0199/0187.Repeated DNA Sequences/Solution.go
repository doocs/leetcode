func findRepeatedDnaSequences(s string) []string {
	hashCode := map[byte]int{'A': 0, 'C': 1, 'G': 2, 'T': 3}
	ans, cnt, left, right := []string{}, map[int]int{}, 0, 0

	sha, multi := 0, int(math.Pow(4, 9))
	for ; right < len(s); right++ {
		sha = sha*4 + hashCode[s[right]]
		if right-left+1 < 10 {
			continue
		}
		cnt[sha]++
		if cnt[sha] == 2 {
			ans = append(ans, s[left:right+1])
		}
		sha, left = sha-multi*hashCode[s[left]], left+1
	}
	return ans
}