type SQL struct {
	cols   map[string]int
	rows   map[string]map[int][]string
	nextId map[string]int
}

func Constructor(names []string, columns []int) SQL {
	cols := map[string]int{}
	rows := map[string]map[int][]string{}
	nextId := map[string]int{}
	for i, name := range names {
		cols[name] = columns[i]
		rows[name] = map[int][]string{}
		nextId[name] = 1
	}
	return SQL{cols, rows, nextId}
}

func (this *SQL) Ins(name string, row []string) bool {
	c, ok := this.cols[name]
	if !ok || len(row) != c {
		return false
	}
	id := this.nextId[name]
	this.nextId[name] = id + 1
	cp := append([]string(nil), row...)
	this.rows[name][id] = cp
	return true
}

func (this *SQL) Rmv(name string, rowId int) {
	if table, ok := this.rows[name]; ok {
		delete(table, rowId)
	}
}

func (this *SQL) Sel(name string, rowId int, columnId int) string {
	table, ok := this.rows[name]
	if !ok {
		return "<null>"
	}
	row, ok := table[rowId]
	if !ok || columnId < 1 || columnId > len(row) {
		return "<null>"
	}
	return row[columnId-1]
}

func (this *SQL) Exp(name string) []string {
	table, ok := this.rows[name]
	if !ok || len(table) == 0 {
		return []string{}
	}
	ids := make([]int, 0, len(table))
	for id := range table {
		ids = append(ids, id)
	}
	sort.Ints(ids)
	ans := make([]string, 0, len(ids))
	for _, id := range ids {
		s := strconv.Itoa(id)
		for _, cell := range table[id] {
			s += "," + cell
		}
		ans = append(ans, s)
	}
	return ans
}

/**
 * Your SQL object will be instantiated and called as such:
 * obj := Constructor(names, columns);
 * param_1 := obj.Ins(name,row);
 * obj.Rmv(name,rowId);
 * param_3 := obj.Sel(name,rowId,columnId);
 * param_4 := obj.Exp(name);
 */
