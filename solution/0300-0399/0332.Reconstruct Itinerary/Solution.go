func findItinerary(tickets [][]string) (ans []string) {
	sort.Slice(tickets, func(i, j int) bool {
		return tickets[i][0] > tickets[j][0] || (tickets[i][0] == tickets[j][0] && tickets[i][1] > tickets[j][1])
	})
	g := make(map[string][]string)
	for _, ticket := range tickets {
		g[ticket[0]] = append(g[ticket[0]], ticket[1])
	}
	var dfs func(f string)
	dfs = func(f string) {
		for len(g[f]) > 0 {
			t := g[f][len(g[f])-1]
			g[f] = g[f][:len(g[f])-1]
			dfs(t)
		}
		ans = append(ans, f)
	}
	dfs("JFK")
	for i := 0; i < len(ans)/2; i++ {
		ans[i], ans[len(ans)-1-i] = ans[len(ans)-1-i], ans[i]
	}
	return
}