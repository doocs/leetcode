func betterCompression(compressed string) string {
	cnt := map[byte]int{}
	n := len(compressed)
	for i := 0; i < n; {
		c := compressed[i]
		j := i + 1
		x := 0
		for j < n && compressed[j] >= '0' && compressed[j] <= '9' {
			x = x*10 + int(compressed[j]-'0')
			j++
		}
		cnt[c] += x
		i = j
	}
	ans := strings.Builder{}
	for c := byte('a'); c <= byte('z'); c++ {
		if cnt[c] > 0 {
			ans.WriteByte(c)
			ans.WriteString(strconv.Itoa(cnt[c]))
		}
	}
	return ans.String()
}