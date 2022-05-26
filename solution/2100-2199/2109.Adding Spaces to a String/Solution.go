func addSpaces(s string, spaces []int) string {
	var ans []byte
	for i, j := 0, 0; i < len(s); i++ {
		if j < len(spaces) && i == spaces[j] {
			ans = append(ans, ' ')
			j++
		}
		ans = append(ans, s[i])
	}
	return string(ans)
}