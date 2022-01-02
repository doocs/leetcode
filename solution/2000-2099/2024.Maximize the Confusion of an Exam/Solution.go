func maxConsecutiveAnswers(answerKey string, k int) int {
	get := func(c byte, k int) int {
		l, r := -1, -1
		for r < len(answerKey)-1 {
			r++
			if answerKey[r] == c {
				k--
			}
			if k < 0 {
				l++
				if answerKey[l] == c {
					k++
				}
			}
		}
		return r - l
	}
	return max(get('T', k), get('F', k))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}