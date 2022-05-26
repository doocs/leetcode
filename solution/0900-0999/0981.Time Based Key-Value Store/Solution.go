type pair struct {
	timestamp int
	value     string
}

type TimeMap struct {
	data map[string][]pair
}

func Constructor() TimeMap {
	return TimeMap{data: make(map[string][]pair)}
}

func (m *TimeMap) Set(key string, value string, timestamp int) {
	m.data[key] = append(m.data[key], pair{timestamp, value})
}

func (m *TimeMap) Get(key string, timestamp int) string {
	pairs := m.data[key]
	// sort.Search return the smallest index i in [0, n) at which f(i) is true
	i := sort.Search(len(pairs), func(i int) bool {
		return pairs[i].timestamp > timestamp
	})
	if i > 0 {
		return pairs[i-1].value
	}
	return ""
}
