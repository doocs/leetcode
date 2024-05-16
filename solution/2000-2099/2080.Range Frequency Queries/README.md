---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2080.Range%20Frequency%20Queries/README.md
rating: 1702
source: 第 268 场周赛 Q3
tags:
    - 设计
    - 线段树
    - 数组
    - 哈希表
    - 二分查找
---

# [2080. 区间内查询数字的频率](https://leetcode.cn/problems/range-frequency-queries)

[English Version](/solution/2000-2099/2080.Range%20Frequency%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个数据结构，它能求出给定子数组内一个给定值的 <strong>频率</strong>&nbsp;。</p>

<p>子数组中一个值的 <strong>频率</strong>&nbsp;指的是这个子数组中这个值的出现次数。</p>

<p>请你实现&nbsp;<code>RangeFreqQuery</code>&nbsp;类：</p>

<ul>
	<li><code>RangeFreqQuery(int[] arr)</code>&nbsp;用下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>arr</code>&nbsp;构造一个类的实例。</li>
	<li><code>int query(int left, int right, int value)</code>&nbsp;返回子数组&nbsp;<code>arr[left...right]</code>&nbsp;中&nbsp;<code>value</code>&nbsp;的&nbsp;<strong>频率</strong>&nbsp;。</li>
</ul>

<p>一个 <strong>子数组</strong> 指的是数组中一段连续的元素。<code>arr[left...right]</code>&nbsp;指的是 <code>nums</code>&nbsp;中包含下标 <code>left</code>&nbsp;和 <code>right</code>&nbsp;<strong>在内</strong>&nbsp;的中间一段连续元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
<strong>输出：</strong>
[null, 1, 2]

<strong>解释：</strong>
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i], value &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; arr.length</code></li>
	<li>调用&nbsp;<code>query</code>&nbsp;不超过&nbsp;<code>10<sup>5</sup></code>&nbsp;次。</li>
</ul>

## 解法

### 方法一：哈希表

我们用一个哈希表 $g$ 来存储每个值对应的下标数组。在构造函数中，我们遍历数组 $\text{arr}$，将每个值对应的下标加入到哈希表中。

在查询函数中，我们首先判断哈希表中是否存在给定的值。如果不存在，说明该值在数组中不存在，直接返回 $0$。否则，我们获取该值对应的下标数组 $\text{idx}$。然后我们使用二分查找找到下标数组中第一个大于等于 $\text{left}$ 的下标 $l$，以及第一个大于 $\text{right}$ 的下标 $r$。最后返回 $r - l$ 即可。

时间复杂度方面，构造函数的时间复杂度为 $O(n)$，查询函数的时间复杂度为 $O(\log n)$。其中 $n$ 为数组的长度。空间复杂度为 $O(n)$。

<!-- tabs:start -->

```python
class RangeFreqQuery:

    def __init__(self, arr: List[int]):
        self.g = defaultdict(list)
        for i, x in enumerate(arr):
            self.g[x].append(i)

    def query(self, left: int, right: int, value: int) -> int:
        idx = self.g[value]
        l = bisect_left(idx, left)
        r = bisect_left(idx, right + 1)
        return r - l


# Your RangeFreqQuery object will be instantiated and called as such:
# obj = RangeFreqQuery(arr)
# param_1 = obj.query(left,right,value)
```

```java
class RangeFreqQuery {
    private Map<Integer, List<Integer>> g = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            g.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        if (!g.containsKey(value)) {
            return 0;
        }
        var idx = g.get(value);
        int l = Collections.binarySearch(idx, left);
        l = l < 0 ? -l - 1 : l;
        int r = Collections.binarySearch(idx, right + 1);
        r = r < 0 ? -r - 1 : r;
        return r - l;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
```

```cpp
class RangeFreqQuery {
public:
    RangeFreqQuery(vector<int>& arr) {
        for (int i = 0; i < arr.size(); ++i) {
            g[arr[i]].push_back(i);
        }
    }

    int query(int left, int right, int value) {
        if (!g.contains(value)) {
            return 0;
        }
        auto& idx = g[value];
        auto l = lower_bound(idx.begin(), idx.end(), left);
        auto r = lower_bound(idx.begin(), idx.end(), right + 1);
        return r - l;
    }

private:
    unordered_map<int, vector<int>> g;
};

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery* obj = new RangeFreqQuery(arr);
 * int param_1 = obj->query(left,right,value);
 */
```

```go
type RangeFreqQuery struct {
	g map[int][]int
}

func Constructor(arr []int) RangeFreqQuery {
	g := make(map[int][]int)
	for i, v := range arr {
		g[v] = append(g[v], i)
	}
	return RangeFreqQuery{g}
}

func (this *RangeFreqQuery) Query(left int, right int, value int) int {
	if idx, ok := this.g[value]; ok {
		l := sort.SearchInts(idx, left)
		r := sort.SearchInts(idx, right+1)
		return r - l
	}
	return 0
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * obj := Constructor(arr);
 * param_1 := obj.Query(left,right,value);
 */
```

```ts
class RangeFreqQuery {
    private g: Map<number, number[]> = new Map();

    constructor(arr: number[]) {
        for (let i = 0; i < arr.length; ++i) {
            if (!this.g.has(arr[i])) {
                this.g.set(arr[i], []);
            }
            this.g.get(arr[i])!.push(i);
        }
    }

    query(left: number, right: number, value: number): number {
        const idx = this.g.get(value);
        if (!idx) {
            return 0;
        }
        const search = (x: number): number => {
            let [l, r] = [0, idx.length];
            while (l < r) {
                const mid = (l + r) >> 1;
                if (idx[mid] >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        const l = search(left);
        const r = search(right + 1);
        return r - l;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * var obj = new RangeFreqQuery(arr)
 * var param_1 = obj.query(left,right,value)
 */
```

<!-- tabs:end -->

<!-- end -->
