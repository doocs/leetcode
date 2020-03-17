func countAndSay(n int) string {
	buf := bytes.NewBufferString("1")
	for i := 2; i <= n; i++ {
		s := buf.String()
		c, l := s[0:1], len(s)
		buf.Reset()
		count := 0
		for j := 0; j < l; j++ {
			if c == s[j:j+1] {
				count++
			} else {
				buf.WriteByte(byte(48 + count))
				buf.WriteString(c)
				count = 1
				c = s[j : j+1]
			}
		}
		buf.WriteByte(byte(48 + count))
		buf.WriteString(c)
	}
	return buf.String()
}
