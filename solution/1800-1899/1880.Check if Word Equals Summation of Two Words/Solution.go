func isSumEqual(firstWord string, secondWord string, targetWord string) bool {
	f := func(s string) int {
		res := 0
		for _, c := range s {
			res = res*10 + int(c-'a')
		}
		return res
	}
	return f(firstWord)+f(secondWord) == f(targetWord)
}