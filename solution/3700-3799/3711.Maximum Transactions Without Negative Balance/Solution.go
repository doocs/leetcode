func maxTransactions(transactions []int) int {
	tm := redblacktree.New[int, int]()
	ans := len(transactions)
	var s int64

	for _, x := range transactions {
		s += int64(x)
		if cnt, ok := tm.Get(x); ok {
			tm.Put(x, cnt+1)
		} else {
			tm.Put(x, 1)
		}

		for s < 0 {
			it := tm.Iterator()
			it.Begin()
			it.Next()
			y := it.Key()
			s -= int64(y)
			ans--

			cnt, _ := tm.Get(y)
			if cnt == 1 {
				tm.Remove(y)
			} else {
				tm.Put(y, cnt-1)
			}
		}
	}
	return ans
}
