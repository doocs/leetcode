type LogSystem struct {
	logs []pair
	d    map[string]int
}

func Constructor() LogSystem {
	d := map[string]int{
		"Year":   4,
		"Month":  7,
		"Day":    10,
		"Hour":   13,
		"Minute": 16,
		"Second": 19,
	}
	return LogSystem{[]pair{}, d}
}

func (this *LogSystem) Put(id int, timestamp string) {
	this.logs = append(this.logs, pair{id, timestamp})
}

func (this *LogSystem) Retrieve(start string, end string, granularity string) (ans []int) {
	i := this.d[granularity]
	s, e := start[:i], end[:i]
	for _, log := range this.logs {
		t := log.ts[:i]
		if s <= t && t <= e {
			ans = append(ans, log.id)
		}
	}
	return
}

type pair struct {
	id int
	ts string
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Put(id,timestamp);
 * param_2 := obj.Retrieve(start,end,granularity);
 */