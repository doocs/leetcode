func maxConsecutiveAnswers(answerKey string, k int) int {
	f := func(c byte) int {
		l, cnt := 0, 0
		for _, ch := range answerKey {
			if byte(ch) == c {
				cnt++
			}
			if cnt > k {
				if answerKey[l] == c {
					cnt--
				}
				l++
			}
		}
		return len(answerKey) - l
	}
	return max(f('T'), f('F'))
}