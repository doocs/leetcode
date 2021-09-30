# [981. 基于时间的键值存储](https://leetcode-cn.com/problems/time-based-key-value-store)

[English Version](/solution/0900-0999/0981.Time%20Based%20Key-Value%20Store/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>创建一个基于时间的键值存储类&nbsp;<code>TimeMap</code>，它支持下面两个操作：</p>

<p>1. <code>set(string key, string value, int timestamp)</code></p>

<ul>
	<li>存储键&nbsp;<code>key</code>、值&nbsp;<code>value</code>，以及给定的时间戳&nbsp;<code>timestamp</code>。</li>
</ul>

<p>2. <code>get(string key, int timestamp)</code></p>

<ul>
	<li>返回先前调用&nbsp;<code>set(key, value, timestamp_prev)</code>&nbsp;所存储的值，其中&nbsp;<code>timestamp_prev &lt;= timestamp</code>。</li>
	<li>如果有多个这样的值，则返回对应最大的&nbsp;&nbsp;<code>timestamp_prev</code>&nbsp;的那个值。</li>
	<li>如果没有值，则返回空字符串（<code>&quot;&quot;</code>）。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>inputs = [&quot;TimeMap&quot;,&quot;set&quot;,&quot;get&quot;,&quot;get&quot;,&quot;set&quot;,&quot;get&quot;,&quot;get&quot;], inputs = [[],[&quot;foo&quot;,&quot;bar&quot;,1],[&quot;foo&quot;,1],[&quot;foo&quot;,3],[&quot;foo&quot;,&quot;bar2&quot;,4],[&quot;foo&quot;,4],[&quot;foo&quot;,5]]
<strong>输出：</strong>[null,null,&quot;bar&quot;,&quot;bar&quot;,null,&quot;bar2&quot;,&quot;bar2&quot;]
<strong>解释：</strong>&nbsp;
TimeMap kv; &nbsp;
kv.set(&quot;foo&quot;, &quot;bar&quot;, 1); // 存储键 &quot;foo&quot; 和值 &quot;bar&quot; 以及时间戳 timestamp = 1 &nbsp;
kv.get(&quot;foo&quot;, 1);  // 输出 &quot;bar&quot; &nbsp;
kv.get(&quot;foo&quot;, 3); // 输出 &quot;bar&quot; 因为在时间戳 3 和时间戳 2 处没有对应 &quot;foo&quot; 的值，所以唯一的值位于时间戳 1 处（即 &quot;bar&quot;） &nbsp;
kv.set(&quot;foo&quot;, &quot;bar2&quot;, 4); &nbsp;
kv.get(&quot;foo&quot;, 4); // 输出 &quot;bar2&quot; &nbsp;
kv.get(&quot;foo&quot;, 5); // 输出 &quot;bar2&quot; &nbsp;

</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>inputs = [&quot;TimeMap&quot;,&quot;set&quot;,&quot;set&quot;,&quot;get&quot;,&quot;get&quot;,&quot;get&quot;,&quot;get&quot;,&quot;get&quot;], inputs = [[],[&quot;love&quot;,&quot;high&quot;,10],[&quot;love&quot;,&quot;low&quot;,20],[&quot;love&quot;,5],[&quot;love&quot;,10],[&quot;love&quot;,15],[&quot;love&quot;,20],[&quot;love&quot;,25]]
<strong>输出：</strong>[null,null,null,&quot;&quot;,&quot;high&quot;,&quot;high&quot;,&quot;low&quot;,&quot;low&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>所有的键/值字符串都是小写的。</li>
	<li>所有的键/值字符串长度都在&nbsp;<code>[1, 100]</code>&nbsp;范围内。</li>
	<li>所有&nbsp;<code>TimeMap.set</code>&nbsp;操作中的时间戳&nbsp;<code>timestamps</code> 都是严格递增的。</li>
	<li><code>1 &lt;= timestamp &lt;= 10^7</code></li>
	<li><code>TimeMap.set</code> 和&nbsp;<code>TimeMap.get</code>&nbsp;函数在每个测试用例中将（组合）调用总计&nbsp;<code>120000</code> 次。</li>
</ol>

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
        self.ktv = collections.defaultdict(list)

    def set(self, key: str, value: str, timestamp: int) -> None:
        self.ktv[key].append((timestamp, value))

    def get(self, key: str, timestamp: int) -> str:
        if key not in self.ktv:
            return ''
        tv = self.ktv[key]
        # #查找第一个大于timestamp的
        i = bisect.bisect_right(tv, (timestamp, chr(127)))
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
