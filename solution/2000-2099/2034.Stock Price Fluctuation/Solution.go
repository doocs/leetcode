type StockPrice struct {
	lastTs  int
	mp      map[int]int
	counter *redblacktree.Tree
}

func Constructor() StockPrice {
	return StockPrice{
		mp:      make(map[int]int),
		counter: redblacktree.NewWithIntComparator(),
	}
}

func (this *StockPrice) Update(timestamp int, price int) {
	if timestamp > this.lastTs {
		this.lastTs = timestamp
	}
	if old, ok := this.mp[timestamp]; ok {
		cnt := getInt(this.counter, old)
		if cnt == 1 {
			this.counter.Remove(old)
		} else {
			this.counter.Put(old, cnt-1)
		}
	}
	this.mp[timestamp] = price
	this.counter.Put(price, getInt(this.counter, price)+1)
}

func (this *StockPrice) Current() int {
	return this.mp[this.lastTs]
}

func (this *StockPrice) Maximum() int {
	return this.counter.Right().Key.(int)
}

func (this *StockPrice) Minimum() int {
	return this.counter.Left().Key.(int)
}

func getInt(rbt *redblacktree.Tree, key int) int {
	val, found := rbt.Get(key)
	if !found {
		return 0
	}
	return val.(int)
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Update(timestamp,price);
 * param_2 := obj.Current();
 * param_3 := obj.Maximum();
 * param_4 := obj.Minimum();
 */
