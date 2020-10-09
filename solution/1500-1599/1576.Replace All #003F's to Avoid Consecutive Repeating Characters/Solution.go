func modifyString(s string) string {
	data := []byte(" " + s + " ")
	for i, c := range data {
		if c == byte('?') {
			ahead, behind := data[i-1], data[i+1]
			for t := byte('a'); t <= byte('z'); t++ {
				if t != ahead && t != behind {
					data[i] = t
				}
			}
		}
	}
	return string(data[1 : len(data)-1])
}