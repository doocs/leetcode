var p []int

func init() {
	N := 1 << 14
	for i := 0; i < N; i++ {
		s := strconv.FormatInt(int64(i), 2)
		if isPalindrome(s) {
			p = append(p, i)
		}
	}
}

func isPalindrome(s string) bool {
	runes := []rune(s)
	for i := 0; i < len(runes)/2; i++ {
		if runes[i] != runes[len(runes)-1-i] {
			return false
		}
	}
	return true
}

func minOperations(nums []int) []int {
	ans := make([]int, len(nums))
	for k, x := range nums {
		i := sort.SearchInts(p, x)
		t := math.MaxInt32
		if i < len(p) {
			t = p[i] - x
		}
		if i >= 1 {
			t = min(t, x-p[i-1])
		}
		ans[k] = t
	}
	return ans
}
