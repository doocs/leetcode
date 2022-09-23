# [面试题 17.14. 最小 K 个数](https://leetcode.cn/problems/smallest-k-lcci)

[English Version](/lcci/17.14.Smallest%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> arr = [1,3,5,7,2,4,6,8], k = 4
<strong>输出：</strong> [1,2,3,4]
</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>0 &lt;= len(arr) &lt;= 100000</code></li>
	<li><code>0 &lt;= k &lt;= min(100000, len(arr))</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

直接排序，取前 k 个数即可。

时间复杂度 $O(n\log n)$。其中 $n$ 为数组长度。

**方法二：优先队列（大根堆）**

维护一个大小为 $k$ 的大根堆，遍历数组，将当前元素入堆，如果堆的大小超过 $k$，弹出堆顶元素。

遍历结束，将堆中元素的 $k$ 个元素取出即可。

时间复杂度 $O(n\log k)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
