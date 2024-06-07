func longestPalindrome(s string) (ans int) {
	cnt := [128]int{}
	for _, c := range s {
		cnt[c]++
	}
	for _, v := range cnt {
		ans += v / 2 * 2
	}
	if ans < len(s) {
		ans++
	}
	return
}