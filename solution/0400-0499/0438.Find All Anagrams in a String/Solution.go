func findAnagrams(s string, p string) []int {
	counter := make([]int, 26)
	for _, c := range p {
		counter[c-'a']++
	}
	var ans []int
	left, right := 0, 0
	t := make([]int, 26)
	for right < len(s) {
		i := s[right] - 'a'
		t[i]++
		for t[i] > counter[i] {
			t[s[left]-'a']--
			left++
		}
		if right-left+1 == len(p) {
			ans = append(ans, left)
		}
		right++
	}
	return ans
}