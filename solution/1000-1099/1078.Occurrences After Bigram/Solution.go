func findOcurrences(text string, first string, second string) []string {
	words := strings.Split(text, " ")
	var ans []string
	for i := 0; i < len(words)-2; i++ {
		if words[i] == first && words[i+1] == second {
			ans = append(ans, words[i+2])
		}
	}
	return ans
}