func decodeCiphertext(encodedText string, rows int) string {
	ans := []byte{}
	cols := len(encodedText) / rows
	for j := 0; j < cols; j++ {
		for x, y := 0, j; x < rows && y < cols; x, y = x+1, y+1 {
			ans = append(ans, encodedText[x*cols+y])
		}
	}
	for len(ans) > 0 && ans[len(ans)-1] == ' ' {
		ans = ans[:len(ans)-1]
	}
	return string(ans)
}