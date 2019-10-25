func strStr(haystack string, needle string) int {
	switch {
	case len(needle) == 0:
		return 0
	case len(needle) > len(haystack):
		return -1
	case len(needle) == len(haystack):
		if needle == haystack {
			return 0
		}
		return -1
	}
	cursor := 0
	for i := 0; i < len(haystack); i++ {
		if haystack[i] == needle[cursor] {
			cursor++
			if cursor == len(needle) {
				return i - cursor + 1
			}
		} else {
			i -= cursor
			cursor = 0
		}
	}
	return -1
}
