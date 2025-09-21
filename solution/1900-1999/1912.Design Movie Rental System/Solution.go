type MovieRentingSystem struct {
	available map[int]*treeset.Set // movie -> (price, shop)
	priceMap  map[int64]int
	rented    *treeset.Set // (price, shop, movie)
}

func Constructor(n int, entries [][]int) MovieRentingSystem {
	// comparator for (price, shop)
	cmpAvail := func(a, b any) int {
		x := a.([2]int)
		y := b.([2]int)
		if x[0] != y[0] {
			return x[0] - y[0]
		}
		return x[1] - y[1]
	}
	// comparator for (price, shop, movie)
	cmpRented := func(a, b any) int {
		x := a.([3]int)
		y := b.([3]int)
		if x[0] != y[0] {
			return x[0] - y[0]
		}
		if x[1] != y[1] {
			return x[1] - y[1]
		}
		return x[2] - y[2]
	}

	mrs := MovieRentingSystem{
		available: make(map[int]*treeset.Set),
		priceMap:  make(map[int64]int),
		rented:    treeset.NewWith(cmpRented),
	}

	for _, e := range entries {
		shop, movie, price := e[0], e[1], e[2]
		if _, ok := mrs.available[movie]; !ok {
			mrs.available[movie] = treeset.NewWith(cmpAvail)
		}
		mrs.available[movie].Add([2]int{price, shop})
		mrs.priceMap[f(shop, movie)] = price
	}

	return mrs
}

func (this *MovieRentingSystem) Search(movie int) []int {
	res := []int{}
	if _, ok := this.available[movie]; !ok {
		return res
	}
	it := this.available[movie].Iterator()
	it.Begin()
	cnt := 0
	for it.Next() && cnt < 5 {
		pair := it.Value().([2]int)
		res = append(res, pair[1])
		cnt++
	}
	return res
}

func (this *MovieRentingSystem) Rent(shop int, movie int) {
	price := this.priceMap[f(shop, movie)]
	this.available[movie].Remove([2]int{price, shop})
	this.rented.Add([3]int{price, shop, movie})
}

func (this *MovieRentingSystem) Drop(shop int, movie int) {
	price := this.priceMap[f(shop, movie)]
	this.rented.Remove([3]int{price, shop, movie})
	this.available[movie].Add([2]int{price, shop})
}

func (this *MovieRentingSystem) Report() [][]int {
	res := [][]int{}
	it := this.rented.Iterator()
	it.Begin()
	cnt := 0
	for it.Next() && cnt < 5 {
		t := it.Value().([3]int)
		res = append(res, []int{t[1], t[2]})
		cnt++
	}
	return res
}

func f(shop, movie int) int64 {
	return (int64(shop) << 30) | int64(movie)
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * obj := Constructor(n, entries);
 * param_1 := obj.Search(movie);
 * obj.Rent(shop,movie);
 * obj.Drop(shop,movie);
 * param_4 := obj.Report();
 */
