func convertDateToBinary(date string) string {
	ans := []string{}
	for _, s := range strings.Split(date, "-") {
		x, _ := strconv.Atoi(s)
		ans = append(ans, strconv.FormatUint(uint64(x), 2))
	}
	return strings.Join(ans, "-")
}
