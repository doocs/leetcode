func countSeniors(details []string) (ans int) {
	for _, x := range details {
		age, _ := strconv.Atoi(x[11:13])
		if age > 60 {
			ans++
		}
	}
	return
}