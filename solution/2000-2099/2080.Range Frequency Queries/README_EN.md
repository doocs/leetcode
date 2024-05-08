# [2080. Range Frequency Queries](https://leetcode.com/problems/range-frequency-queries)

[中文文档](/solution/2000-2099/2080.Range%20Frequency%20Queries/README.md)

<!-- tags:Design,Segment Tree,Array,Hash Table,Binary Search -->

## Description

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

## Solutions

### Solution 1: Hash Table

We use a hash table $g$ to store the array of indices corresponding to each value. In the constructor, we traverse the array $\text{arr}$, adding the index corresponding to each value to the hash table.

In the query function, we first check whether the given value exists in the hash table. If it does not exist, it means that the value does not exist in the array, so we directly return $0$. Otherwise, we get the index array $\text{idx}$ corresponding to the value. Then we use binary search to find the first index $l$ that is greater than or equal to $\text{left}$, and the first index $r$ that is greater than $\text{right}$. Finally, we return $r - l$.

In terms of time complexity, the time complexity of the constructor is $O(n)$, and the time complexity of the query function is $O(\log n)$. The space complexity is $O(n)$. Where $n$ is the length of the array.

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
