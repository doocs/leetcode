# [1718. 构建字典序最大的可行序列](https://leetcode.cn/problems/construct-the-lexicographically-largest-valid-sequence)

[English Version](/solution/1700-1799/1718.Construct%20the%20Lexicographically%20Largest%20Valid%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，请你找到满足下面条件的一个序列：</p>

<ul>
	<li>整数 <code>1</code> 在序列中只出现一次。</li>
	<li><code>2</code> 到 <code>n</code> 之间每个整数都恰好出现两次。</li>
	<li>对于每个 <code>2</code> 到 <code>n</code> 之间的整数 <code>i</code> ，两个 <code>i</code> 之间出现的距离恰好为 <code>i</code> 。</li>
</ul>

<p>序列里面两个数 <code>a[i]</code> 和 <code>a[j]</code> 之间的 <strong>距离</strong> ，我们定义为它们下标绝对值之差 <code>|j - i|</code> 。</p>

<p>请你返回满足上述条件中 <strong>字典序最大</strong> 的序列。题目保证在给定限制条件下，一定存在解。</p>

<p>一个序列 <code>a</code> 被认为比序列 <code>b</code> （两者长度相同）字典序更大的条件是： <code>a</code> 和 <code>b</code> 中第一个不一样的数字处，<code>a</code> 序列的数字比 <code>b</code> 序列的数字大。比方说，<code>[0,1,9,0]</code> 比 <code>[0,1,5,6]</code> 字典序更大，因为第一个不同的位置是第三个数字，且 <code>9</code> 比 <code>5</code> 大。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 3
<b>输出：</b>[3,1,2,3,2]
<b>解释：</b>[2,3,2,1,3] 也是一个可行的序列，但是 [3,1,2,3,2] 是字典序最大的序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 5
<b>输出：</b>[5,3,1,4,3,5,2,4,2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS 回溯。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def constructDistancedSequence(self, n: int) -> List[int]:
        def dfs(u):
            if u == n * 2:
                return True
            if path[u]:
                return dfs(u + 1)
            for i in range(n, 1, -1):
                if cnt[i] and u + i < n * 2 and path[u + i] == 0:
                    cnt[i] = 0
                    path[u] = path[u + i] = i
                    if dfs(u + 1):
                        return True
                    path[u] = path[u + i] = 0
                    cnt[i] = 2
            if cnt[1]:
                cnt[1], path[u] = 0, 1
                if dfs(u + 1):
                    return True
                path[u], cnt[1] = 0, 1
            return False

        path = [0] * (n * 2)
        cnt = [2] * (n * 2)
        cnt[1] = 1
        dfs(1)
        return path[1:]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] path;
    private int[] cnt;
    private int n;

    public int[] constructDistancedSequence(int n) {
        this.n = n;
        path = new int[n * 2];
        cnt = new int[n * 2];
        Arrays.fill(cnt, 2);
        cnt[1] = 1;
        dfs(1);
        int[] ans = new int[n * 2 - 1];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = path[i + 1];
        }
        return ans;
    }

    private boolean dfs(int u) {
        if (u == n * 2) {
            return true;
        }
        if (path[u] > 0) {
            return dfs(u + 1);
        }
        for (int i = n; i > 1; --i) {
            if (cnt[i] > 0 && u + i < n * 2 &&  path[u + i] == 0) {
                cnt[i] = 0;
                path[u] = i;
                path[u + i] = i;
                if (dfs(u + 1)) {
                    return true;
                }
                cnt[i] = 2;
                path[u] = 0;
                path[u + i] = 0;
            }
        }
        if (cnt[1] > 0) {
            path[u] = 1;
            cnt[1] = 0;
            if (dfs(u + 1)) {
                return true;
            }
            cnt[1] = 1;
            path[u] = 0;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;
    vector<int> cnt, path;

    vector<int> constructDistancedSequence(int _n) {
        n = _n;
        cnt.resize(n * 2, 2);
        path.resize(n * 2);
        cnt[1] = 1;
        dfs(1);
        vector<int> ans;
        for (int i = 1; i < n * 2; ++i) ans.push_back(path[i]);
        return ans;
    }

    bool dfs(int u) {
        if (u == n * 2) return 1;
        if (path[u]) return dfs(u + 1);
        for (int i = n; i > 1; --i) {
            if (cnt[i] && u + i < n * 2 && !path[u + i]) {
                path[u] = path[u + i] = i;
                cnt[i] = 0;
                if (dfs(u + 1)) return 1;
                cnt[i] = 2;
                path[u] = path[u + i] = 0;
            }
        }
        if (cnt[1]) {
            path[u] = 1;
            cnt[1] = 0;
            if (dfs(u + 1)) return 1;
            cnt[1] = 1;
            path[u] = 0;
        }
        return 0;
    }
};
```

### **Go**

```go
func constructDistancedSequence(n int) []int {
	path := make([]int, n*2)
	cnt := make([]int, n*2)
	for i := range cnt {
		cnt[i] = 2
	}
	cnt[1] = 1
	var dfs func(u int) bool
	dfs = func(u int) bool {
		if u == n*2 {
			return true
		}
		if path[u] > 0 {
			return dfs(u + 1)
		}
		for i := n; i > 1; i-- {
			if cnt[i] > 0 && u+i < n*2 && path[u+i] == 0 {
				cnt[i] = 0
				path[u], path[u+i] = i, i
				if dfs(u + 1) {
					return true
				}
				cnt[i] = 2
				path[u], path[u+i] = 0, 0
			}
		}
		if cnt[1] > 0 {
			cnt[1] = 0
			path[u] = 1
			if dfs(u + 1) {
				return true
			}
			cnt[1] = 1
			path[u] = 0
		}
		return false
	}
	dfs(1)
	return path[1:]
}
```

### **...**

```

```

<!-- tabs:end -->
