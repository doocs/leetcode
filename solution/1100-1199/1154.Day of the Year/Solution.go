func dayOfYear(date string) (ans int) {
	y, _ := strconv.Atoi(date[:4])
	m, _ := strconv.Atoi(date[5:7])
	d, _ := strconv.Atoi(date[8:])
	days := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	if y%400 == 0 || (y%4 == 0 && y%100 != 0) {
		days[1] = 29
	}
	ans += d
	for _, v := range days[:m-1] {
		ans += v
	}
	return
}