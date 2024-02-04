func minimumTimeToInitialState(word string, k int) int {
	n := len(word)
	for i := 1; i <= 10000; i++ {
		re := i * k
		if re >= n {
			return i
		}
		str := word[re:]
		flag := true
		for j := 0; j < len(str); j++ {
			if str[j] != word[j] {
				flag = false
				break
			}
		}
		if flag {
			return i
		}
	}
	return 0
}
