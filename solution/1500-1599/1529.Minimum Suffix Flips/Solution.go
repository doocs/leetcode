func minFlips(target string) int {
	ans := 0
	for _, c := range target {
		v := int(c - '0')
		if ((ans & 1) ^ v) != 0 {
			ans++
		}
	}
	return ans
}