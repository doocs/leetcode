func canThreePartsEqualSum(arr []int) bool {
	s := 0
	for _, v := range arr {
		s += v
	}
	if s%3 != 0 {
		return false
	}
	i, j := 0, len(arr)-1
	a, b := 0, 0
	for i < len(arr) {
		a += arr[i]
		if a == s/3 {
			break
		}
		i++
	}
	for j >= 0 {
		b += arr[j]
		if b == s/3 {
			break
		}
		j--
	}
	return i < j-1
}