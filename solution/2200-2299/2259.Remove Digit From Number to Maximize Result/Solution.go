func removeDigit(number string, digit byte) string {
	last := -1
	n := len(number)
	for i := range number {
		if number[i] == digit {
			last = i
			if i+1 < n && number[i] < number[i+1] {
				break
			}
		}
	}
	return number[:last] + number[last+1:]
}