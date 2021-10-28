func longestPalindrome(s string) int {
	counter := make([]int, 128)
	for _, c := range s {
		counter[c]++
	}
	oddCnt := 0
	for _, e := range counter {
		oddCnt += e % 2
	}
	n := len(s)
	if oddCnt == 0 {
		return n
	}
	return n - oddCnt + 1
}