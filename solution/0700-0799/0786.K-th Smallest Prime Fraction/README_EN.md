# [786. K-th Smallest Prime Fraction](https://leetcode.com/problems/k-th-smallest-prime-fraction)

[中文文档](/solution/0700-0799/0786.K-th%20Smallest%20Prime%20Fraction/README.md)

## Description

<p>You are given a sorted integer array <code>arr</code> containing <code>1</code> and <strong>prime</strong> numbers, where all the integers of <code>arr</code> are unique. You are also given an integer <code>k</code>.</p>

<p>For every <code>i</code> and <code>j</code> where <code>0 &lt;= i &lt; j &lt; arr.length</code>, we consider the fraction <code>arr[i] / arr[j]</code>.</p>

<p>Return <em>the</em> <code>k<sup>th</sup></code> <em>smallest fraction considered</em>. Return your answer as an array of integers of size <code>2</code>, where <code>answer[0] == arr[i]</code> and <code>answer[1] == arr[j]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,5], k = 3
<strong>Output:</strong> [2,5]
<strong>Explanation:</strong> The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,7], k = 1
<strong>Output:</strong> [1,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>arr[0] == 1</code></li>
	<li><code>arr[i]</code> is a <strong>prime</strong> number for <code>i &gt; 0</code>.</li>
	<li>All the numbers of <code>arr</code> are <strong>unique</strong> and sorted in <strong>strictly increasing</strong> order.</li>
	<li><code>1 &lt;= k &lt;= arr.length * (arr.length - 1) / 2</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you solve the problem with better than <code>O(n<sup>2</sup>)</code> complexity?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kthSmallestPrimeFraction(self, arr: List[int], k: int) -> List[int]:
        h = [(1 / y, 0, j + 1) for j, y in enumerate(arr[1:])]
        heapify(h)
        for _ in range(k - 1):
            _, i, j = heappop(h)
            if i + 1 < j:
                heappush(h, (arr[i + 1] / arr[j], i + 1, j))
        return [arr[h[0][1]], arr[h[0][2]]]
```

### **Java**

```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        Queue<Frac> pq = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            pq.offer(new Frac(1, arr[i], 0, i));
        }
        for (int i = 1; i < k; i++) {
            Frac f = pq.poll();
            if (f.i + 1 < f.j) {
                pq.offer(new Frac(arr[f.i + 1], arr[f.j], f.i + 1, f.j));
            }
        }
        Frac f = pq.peek();
        return new int[] { f.x, f.y };
    }

    static class Frac implements Comparable {
        int x, y, i, j;

        public Frac(int x, int y, int i, int j) {
            this.x = x;
            this.y = y;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Object o) {
            return x * ((Frac) o).y - ((Frac) o).x * y;
        }
    }
}
```

### **Go**

```go
type frac struct{ x, y, i, j int }
type hp []frac

func (a hp) Len() int            { return len(a) }
func (a hp) Swap(i, j int)       { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool  { return a[i].x*a[j].y < a[j].x*a[i].y }
func (a *hp) Push(x interface{}) { *a = append(*a, x.(frac)) }
func (a *hp) Pop() interface{}   { l := len(*a); tmp := (*a)[l-1]; *a = (*a)[:l-1]; return tmp }

func kthSmallestPrimeFraction(arr []int, k int) []int {
	n := len(arr)
	h := make(hp, 0, n-1)
	for i := 1; i < n; i++ {
		h = append(h, frac{1, arr[i], 0, i})
	}
	heap.Init(&h)
	for i := 1; i < k; i++ {
		f := heap.Pop(&h).(frac)
		if f.i+1 < f.j {
			heap.Push(&h, frac{arr[f.i+1], arr[f.j], f.i + 1, f.j})
		}
	}
	return []int{h[0].x, h[0].y}
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> kthSmallestPrimeFraction(vector<int>& arr, int k) {
        using pii = pair<int, int>;
        int n = arr.size();
        auto cmp = [&](const pii& a, const pii& b) {
            return arr[a.first] * arr[b.second] > arr[b.first] * arr[a.second];
        };
        priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
        for (int i = 1; i < n; ++i) {
            pq.push({0, i});
        }
        for (int i = 1; i < k; ++i) {
            pii f = pq.top();
            pq.pop();
            if (f.first + 1 < f.second) {
                pq.push({f.first + 1, f.second});
            }
        }
        return {arr[pq.top().first], arr[pq.top().second]};
    }
};
```

### **...**

```

```

<!-- tabs:end -->
