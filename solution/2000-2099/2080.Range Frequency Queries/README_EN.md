# [2080. Range Frequency Queries](https://leetcode.com/problems/range-frequency-queries)

[中文文档](/solution/2000-2099/2080.Range%20Frequency%20Queries/README.md)

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

<!-- tabs:start -->

### **Python3**

```python
class RangeFreqQuery:
    def __init__(self, arr: List[int]):
        self.mp = defaultdict(list)
        for i, x in enumerate(arr):
            self.mp[x].append(i)

    def query(self, left: int, right: int, value: int) -> int:
        if value not in self.mp:
            return 0
        arr = self.mp[value]
        l, r = bisect_right(arr, left - 1), bisect_right(arr, right)
        return r - l


# Your RangeFreqQuery object will be instantiated and called as such:
# obj = RangeFreqQuery(arr)
# param_1 = obj.query(left,right,value)
```

### **Java**

```java
class RangeFreqQuery {
    private Map<Integer, List<Integer>> mp = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            mp.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        if (!mp.containsKey(value)) {
            return 0;
        }
        List<Integer> arr = mp.get(value);
        int l = search(arr, left - 1);
        int r = search(arr, right);
        return r - l;
    }

    private int search(List<Integer> arr, int val) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) > val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
```

### **C++**

```cpp
class RangeFreqQuery {
public:
    unordered_map<int, vector<int>> mp;
    RangeFreqQuery(vector<int>& arr) {
        for (int i = 0; i < arr.size(); ++i)
            mp[arr[i]].push_back(i);
    }

    int query(int left, int right, int value) {
        if (!mp.count(value)) return 0;
        auto& arr = mp[value];
        auto l = upper_bound(arr.begin(), arr.end(), left - 1);
        auto r = upper_bound(arr.begin(), arr.end(), right);
        return r - l;
    }
};

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery* obj = new RangeFreqQuery(arr);
 * int param_1 = obj->query(left,right,value);
 */
```

### **Go**

```go
type RangeFreqQuery struct {
	mp map[int][]int
}

func Constructor(arr []int) RangeFreqQuery {
	mp := make(map[int][]int)
	for i, v := range arr {
		mp[v] = append(mp[v], i)
	}
	return RangeFreqQuery{mp}
}

func (this *RangeFreqQuery) Query(left int, right int, value int) int {
	arr := this.mp[value]
	l := sort.SearchInts(arr, left)
	r := sort.SearchInts(arr, right+1)
	return r - l
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * obj := Constructor(arr);
 * param_1 := obj.Query(left,right,value);
 */
```

### **...**

```

```

<!-- tabs:end -->
