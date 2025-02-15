func canPermutePalindrome(s string) bool {
	cnt := map[rune]int{}
	for _, c := range s {
		cnt[c]++
	}
	sum := 0
	for _, v := range cnt {
		sum += v & 1
	}
	return sum < 2
}
