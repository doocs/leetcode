func countDigitOccurrences(nums []int, digit int) (ans int) {
	for _, x := range nums {
		for ; x > 0; x /= 10 {
			if x%10 == digit {
				ans++
			}
		}
	}
	return
}
