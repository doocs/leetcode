func computeSimilarities(docs [][]int) []string {
	d := map[int][]int{}
	for i, v := range docs {
		for _, x := range v {
			d[x] = append(d[x], i)
		}
	}
	type pair struct{ i, j int }
	cnt := map[pair]int{}
	for _, ids := range d {
		n := len(ids)
		for i := 0; i < n; i++ {
			for j := i + 1; j < n; j++ {
				k := pair{ids[i], ids[j]}
				cnt[k]++
			}
		}
	}
	ans := []string{}
	for k, v := range cnt {
		i, j := k.i, k.j
		tot := len(docs[i]) + len(docs[j]) - v
		x := float64(v)/float64(tot) + 1e-9
		ans = append(ans, strconv.Itoa(i)+","+strconv.Itoa(j)+": "+fmt.Sprintf("%.4f", x))
	}
	return ans
}