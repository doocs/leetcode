# [1306. Jump Game III](https://leetcode.com/problems/jump-game-iii)

[中文文档](/solution/1300-1399/1306.Jump%20Game%20III/README.md)

## Description

<p>Given an array of non-negative integers <code>arr</code>, you are initially positioned at <code>start</code>&nbsp;index of the array. When you are at index <code>i</code>, you can jump&nbsp;to <code>i + arr[i]</code> or <code>i - arr[i]</code>, check if you can reach to <strong>any</strong> index with value 0.</p>

<p>Notice that you can not jump outside of the array at any time.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,2,3,0,3,1,2], start = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> 
All possible ways to reach at index 3 with value 0 are: 
index 5 -&gt; index 4 -&gt; index 1 -&gt; index 3 
index 5 -&gt; index 6 -&gt; index 4 -&gt; index 1 -&gt; index 3 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,2,3,0,3,1,2], start = 0
<strong>Output:</strong> true 
<strong>Explanation: 
</strong>One possible way to reach at index 3 with value 0 is: 
index 0 -&gt; index 4 -&gt; index 1 -&gt; index 3
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,0,2,1,2], start = 2
<strong>Output:</strong> false
<strong>Explanation: </strong>There is no way to reach at index 1 with value 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;&nbsp;arr.length</code></li>
	<li><code>0 &lt;= start &lt; arr.length</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        n = len(arr)
        q = deque([start])
        while q:
            i = q.popleft()
            if arr[i] == 0:
                return True
            for j in [i + arr[i], i - arr[i]]:
                if 0 <= j < n and arr[j] >= 0:
                    q.append(j)
            arr[i] = -1
        return False
```

### **Java**

```java
class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int i = q.poll();
            if (arr[i] == 0) {
                return true;
            }
            for (int j : Arrays.asList(i + arr[i], i - arr[i])) {
                if (j >= 0 && j < n && arr[j] >= 0) {
                    q.offer(j);
                }
            }
            arr[i] = -1;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canReach(vector<int>& arr, int start) {
        int n = arr.size();
        queue<int> q {{start}};
        while (!q.empty()) {
            int i = q.front();
            if (arr[i] == 0)
                return 1;
            q.pop();
            for (int j : {i + arr[i], i - arr[i]}) {
                if (j >= 0 && j < n && arr[j] >= 0)
                    q.push(j);
            }
            arr[i] = -1;
        }
        return 0;
    }
};
```

### **Go**

```go
func canReach(arr []int, start int) bool {
	q := []int{start}
	for len(q) > 0 {
		i := q[0]
		if arr[i] == 0 {
			return true
		}
		q = q[1:]
		for _, j := range []int{i + arr[i], i - arr[i]} {
			if j >= 0 && j < len(arr) && arr[j] >= 0 {
				q = append(q, j)
			}
		}
		arr[i] = -1
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
