func checkPalindromeFormation(a string, b string) bool {
	return check1(a, b) || check1(b, a)
}

func check1(a, b string) bool {
	i, j := 0, len(b)-1
	for i < j && a[i] == b[j] {
		i++
		j--
	}
	return i >= j || check2(a, i, j) || check2(b, i, j)
}

func check2(a string, i, j int) bool {
	for i < j && a[i] == a[j] {
		i++
		j--
	}
	return i >= j
}
