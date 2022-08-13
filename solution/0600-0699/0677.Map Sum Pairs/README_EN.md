# [677. Map Sum Pairs](https://leetcode.com/problems/map-sum-pairs)

[中文文档](/solution/0600-0699/0677.Map%20Sum%20Pairs/README.md)

## Description

<p>Design a map that allows you to do the following:</p>

<ul>
	<li>Maps a string key to a given value.</li>
	<li>Returns the sum of the values that have a key with a prefix equal to a given string.</li>
</ul>

<p>Implement the <code>MapSum</code> class:</p>

<ul>
	<li><code>MapSum()</code> Initializes the <code>MapSum</code> object.</li>
	<li><code>void insert(String key, int val)</code> Inserts the <code>key-val</code> pair into the map. If the <code>key</code> already existed, the original <code>key-value</code> pair will be overridden to the new one.</li>
	<li><code>int sum(string prefix)</code> Returns the sum of all the pairs&#39; value whose <code>key</code> starts with the <code>prefix</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MapSum&quot;, &quot;insert&quot;, &quot;sum&quot;, &quot;insert&quot;, &quot;sum&quot;]
[[], [&quot;apple&quot;, 3], [&quot;ap&quot;], [&quot;app&quot;, 2], [&quot;ap&quot;]]
<strong>Output</strong>
[null, null, 3, null, 5]

<strong>Explanation</strong>
MapSum mapSum = new MapSum();
mapSum.insert(&quot;apple&quot;, 3);  
mapSum.sum(&quot;ap&quot;);           // return 3 (<u>ap</u>ple = 3)
mapSum.insert(&quot;app&quot;, 2);    
mapSum.sum(&quot;ap&quot;);           // return 5 (<u>ap</u>ple + <u>ap</u>p = 3 + 2 = 5)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= key.length, prefix.length &lt;= 50</code></li>
	<li><code>key</code> and <code>prefix</code> consist of only lowercase English letters.</li>
	<li><code>1 &lt;= val &lt;= 1000</code></li>
	<li>At most <code>50</code> calls will be made to <code>insert</code> and <code>sum</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class MapSum:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.data = defaultdict(int)
        self.t = defaultdict(int)

    def insert(self, key: str, val: int) -> None:
        old = self.t[key]
        self.t[key] = val
        for i in range(1, len(key) + 1):
            self.data[key[:i]] += val - old

    def sum(self, prefix: str) -> int:
        return self.data[prefix]


# Your MapSum object will be instantiated and called as such:
# obj = MapSum()
# obj.insert(key,val)
# param_2 = obj.sum(prefix)
```

### **Java**

```java
class MapSum {
    private Map<String, Integer> data;
    private Map<String, Integer> t;

    /** Initialize your data structure here. */
    public MapSum() {
        data = new HashMap<>();
        t = new HashMap<>();
    }

    public void insert(String key, int val) {
        int old = t.getOrDefault(key, 0);
        t.put(key, val);
        for (int i = 1; i < key.length() + 1; ++i) {
            String k = key.substring(0, i);
            data.put(k, data.getOrDefault(k, 0) + (val - old));
        }
    }

    public int sum(String prefix) {
        return data.getOrDefault(prefix, 0);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
```

### **C++**

```cpp
class MapSum {
public:
    unordered_map<string, int> data;
    unordered_map<string, int> t;

    /** Initialize your data structure here. */
    MapSum() {
    }

    void insert(string key, int val) {
        int old = t[key];
        t[key] = val;
        for (int i = 1; i < key.size() + 1; ++i) {
            string k = key.substr(0, i);
            data[k] += (val - old);
        }
    }

    int sum(string prefix) {
        return data[prefix];
    }
};

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum* obj = new MapSum();
 * obj->insert(key,val);
 * int param_2 = obj->sum(prefix);
 */
```

### **Go**

```go
type MapSum struct {
	data map[string]int
	t    map[string]int
}

/** Initialize your data structure here. */
func Constructor() MapSum {
	return MapSum{
		data: make(map[string]int),
		t:    make(map[string]int),
	}
}

func (this *MapSum) Insert(key string, val int) {
	old := this.t[key]
	this.t[key] = val
	for i := 1; i < len(key)+1; i++ {
		k := key[:i]
		this.data[k] += (val - old)
	}
}

func (this *MapSum) Sum(prefix string) int {
	return this.data[prefix]
}

/**
 * Your MapSum object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(key,val);
 * param_2 := obj.Sum(prefix);
 */
```

### **...**

```

```

<!-- tabs:end -->
