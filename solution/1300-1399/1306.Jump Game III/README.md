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

**方法一：BFS**

我们可以使用 BFS 来判断是否能够到达值为 $0$ 的下标。

定义一个队列 $q$，用于存储当前能够到达的下标。初始时，将 $start$ 下标入队。

当队列不为空时，取出队首下标 $i$，如果 $arr[i] = 0$，则返回 `true`。否则，我们将下标 $i$ 标记为已访问，如果 $i + arr[i]$ 和 $i - arr[i]$ 在数组范围内且未被访问过，则将其入队，继续搜索。

最后，如果队列为空，说明无法到达值为 $0$ 的下标，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        q = deque([start])
        while q:
            i = q.popleft()
            if arr[i] == 0:
                return True
            x = arr[i]
            arr[i] = -1
            for j in (i + x, i - x):
                if 0 <= j < len(arr) and arr[j] >= 0:
                    q.append(j)
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canReach(int[] arr, int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int i = q.poll();
            if (arr[i] == 0) {
                return true;
            }
            int x = arr[i];
            arr[i] = -1;
            for (int j : List.of(i + x, i - x)) {
                if (j >= 0 && j < arr.length && arr[j] >= 0) {
                    q.offer(j);
                }
            }
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
        queue<int> q{{start}};
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            if (arr[i] == 0) {
                return true;
            }
            int x = arr[i];
            arr[i] = -1;
            for (int j : {i + x, i - x}) {
                if (j >= 0 && j < arr.size() && ~arr[j]) {
                    q.push(j);
                }
            }
        }
        return false;
    }
};
```

### **Go**

```go
func canReach(arr []int, start int) bool {
	q := []int{start}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if arr[i] == 0 {
			return true
		}
		x := arr[i]
		arr[i] = -1
		for _, j := range []int{i + x, i - x} {
			if j >= 0 && j < len(arr) && arr[j] >= 0 {
				q = append(q, j)
			}
		}
	}
	return false
}
```

### **TypeScript**

```ts
function canReach(arr: number[], start: number): boolean {
    const q: number[] = [start];
    while (q.length) {
        const i: number = q.shift()!;
        if (arr[i] === 0) {
            return true;
        }
        const x: number = arr[i];
        arr[i] = -1;
        for (const j of [i + x, i - x]) {
            if (j >= 0 && j < arr.length && arr[j] !== -1) {
                q.push(j);
            }
        }
    }
    return false;
}
```

### **...**

```

```

<!-- tabs:end -->
