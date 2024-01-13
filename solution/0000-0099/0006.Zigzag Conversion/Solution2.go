func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	n := len(s)
	ans := make([]byte, n)
	step := 2*numRows - 2
	count := 0
	for i := 0; i < numRows; i++ {
		for j := 0; j+i < n; j += step {
			ans[count] = s[i+j]
			count++
			if i != 0 && i != numRows-1 && j+step-i < n {
				ans[count] = s[j+step-i]
				count++
			}
		}
	}
	return string(ans)
}