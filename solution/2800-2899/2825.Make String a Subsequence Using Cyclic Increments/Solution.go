func canMakeSubsequence(str1 string, str2 string) bool {
	i, n := 0, len(str2)
	for _, c := range str1 {
		d := byte('a')
		if c != 'z' {
			d = byte(c + 1)
		}
		if i < n && (str2[i] == byte(c) || str2[i] == d) {
			i++
		}
	}
	return i == n
}