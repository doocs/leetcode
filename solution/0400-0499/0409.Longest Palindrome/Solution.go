func longestPalindrome(s string) (ans int) {
	cnt := [128]int{}
	for _, c := range s {
		cnt[c]++
	}
	for _, v := range cnt {
		ans += v - (v & 1)
		if ans&1 == 0 && v&1 == 1 {
			ans++
		}
	}
	return
}