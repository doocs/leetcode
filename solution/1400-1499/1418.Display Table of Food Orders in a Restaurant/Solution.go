func displayTable(orders [][]string) [][]string {
	tables := make(map[int]bool)
	foods := make(map[string]bool)
	mp := make(map[string]int)
	for _, order := range orders {
		table, food := order[1], order[2]
		t, _ := strconv.Atoi(table)
		tables[t] = true
		foods[food] = true
		key := table + "." + food
		mp[key] += 1
	}
	var t []int
	var f []string
	for i := range tables {
		t = append(t, i)
	}
	for i := range foods {
		f = append(f, i)
	}
	sort.Ints(t)
	sort.Strings(f)
	var res [][]string
	var title []string
	title = append(title, "Table")
	for _, e := range f {
		title = append(title, e)
	}
	res = append(res, title)
	for _, table := range t {
		var tmp []string
		tmp = append(tmp, strconv.Itoa(table))
		for _, food := range f {
			tmp = append(tmp, strconv.Itoa(mp[strconv.Itoa(table)+"."+food]))
		}
		res = append(res, tmp)
	}
	return res
}