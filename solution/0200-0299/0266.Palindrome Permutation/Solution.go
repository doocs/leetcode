func canPermutePalindrome(s string) bool {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	n := 0
	for _, v := range cnt {
		n += v & 1
	}
	return n < 2
}