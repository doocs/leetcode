func countKConstraintSubstrings(s string, k int) (ans int) {
	cnt0, cnt1, l := 0, 0, 0
	for r, c := range s {
		x := int(c - '0')
		cnt0 += x ^ 1
		cnt1 += x
		for cnt0 > k && cnt1 > k {
			y := int(s[l] - '0')
			cnt0 -= y ^ 1
			cnt1 -= y
			l++
		}
		ans += r - l + 1
	}
	return
}
