func findString(words []string, s string) int {
	left, right := 0, len(words)-1
	for left < right {
		mid := (left + right) >> 1
		for left < mid && words[mid] == "" {
			mid--
		}
		if s <= words[mid] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if words[left] == s {
		return left
	}
	return -1
}