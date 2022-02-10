func canPermutePalindrome(s string) bool {
	counter := make([]int, 26)
	for i := range s {
		counter[s[i]-'a']++
	}
	oddCnt := 0
	for _, cnt := range counter {
		oddCnt += cnt % 2
	}
	return oddCnt < 2
}