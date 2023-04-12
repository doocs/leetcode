type UndergroundSystem struct {
	ts map[int]pair
	d  map[station][2]int
}

func Constructor() UndergroundSystem {
	return UndergroundSystem{
		ts: make(map[int]pair),
		d:  make(map[station][2]int),
	}
}

func (this *UndergroundSystem) CheckIn(id int, stationName string, t int) {
	this.ts[id] = pair{t, stationName}
}

func (this *UndergroundSystem) CheckOut(id int, stationName string, t int) {
	p := this.ts[id]
	s := station{p.a, stationName}
	if _, ok := this.d[s]; !ok {
		this.d[s] = [2]int{t - p.t, 1}
	} else {
		this.d[s] = [2]int{this.d[s][0] + t - p.t, this.d[s][1] + 1}
	}

}

func (this *UndergroundSystem) GetAverageTime(startStation string, endStation string) float64 {
	s := station{startStation, endStation}
	return float64(this.d[s][0]) / float64(this.d[s][1])
}

type station struct {
	a string
	b string
}

type pair struct {
	t int
	a string
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * obj := Constructor();
 * obj.CheckIn(id,stationName,t);
 * obj.CheckOut(id,stationName,t);
 * param_3 := obj.GetAverageTime(startStation,endStation);
 */