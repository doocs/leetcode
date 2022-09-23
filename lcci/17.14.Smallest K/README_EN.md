# [17.14. Smallest K](https://leetcode.cn/problems/smallest-k-lcci)

[中文文档](/lcci/17.14.Smallest%20K/README.md)

## Description

<p>Design an algorithm to find the smallest K numbers in an array.</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong> arr = [1,3,5,7,2,4,6,8], k = 4

<strong>Output: </strong> [1,2,3,4]

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>0 &lt;= len(arr) &lt;= 100000</code></li>
	<li><code>0 &lt;= k &lt;= min(100000, len(arr))</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestK(self, arr: List[int], k: int) -> List[int]:
        return sorted(arr)[:k]
```

```python
class Solution:
    def smallestK(self, arr: List[int], k: int) -> List[int]:
        h = []
        for v in arr:
            heappush(h, -v)
            if len(h) > k:
                heappop(h)
        return [-v for v in h]
```

### **Java**

```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int v : arr) {
            q.offer(v);
            if (q.size() > k) {
                q.poll();
            }
        }
        int[] ans = new int[k];
        int i = 0;
        while (!q.isEmpty()) {
            ans[i++] = q.poll();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> smallestK(vector<int>& arr, int k) {
        sort(arr.begin(), arr.end());
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            ans[i] = arr[i];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> smallestK(vector<int>& arr, int k) {
        priority_queue<int> q;
        for (int& v : arr) {
            q.push(v);
            if (q.size() > k) {
                q.pop();
            }
        }
        vector<int> ans;
        while (q.size()) {
            ans.push_back(q.top());
            q.pop();
        }
        return ans;
    }
};
```

### **Go**

```go
func smallestK(arr []int, k int) []int {
	sort.Ints(arr)
	ans := make([]int, k)
	for i, v := range arr[:k] {
		ans[i] = v
	}
	return ans
}
```

```go
func smallestK(arr []int, k int) []int {
	q := hp{}
	for _, v := range arr {
		heap.Push(&q, v)
		if q.Len() > k {
			heap.Pop(&q)
		}
	}
	ans := make([]int, k)
	for i := range ans {
		ans[i] = heap.Pop(&q).(int)
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **...**

```

```

<!-- tabs:end -->
