type MyCalendar struct {
	rbt *redblacktree.Tree
}

func Constructor() MyCalendar {
	return MyCalendar{
		rbt: redblacktree.NewWithIntComparator(),
	}
}

func (this *MyCalendar) Book(startTime int, endTime int) bool {
	if p, ok := this.rbt.Ceiling(startTime + 1); ok && p.Value.(int) < endTime {
		return false
	}
	this.rbt.Put(endTime, startTime)
	return true
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(startTime,endTime);
 */
