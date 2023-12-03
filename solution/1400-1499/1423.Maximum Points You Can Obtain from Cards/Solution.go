func maxScore(cardPoints []int, k int) int {
	n := len(cardPoints)
	s := 0
	for _, x := range cardPoints[n-k:] {
		s += x
	}
	ans := s
	for i := 0; i < k; i++ {
		s += cardPoints[i] - cardPoints[n-k+i]
		ans = max(ans, s)
	}
	return ans
}