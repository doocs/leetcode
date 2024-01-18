const MX = 1e5
const MOD = 1e9 + 7

var fac [MX + 1]int

func init() {
	fac[0] = 1
	for i := 1; i <= MX; i++ {
		fac[i] = fac[i-1] * i % MOD
	}
}

func qpow(a, n int) int {
	ans := 1
	for n > 0 {
		if n&1 == 1 {
			ans = (ans * a) % MOD
		}
		a = (a * a) % MOD
		n >>= 1
	}
	return ans
}

func numberOfSequence(n int, sick []int) int {
	m := len(sick)
	nums := make([]int, m+1)

	nums[0] = sick[0]
	nums[m] = n - sick[m-1] - 1
	for i := 1; i < m; i++ {
		nums[i] = sick[i] - sick[i-1] - 1
	}

	s := 0
	for _, x := range nums {
		s += x
	}
	ans := fac[s]
	for _, x := range nums {
		if x > 0 {
			ans = ans * qpow(fac[x], MOD-2) % MOD
		}
	}
	for i := 1; i < len(nums)-1; i++ {
		if nums[i] > 1 {
			ans = ans * qpow(2, nums[i]-1) % MOD
		}
	}
	return ans
}