# [981. 基于时间的键值存储](https://leetcode.cn/problems/time-based-key-value-store)

[English Version](/solution/0900-0999/0981.Time%20Based%20Key-Value%20Store/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个基于时间的键值数据结构，该结构可以在不同时间戳存储对应同一个键的多个值，并针对特定时间戳检索键对应的值。</p>

<p>实现 <code>TimeMap</code> 类：</p>

<ul>
	<li><code>TimeMap()</code> 初始化数据结构对象</li>
	<li><code>void set(String key, String value, int timestamp)</code> 存储键 <code>key</code>、值 <code>value</code>，以及给定的时间戳 <code>timestamp</code>。</li>
	<li><code>String get(String key, int timestamp)</code>
	<ul>
		<li>返回先前调用 <code>set(key, value, timestamp_prev)</code> 所存储的值，其中 <code>timestamp_prev <= timestamp</code> 。</li>
		<li>如果有多个这样的值，则返回对应最大的  <code>timestamp_prev</code> 的那个值。</li>
		<li>如果没有值，则返回空字符串（<code>""</code>）。</li>
	</ul>
	</li>
</ul>
 

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
<strong>输出：</strong>
[null, null, "bar", "bar", null, "bar2", "bar2"]

<strong>解释：</strong>
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // 存储键 "foo" 和值 "bar" ，时间戳 timestamp = 1   
timeMap.get("foo", 1);         // 返回 "bar"
timeMap.get("foo", 3);         // 返回 "bar", 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"） 。
timeMap.set("foo", "bar2", 4); // 存储键 "foo" 和值 "bar2" ，时间戳 timestamp = 4  
timeMap.get("foo", 4);         // 返回 "bar2"
timeMap.get("foo", 5);         // 返回 "bar2"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= key.length, value.length <= 100</code></li>
	<li><code>key</code> 和 <code>value</code> 由小写英文字母和数字组成</li>
	<li><code>1 <= timestamp <= 10<sup>7</sup></code></li>
	<li><code>set</code> 操作中的时间戳 <code>timestamp</code> 都是严格递增的</li>
	<li>最多调用 <code>set</code> 和 <code>get</code> 操作 <code>2 * 10<sup>5</sup></code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

嵌套哈希表实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        # #查找第一个大于timestamp的
        i = bisect_right(tv, (timestamp, chr(127)))
        return tv[i - 1][1] if i else ''


# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

因为 timestamp 是一直增长的，所以可以用二分查找快速找到值

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
