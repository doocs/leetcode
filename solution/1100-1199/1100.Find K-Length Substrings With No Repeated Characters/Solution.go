func numKLenSubstrNoRepeats(s string, k int) (ans int) {
	if k > len(s) || k > 26 {
		return 0
	}
	cnt := [128]int{}
	for i, j := 0, 0; i < len(s); i++ {
		cnt[s[i]]++
		for cnt[s[i]] > 1 || i-j+1 > k {
			cnt[s[j]]--
			j++
		}
		if i-j+1 == k {
			ans++
		}
	}
	return
}