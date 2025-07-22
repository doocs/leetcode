func phonePrefix(numbers []string) bool {
	sort.Slice(numbers, func(i, j int) bool {
		return len(numbers[i]) < len(numbers[j])
	})
	for i, s := range numbers {
		for _, t := range numbers[:i] {
			if strings.HasPrefix(s, t) {
				return false
			}
		}
	}
	return true
}
