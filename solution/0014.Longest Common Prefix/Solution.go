func longestCommonPrefix(strs []string) string {
	result := ""
	for i, v := range strs {
		if i == 0 {
			result = v
		}
		index := 0
		for index < len(result) && index < len(v) && result[index] == v[index] {
			index++
		}
		result = result[:index]
	}
	return result
}