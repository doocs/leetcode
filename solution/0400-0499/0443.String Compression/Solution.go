func compress(chars []byte) int {
	i, k, n := 0, 0, len(chars)
	for i < n {
		j := i + 1
		for j < n && chars[j] == chars[i] {
			j++
		}
		chars[k] = chars[i]
		k++
		if j-i > 1 {
			cnt := strconv.Itoa(j - i)
			for _, c := range cnt {
				chars[k] = byte(c)
				k++
			}
		}
		i = j
	}
	return k
}