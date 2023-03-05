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

**方法一：哈希表 + 有序集合（或二分查找）**

我们可以用哈希表 $ktv$ 记录键值对，其中键为字符串 $key$，值为一个列表，列表中的每个元素为一个二元组 $(timestamp, value)$，表示键 $key$ 在时间戳 $timestamp$ 时对应的值为 $value$。

当我们需要查询键 $key$ 在时间戳 $timestamp$ 时对应的值时，我们可以通过二分查找的方法在 $ktv[key]$ 中找到最大的时间戳 $timestamp'$，使得 $timestamp' \leq timestamp$，然后返回对应的值即可。

时间复杂度方面，对于 $set$ 操作，由于哈希表的插入操作的时间复杂度为 $O(1)$，因此时间复杂度为 $O(1)$。对于 $get$ 操作，由于哈希表的查找操作的时间复杂度为 $O(1)$，而二分查找的时间复杂度为 $O(\log n)$，因此时间复杂度为 $O(\log n)$。空间复杂度为 $O(n)$，其中 $n$ 为 $set$ 操作的次数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class TimeMap:

    def __init__(self):
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class TimeMap {
    private Map<String, TreeMap<Integer, String>> ktv = new HashMap<>();

    public TimeMap() {
    }

    public void set(String key, String value, int timestamp) {
        ktv.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!ktv.containsKey(key)) {
            return "";
        }
        var tv = ktv.get(key);
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

### **C++**

```cpp
class TimeMap {
public:
    TimeMap() {

    }

    void set(string key, string value, int timestamp) {
        ktv[key].emplace_back(timestamp, value);
    }

    string get(string key, int timestamp) {
        auto& pairs = ktv[key];
        pair<int, string> p = {timestamp, string({127})};
        auto i = upper_bound(pairs.begin(), pairs.end(), p);
        return i == pairs.begin() ? "" : (i - 1)->second;
    }

private:
    unordered_map<string, vector<pair<int, string>>> ktv;
};

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap* obj = new TimeMap();
 * obj->set(key,value,timestamp);
 * string param_2 = obj->get(key,timestamp);
 */
```

### **Go**

```go
type TimeMap struct {
	ktv map[string][]pair
}

func Constructor() TimeMap {
	return TimeMap{map[string][]pair{}}
}

func (this *TimeMap) Set(key string, value string, timestamp int) {
	this.ktv[key] = append(this.ktv[key], pair{timestamp, value})
}

func (this *TimeMap) Get(key string, timestamp int) string {
	pairs := this.ktv[key]
	i := sort.Search(len(pairs), func(i int) bool { return pairs[i].t > timestamp })
	if i > 0 {
		return pairs[i-1].v
	}
	return ""
}

type pair struct {
	t int
	v string
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Set(key,value,timestamp);
 * param_2 := obj.Get(key,timestamp);
 */
```

### **...**

```

```

<!-- tabs:end -->
