func finalValueAfterOperations(operations []string) (ans int) {
	for _, s := range operations {
		if s[1] == '+' {
			ans += 1
		} else {
			ans -= 1
		}
	}
	return
}