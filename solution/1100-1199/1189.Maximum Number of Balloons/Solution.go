func maxNumberOfBalloons(text string) int {
	cnt := [26]int{}
	for _, c := range text {
		cnt[c-'a']++
	}
	cnt['l'-'a'] >>= 1
	cnt['o'-'a'] >>= 1
	ans := 1 << 30
	for _, c := range "balon" {
		if x := cnt[c-'a']; ans > x {
			ans = x
		}
	}
	return ans
}