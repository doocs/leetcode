# [1306. 跳跃游戏 III](https://leetcode.cn/problems/jump-game-iii)

[English Version](/solution/1300-1399/1306.Jump%20Game%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这里有一个非负整数数组&nbsp;<code>arr</code>，你最开始位于该数组的起始下标&nbsp;<code>start</code>&nbsp;处。当你位于下标&nbsp;<code>i</code>&nbsp;处时，你可以跳到&nbsp;<code>i + arr[i]</code> 或者 <code>i - arr[i]</code>。</p>

<p>请你判断自己是否能够跳到对应元素值为 0 的 <strong>任一</strong> 下标处。</p>

<p>注意，不管是什么情况下，你都无法跳到数组之外。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [4,2,3,0,3,1,2], start = 5
<strong>输出：</strong>true
<strong>解释：</strong>
到达值为 0 的下标 3 有以下可能方案： 
下标 5 -&gt; 下标 4 -&gt; 下标 1 -&gt; 下标 3 
下标 5 -&gt; 下标 6 -&gt; 下标 4 -&gt; 下标 1 -&gt; 下标 3 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [4,2,3,0,3,1,2], start = 0
<strong>输出：</strong>true 
<strong>解释：
</strong>到达值为 0 的下标 3 有以下可能方案： 
下标 0 -&gt; 下标 4 -&gt; 下标 1 -&gt; 下标 3
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [3,0,2,1,2], start = 2
<strong>输出：</strong>false
<strong>解释：</strong>无法到达值为 0 的下标 1 处。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5 * 10^4</code></li>
	<li><code>0 &lt;= arr[i] &lt;&nbsp;arr.length</code></li>
	<li><code>0 &lt;= start &lt; arr.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
