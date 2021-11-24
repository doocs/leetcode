func originalDigits(s string) string {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	cnt := make([]int, 10)
	cnt[0] = counter['z'-'a']
	cnt[2] = counter['w'-'a']
	cnt[4] = counter['u'-'a']
	cnt[6] = counter['x'-'a']
	cnt[8] = counter['g'-'a']

	cnt[3] = counter['h'-'a'] - cnt[8]
	cnt[5] = counter['f'-'a'] - cnt[4]
	cnt[7] = counter['s'-'a'] - cnt[6]

	cnt[1] = counter['o'-'a'] - cnt[0] - cnt[2] - cnt[4]
	cnt[9] = counter['i'-'a'] - cnt[5] - cnt[6] - cnt[8]

	ans := []byte{}
	for i, c := range cnt {
		ans = append(ans, bytes.Repeat([]byte{byte('0' + i)}, c)...)
	}
	return string(ans)
}