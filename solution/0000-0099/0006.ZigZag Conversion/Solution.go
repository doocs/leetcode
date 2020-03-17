func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	length := len(s)
	result := make([]byte, length)
	step := 2 * numRows - 2
	count := 0
	for i := 0; i < numRows; i++ {
		for j := 0; j + i < length; j += step {
			result[count] = s[i+j]
			count++
			if i != 0 && i != numRows - 1 && j + step - i < length {
				result[count] = s[j+step-i]
				count++
			}
		}
	}
	return string(result)
}