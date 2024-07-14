var ps [2 * 100000]int64

func init() {
	for i := 1; i <= 100000; i++ {
		s := strconv.Itoa(i)
		t1 := reverseString(s)
		t2 := reverseString(s[:len(s)-1])
		ps[2*i-2], _ = strconv.ParseInt(s+t1, 10, 64)
		ps[2*i-1], _ = strconv.ParseInt(s+t2, 10, 64)
	}
}

func reverseString(s string) string {
	cs := []rune(s)
	for i, j := 0, len(cs)-1; i < j; i, j = i+1, j-1 {
		cs[i], cs[j] = cs[j], cs[i]
	}
	return string(cs)
}

func superpalindromesInRange(left string, right string) (ans int) {
	l, _ := strconv.ParseInt(left, 10, 64)
	r, _ := strconv.ParseInt(right, 10, 64)
	isPalindrome := func(x int64) bool {
		var y int64
		for t := x; t > 0; t /= 10 {
			y = y*10 + int64(t%10)
		}
		return x == y
	}
	for _, p := range ps {
		x := p * p
		if x >= l && x <= r && isPalindrome(x) {
			ans++
		}
	}
	return
}