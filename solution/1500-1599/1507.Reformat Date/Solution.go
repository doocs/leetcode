func reformatDate(date string) string {
	s := strings.Split(date, " ")
	day, _ := strconv.Atoi(s[0][:len(s[0])-2])
	months := " JanFebMarAprMayJunJulAugSepOctNovDec"
	month := strings.Index(months, s[1])/3 + 1
	year, _ := strconv.Atoi(s[2])
	return fmt.Sprintf("%d-%02d-%02d", year, month, day)
}