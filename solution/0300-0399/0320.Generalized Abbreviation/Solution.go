func generateAbbreviations(word string) (ans []string) {
	n := len(word)
	for i := 0; i < 1<<n; i++ {
		s := &strings.Builder{}
		cnt := 0
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				cnt++
			} else {
				if cnt > 0 {
					s.WriteString(strconv.Itoa(cnt))
					cnt = 0
				}
				s.WriteByte(word[j])
			}
		}
		if cnt > 0 {
			s.WriteString(strconv.Itoa(cnt))
		}
		ans = append(ans, s.String())
	}
	return
}