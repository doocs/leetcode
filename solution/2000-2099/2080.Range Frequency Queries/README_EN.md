---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2080.Range%20Frequency%20Queries/README_EN.md
rating: 1702
source: Weekly Contest 268 Q3
tags:
    - Design
    - Segment Tree
    - Array
    - Hash Table
    - Binary Search
---

<!-- problem:start -->

# [2080. Range Frequency Queries](https://leetcode.com/problems/range-frequency-queries)

[中文文档](/solution/2000-2099/2080.Range%20Frequency%20Queries/README.md)

## Description

<!-- description:start -->

<p>Design a data structure to find the <strong>frequency</strong> of a given value in a given subarray.</p>

<p>The <strong>frequency</strong> of a value in a subarray is the number of occurrences of that value in the subarray.</p>

<p>Implement the <code>RangeFreqQuery</code> class:</p>

<ul>
	<li><code>RangeFreqQuery(int[] arr)</code> Constructs an instance of the class with the given <strong>0-indexed</strong> integer array <code>arr</code>.</li>
	<li><code>int query(int left, int right, int value)</code> Returns the <strong>frequency</strong> of <code>value</code> in the subarray <code>arr[left...right]</code>.</li>
</ul>

<p>A <strong>subarray</strong> is a contiguous sequence of elements within an array. <code>arr[left...right]</code> denotes the subarray that contains the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> (<strong>inclusive</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;RangeFreqQuery&quot;, &quot;query&quot;, &quot;query&quot;]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
<strong>Output</strong>
[null, 1, 2]

<strong>Explanation</strong>
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i], value &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; arr.length</code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>query</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Binary Search

We use a hash table $g$ to store the array of indices corresponding to each value. In the constructor, we traverse the array $\textit{arr}$, adding the index corresponding to each value to the hash table.

In the query function, we first check whether the given value exists in the hash table. If it does not exist, it means that the value does not exist in the array, so we directly return $0$. Otherwise, we get the index array $\textit{idx}$ corresponding to the value. Then we use binary search to find the first index $l$ that is greater than or equal to $\textit{left}$, and the first index $r$ that is greater than $\textit{right}$. Finally, we return $r - l$.

In terms of time complexity, the time complexity of the constructor is $O(n)$, and the time complexity of the query function is $O(\log n)$. The space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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
        const l = _.sortedIndex(idx, left);
        const r = _.sortedIndex(idx, right + 1);
        return r - l;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * var obj = new RangeFreqQuery(arr)
 * var param_1 = obj.query(left,right,value)
 */
```

#### Rust

```rust
use std::collections::HashMap;

struct RangeFreqQuery {
    g: HashMap<i32, Vec<usize>>,
}

impl RangeFreqQuery {
    fn new(arr: Vec<i32>) -> Self {
        let mut g = HashMap::new();
        for (i, &value) in arr.iter().enumerate() {
            g.entry(value).or_insert_with(Vec::new).push(i);
        }
        RangeFreqQuery { g }
    }

    fn query(&self, left: i32, right: i32, value: i32) -> i32 {
        if let Some(idx) = self.g.get(&value) {
            let l = idx.partition_point(|&x| x < left as usize);
            let r = idx.partition_point(|&x| x <= right as usize);
            return (r - l) as i32;
        }
        0
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 */
var RangeFreqQuery = function (arr) {
    this.g = new Map();

    for (let i = 0; i < arr.length; ++i) {
        if (!this.g.has(arr[i])) {
            this.g.set(arr[i], []);
        }
        this.g.get(arr[i]).push(i);
    }
};

/**
 * @param {number} left
 * @param {number} right
 * @param {number} value
 * @return {number}
 */
RangeFreqQuery.prototype.query = function (left, right, value) {
    const idx = this.g.get(value);
    if (!idx) {
        return 0;
    }
    const l = _.sortedIndex(idx, left);
    const r = _.sortedIndex(idx, right + 1);
    return r - l;
};

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * var obj = new RangeFreqQuery(arr)
 * var param_1 = obj.query(left,right,value)
 */
```

#### C#

```cs
public class RangeFreqQuery {
    private Dictionary<int, List<int>> g;

    public RangeFreqQuery(int[] arr) {
        g = new Dictionary<int, List<int>>();
        for (int i = 0; i < arr.Length; ++i) {
            if (!g.ContainsKey(arr[i])) {
                g[arr[i]] = new List<int>();
            }
            g[arr[i]].Add(i);
        }
    }

    public int Query(int left, int right, int value) {
        if (g.ContainsKey(value)) {
            var idx = g[value];
            int l = idx.BinarySearch(left);
            int r = idx.BinarySearch(right + 1);
            l = l < 0 ? -l - 1 : l;
            r = r < 0 ? -r - 1 : r;
            return r - l;
        }
        return 0;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.Query(left, right, value);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
