func maxConsecutiveAnswers(answerKey string, k int) int {
	f := func(c byte) int {
		var ans, cnt, j int
		for i := range answerKey {
			if answerKey[i] == c {
				cnt++
			}
			for cnt > k {
				if answerKey[j] == c {
					cnt--
				}
				j++
			}
			ans = max(ans, i-j+1)
		}
		return ans
	}
	return max(f('T'), f('F'))
}