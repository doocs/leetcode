---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0981.Time%20Based%20Key-Value%20Store/README.md
tags:
    - 设计
    - 哈希表
    - 字符串
    - 二分查找
---

<!-- problem:start -->

# [981. 基于时间的键值存储](https://leetcode.cn/problems/time-based-key-value-store)

[English Version](/solution/0900-0999/0981.Time%20Based%20Key-Value%20Store/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个基于时间的键值数据结构，该结构可以在不同时间戳存储对应同一个键的多个值，并针对特定时间戳检索键对应的值。</p>

<p>实现 <code>TimeMap</code> 类：</p>

<ul>
	<li><code>TimeMap()</code> 初始化数据结构对象</li>
	<li><code>void set(String key, String value, int timestamp)</code> 存储给定时间戳&nbsp;<code>timestamp</code>&nbsp;时的键&nbsp;<code>key</code>&nbsp;和值&nbsp;<code>value</code>。</li>
	<li><code>String get(String key, int timestamp)</code>&nbsp;返回一个值，该值在之前调用了 <code>set</code>，其中&nbsp;<code>timestamp_prev &lt;= timestamp</code>&nbsp;。如果有多个这样的值，它将返回与最大 &nbsp;<code>timestamp_prev</code>&nbsp;关联的值。如果没有值，则返回空字符串（<code>""</code>）。</li>
</ul>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
<strong>输出：</strong>
[null, null, "bar", "bar", null, "bar2", "bar2"]

<strong>解释：</strong>
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // 存储键 "foo" 和值 "bar" ，时间戳 timestamp = 1 &nbsp; 
timeMap.get("foo", 1);         // 返回 "bar"
timeMap.get("foo", 3);         // 返回 "bar", 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"） 。
timeMap.set("foo", "bar2", 4); // 存储键 "foo" 和值 "bar2" ，时间戳 timestamp = 4&nbsp; 
timeMap.get("foo", 4);         // 返回 "bar2"
timeMap.get("foo", 5);         // 返回 "bar2"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= key.length, value.length &lt;= 100</code></li>
	<li><code>key</code> 和 <code>value</code> 由小写英文字母和数字组成</li>
	<li><code>1 &lt;= timestamp &lt;= 10<sup>7</sup></code></li>
	<li><code>set</code> 操作中的时间戳 <code>timestamp</code> 都是严格递增的</li>
	<li>最多调用&nbsp;<code>set</code> 和 <code>get</code> 操作 <code>2 * 10<sup>5</sup></code> 次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 有序集合（或二分查找）

我们可以用哈希表 $\textit{kvt}$ 记录键值对，其中键为字符串 $\textit{key}$，值为一个有序集合，集合中的每个元素为一个二元组 $(\textit{timestamp}, \textit{value})$，表示键 $\textit{key}$ 在时间戳 $\textit{timestamp}$ 时对应的值为 $\textit{value}$。

当我们需要查询键 $\textit{key}$ 在时间戳 $\textit{timestamp}$ 时对应的值时，我们可以通过有序集合的方法找到最大的时间戳 $\textit{timestamp}'$，使得 $\textit{timestamp}' \leq \textit{timestamp}$，然后返回对应的值即可。

时间复杂度方面，对于 $\textit{set}$ 操作，由于哈希表的插入操作的时间复杂度为 $O(1)$，因此时间复杂度为 $O(1)$。对于 $\textit{get}$ 操作，由于哈希表的查找操作的时间复杂度为 $O(1)$，而有序集合的查找操作的时间复杂度为 $O(\log n)$，因此时间复杂度为 $O(\log n)$。空间复杂度为 $O(n)$，其中 $n$ 为 $\textit{set}$ 操作的次数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
