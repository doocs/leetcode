# [981. Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store)

[中文文档](/solution/0900-0999/0981.Time%20Based%20Key-Value%20Store/README.md)

## Description

<p>Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key&#39;s value at a certain timestamp.</p>

<p>Implement the <code>TimeMap</code> class:</p>

<ul>
	<li><code>TimeMap()</code> Initializes the object of the data structure.</li>
	<li><code>void set(String key, String value, int timestamp)</code> Stores the key <code>key</code> with the value <code>value </code>at the given time <code>timestamp</code>.</li>
	<li><code>String get(String key, int timestamp)</code> Returns a value such that <code>set</code> was called previously, with <code>timestamp_prev &lt;= timestamp</code>. If there are multiple such values, it returns the value associated with the largest <code>timestamp_prev</code>. If there are no values, it returns <code>&quot;&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TimeMap&quot;, &quot;set&quot;, &quot;get&quot;, &quot;get&quot;, &quot;set&quot;, &quot;get&quot;, &quot;get&quot;]
[[], [&quot;foo&quot;, &quot;bar&quot;, 1], [&quot;foo&quot;, 1], [&quot;foo&quot;, 3], [&quot;foo&quot;, &quot;bar2&quot;, 4], [&quot;foo&quot;, 4], [&quot;foo&quot;, 5]]
<strong>Output</strong>
[null, null, &quot;bar&quot;, &quot;bar&quot;, null, &quot;bar2&quot;, &quot;bar2&quot;]

<strong>Explanation</strong>
TimeMap timeMap = new TimeMap();
timeMap.set(&quot;foo&quot;, &quot;bar&quot;, 1);  // store the key &quot;foo&quot; and value &quot;bar&quot; along with timestamp = 1.
timeMap.get(&quot;foo&quot;, 1);         // return &quot;bar&quot;
timeMap.get(&quot;foo&quot;, 3);         // return &quot;bar&quot;, since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is &quot;bar&quot;.
timeMap.set(&quot;foo&quot;, &quot;bar2&quot;, 4); // store the key &quot;foo&quot; and value &quot;bar2&quot; along with timestamp = 4.
timeMap.get(&quot;foo&quot;, 4);         // return &quot;bar2&quot;
timeMap.get(&quot;foo&quot;, 5);         // return &quot;bar2&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= key.length, value.length &lt;= 100</code></li>
	<li><code>key</code> and <code>value</code> consist of lowercase English letters and digits.</li>
	<li><code>1 &lt;= timestamp &lt;= 10<sup>7</sup></code></li>
	<li>All the timestamps <code>timestamp</code> of <code>set</code> are strictly increasing.</li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls will be made to <code>set</code> and <code>get</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class TimeMap:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.ktv = defaultdict(list)

    def set(self, key: str, value: str, timestamp: int) -> None:
        self.ktv[key].append((timestamp, value))

    def get(self, key: str, timestamp: int) -> str:
        if key not in self.ktv:
            return ''
        tv = self.ktv[key]
        i = bisect_right(tv, (timestamp, chr(127)))
        return tv[i - 1][1] if i else ''


# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)
```

### **Java**

```java
class TimeMap {
    private Map<String, TreeMap<Integer, String>> ktv;

    /** Initialize your data structure here. */
    public TimeMap() {
        ktv = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        ktv.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!ktv.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> tv = ktv.get(key);
        Integer t = tv.floorKey(timestamp);
        return t == null ? "" : tv.get(t);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
```

### **Go**

Because timestamp is always increasing, you can use binary search to quickly find the value

```go
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
```

### **...**

```

```

<!-- tabs:end -->
