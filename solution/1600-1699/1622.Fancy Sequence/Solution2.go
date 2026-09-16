const mod int = 1e9 + 7

func qpow(x, n int) int {
	res := 1
	for n > 0 {
		if n&1 == 1 {
			res = res * x % mod
		}
		x = x * x % mod
		n >>= 1
	}
	return res
}

type Fancy struct {
	nums []int
	a, b int
}

func Constructor() Fancy {
	return Fancy{a: 1}
}

func (f *Fancy) Append(val int) {
	x := (val - f.b + mod) % mod * qpow(f.a, mod-2) % mod
	f.nums = append(f.nums, x)
}

func (f *Fancy) AddAll(inc int) {
	f.b = (f.b + inc) % mod
}

func (f *Fancy) MultAll(m int) {
	f.a = f.a * m % mod
	f.b = f.b * m % mod
}

func (f *Fancy) GetIndex(idx int) int {
	if idx >= len(f.nums) {
		return -1
	}
	return (f.a*f.nums[idx] + f.b) % mod
}
