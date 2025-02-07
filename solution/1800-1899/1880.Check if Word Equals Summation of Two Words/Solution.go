func isSumEqual(firstWord string, secondWord string, targetWord string) bool {
	f := func(s string) (ans int) {
		for _, c := range s {
			ans = ans*10 + int(c-'a')
		}
		return
	}
	return f(firstWord)+f(secondWord) == f(targetWord)
}
