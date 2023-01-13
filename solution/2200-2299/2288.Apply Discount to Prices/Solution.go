func discountPrices(sentence string, discount int) string {
	words := strings.Split(sentence, " ")
	for i, w := range words {
		if w[0] == '$' {
			if v, err := strconv.Atoi(w[1:]); err == nil {
				words[i] = fmt.Sprintf("$%.2f", float64(v*(100-discount))/100)
			}
		}
	}
	return strings.Join(words, " ")
}