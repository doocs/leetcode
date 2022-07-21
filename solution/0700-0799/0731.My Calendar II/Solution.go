type MyCalendarTwo struct {
	*redblacktree.Tree
}

func Constructor() MyCalendarTwo {
	return MyCalendarTwo{redblacktree.NewWithIntComparator()}
}

func (this *MyCalendarTwo) Book(start int, end int) bool {
	add := func(key, val int) {
		if v, ok := this.Get(key); ok {
			this.Put(key, v.(int)+val)
		} else {
			this.Put(key, val)
		}
	}
	add(start, 1)
	add(end, -1)
	s := 0
	it := this.Iterator()
	for it.Next() {
		s += it.Value().(int)
		if s > 2 {
			add(start, -1)
			add(end, 1)
			return false
		}
	}
	return true
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(start,end);
 */