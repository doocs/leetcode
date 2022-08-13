# [1215. 步进数](https://leetcode.cn/problems/stepping-numbers)

[English Version](/solution/1200-1299/1215.Stepping%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个整数上的每一位数字与其相邻位上的数字的绝对差都是 <code>1</code>，那么这个数就是一个「步进数」。</p>

<p>例如，<code>321</code>&nbsp;是一个步进数，而&nbsp;<code>421</code>&nbsp;不是。</p>

<p>给你两个整数，<code>low</code>&nbsp;和&nbsp;<code>high</code>，请你找出在&nbsp;<code>[low, high]</code>&nbsp;范围内的所有步进数，并返回&nbsp;<strong>排序后</strong> 的结果。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>low = 0, high = 21
<strong>输出：</strong>[0,1,2,3,4,5,6,7,8,9,10,12,21]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= low &lt;= high &lt;= 2 * 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSteppingNumbers(self, low: int, high: int) -> List[int]:
        ans = []
        if low == 0:
            ans.append(0)
        q = deque(range(1, 10))
        while q:
            v = q.popleft()
            if v > high:
                break
            if v >= low:
                ans.append(v)
            x = v % 10
            if x:
                q.append(v * 10 + x - 1)
            if x < 9:
                q.append(v * 10 + x + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        if (low == 0) {
            ans.add(0);
        }
        Deque<Long> q = new ArrayDeque<>();
        for (long i = 1; i < 10; ++i) {
            q.offer(i);
        }
        while (!q.isEmpty()) {
            long v = q.pollFirst();
            if (v > high) {
                break;
            }
            if (v >= low) {
                ans.add((int) v);
            }
            int x = (int) v % 10;
            if (x > 0) {
                q.offer(v * 10 + x - 1);
            }
            if (x < 9) {
                q.offer(v * 10 + x + 1);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> countSteppingNumbers(int low, int high) {
        vector<int> ans;
        if (low == 0) ans.push_back(0);
        queue<long long> q;
        for (int i = 1; i < 10; ++i) q.push(i);
        while (!q.empty()) {
            int v = q.front();
            q.pop();
            if (v > high) break;
            if (v >= low) ans.push_back(v);
            int x = v % 10;
            if (x) q.push(1ll * v * 10 + x - 1);
            if (x < 9) q.push(1ll * v * 10 + x + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func countSteppingNumbers(low int, high int) []int {
	ans := []int{}
	if low == 0 {
		ans = append(ans, 0)
	}
	q := []int{1, 2, 3, 4, 5, 6, 7, 8, 9}
	for len(q) > 0 {
		v := q[0]
		q = q[1:]
		if v > high {
			break
		}
		if v >= low {
			ans = append(ans, v)
		}
		x := v % 10
		if x > 0 {
			q = append(q, v*10+x-1)
		}
		if x < 9 {
			q = append(q, v*10+x+1)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
