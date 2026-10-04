func nearestPalindromic(n string) string {
	x := new(big.Int)
	x.SetString(n, 10)
	l := len(n)
	ten := big.NewInt(10)
	base := new(big.Int).Exp(ten, big.NewInt(int64(l-1)), nil)
	res := []*big.Int{
		new(big.Int).Sub(new(big.Int).Set(base), big.NewInt(1)),
		new(big.Int).Add(new(big.Int).Mul(new(big.Int).Set(base), ten), big.NewInt(1)),
	}
	left := new(big.Int)
	left.SetString(n[:(l+1)/2], 10)
	for d := int64(-1); d <= 1; d++ {
		i := new(big.Int).Add(left, big.NewInt(d))
		j := new(big.Int).Set(i)
		if l&1 == 1 {
			j.Quo(j, ten)
		}
		for j.Sign() > 0 {
			i.Mul(i, ten)
			i.Add(i, new(big.Int).Mod(j, ten))
			j.Quo(j, ten)
		}
		res = append(res, i)
	}
	var ans *big.Int
	for _, t := range res {
		if t.Cmp(x) == 0 {
			continue
		}
		dist := new(big.Int).Abs(new(big.Int).Sub(t, x))
		if ans == nil {
			ans = new(big.Int).Set(t)
			continue
		}
		best := new(big.Int).Abs(new(big.Int).Sub(ans, x))
		if dist.Cmp(best) < 0 || (dist.Cmp(best) == 0 && t.Cmp(ans) < 0) {
			ans = new(big.Int).Set(t)
		}
	}
	return ans.String()
}
