func canPermutePalindrome(s string) bool {
	counter := make(map[rune]int)
	for _, c := range s {
		counter[c]++
	}
	oddCnt := 0
	for _, e := range counter {
		oddCnt += e % 2
	}
	return oddCnt < 2
}