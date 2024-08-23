const m = 50

var cnt [m + 1]int64
var s [m + 1]int64
var p int64 = 1

func init() {
	cnt[0] = 0
	s[0] = 0
	for i := 1; i <= m; i++ {
		cnt[i] = cnt[i-1]*2 + p
		s[i] = s[i-1]*2 + p*(int64(i)-1)
		p *= 2
	}
}

func numIdxAndSum(x int64) (int64, int64) {
	var idx, totalSum int64
	for x > 0 {
		i := 63 - bits.LeadingZeros64(uint64(x))
		idx += cnt[i]
		totalSum += s[i]
		x -= 1 << i
		totalSum += (x + 1) * int64(i)
		idx += x + 1
	}
	return idx, totalSum
}

func f(i int64) int64 {
	l, r := int64(0), int64(1)<<m
	for l < r {
		mid := (l + r + 1) >> 1
		idx, _ := numIdxAndSum(mid)
		if idx < i {
			l = mid
		} else {
			r = mid - 1
		}
	}

	_, totalSum := numIdxAndSum(l)
	idx, _ := numIdxAndSum(l)
	i -= idx
	x := l + 1
	for j := int64(0); j < i; j++ {
		y := x & -x
		totalSum += int64(bits.TrailingZeros64(uint64(y)))
		x -= y
	}
	return totalSum
}

func qpow(a, n, mod int64) int64 {
	ans := int64(1) % mod
	a = a % mod
	for n > 0 {
		if n&1 == 1 {
			ans = (ans * a) % mod
		}
		a = (a * a) % mod
		n >>= 1
	}
	return ans
}

func findProductsOfElements(queries [][]int64) []int {
	ans := make([]int, len(queries))
	for i, q := range queries {
		left, right, mod := q[0], q[1], q[2]
		power := f(right+1) - f(left)
		ans[i] = int(qpow(2, power, mod))
	}
	return ans
}
