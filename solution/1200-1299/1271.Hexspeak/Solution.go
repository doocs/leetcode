func toHexspeak(num string) string {
	x, _ := strconv.Atoi(num)
	t := strings.ToUpper(fmt.Sprintf("%x", x))
	t = strings.ReplaceAll(t, "0", "O")
	t = strings.ReplaceAll(t, "1", "I")
	for _, c := range t {
		if c >= '2' && c <= '9' {
			return "ERROR"
		}
	}
	return t
}