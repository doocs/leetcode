func reformatNumber(number string) string {
	number = strings.ReplaceAll(number, " ", "")
	number = strings.ReplaceAll(number, "-", "")
	n := len(number)
	ans := []string{}
	for i := 0; i < n/3; i++ {
		ans = append(ans, number[i*3:i*3+3])
	}
	if n%3 == 1 {
		ans[len(ans)-1] = ans[len(ans)-1][:2]
		ans = append(ans, number[n-2:])
	} else if n%3 == 2 {
		ans = append(ans, number[n-2:])
	}
	return strings.Join(ans, "-")
}