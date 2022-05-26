func reverseVowels(s string) string {
	left, right := 0, len(s)-1
	a := []byte(s)
	for left < right {
		for left < right && !isVowel(a[left]) {
			left++
		}
		for left < right && !isVowel(a[right]) {
			right--
		}
		if left != right && isVowel(a[left]) && isVowel(a[right]) {
			a[left], a[right] = a[right], a[left]
			left++
			right--
		}
	}
	return string(a)
}

func isVowel(b byte) bool {
	return b == 'a' || b == 'e' || b == 'i' || b == 'o' || b == 'u' ||
		b == 'A' || b == 'E' || b == 'I' || b == 'O' || b == 'U'
}
