func minOperations(logs []string) int {
	ans := 0
	for _, v := range logs {
		if v == "../" {
			if ans > 0 {
				ans--
			}
		} else if v[0] != '.' {
			ans++
		}
	}
	return ans
}