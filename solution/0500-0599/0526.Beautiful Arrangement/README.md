# [526. 优美的排列](https://leetcode-cn.com/problems/beautiful-arrangement)

[English Version](/solution/0500-0599/0526.Beautiful%20Arrangement/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设有从 1 到 N 的&nbsp;<strong>N&nbsp;</strong>个整数，如果从这&nbsp;<strong>N&nbsp;</strong>个数字中成功构造出一个数组，使得数组的第 <strong>i</strong>&nbsp;位 (1 &lt;= i &lt;= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：</p>

<ol>
	<li>第&nbsp;<strong>i&nbsp;</strong>位的数字能被&nbsp;<strong>i&nbsp;</strong>整除</li>
	<li><strong>i</strong> 能被第 <strong>i</strong> 位上的数字整除</li>
</ol>

<p>现在给定一个整数 N，请问可以构造多少个优美的排列？</p>

<p><strong>示例1:</strong></p>

<pre>
<strong>输入:</strong> 2
<strong>输出:</strong> 2
<strong>解释:</strong> 

第 1 个优美的排列是 [1, 2]:
  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除

第 2 个优美的排列是 [2, 1]:
  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li><strong>N</strong> 是一个正整数，并且不会超过15。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS 回溯，或者状态压缩。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countArrangement(self, n: int) -> int:
        def dfs(i):
            nonlocal ans, n
            if i == n + 1:
                ans += 1
                return
            for j in match[i]:
                if not vis[j]:
                    vis[j] = True
                    dfs(i + 1)
                    vis[j] = False

        ans = 0
        vis = [False] * (n + 1)
        match = defaultdict(list)
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if j % i == 0 or i % j == 0:
                    match[i].append(j)

        dfs(1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int ans;
    private boolean[] vis;
    private Map<Integer, List<Integer>> match;

    public int countArrangement(int n) {
        this.n = n;
        ans = 0;
        vis = new boolean[n + 1];
        match = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i % j == 0 || j % i == 0) {
                    match.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (i == n + 1) {
            ++ans;
            return;
        }
        if (!match.containsKey(i)) {
            return;
        }
        for (int j : match.get(i)) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
}
```

```java
class Solution {
    public int countArrangement(int N) {
        int maxn = 1 << N;
        int[] f = new int[maxn];
        f[0] = 1;
        for (int i = 0; i < maxn; ++i) {
            int s = 1;
            for (int j = 0; j < N; ++j) {
                s += (i >> j) & 1;
            }
            for (int j = 1; j <= N; ++j) {
                if (((i >> (j - 1) & 1) == 0) && (s % j == 0 || j % s == 0)) {
                    f[i | (1 << (j - 1))] += f[i];
                }
            }
        }
        return f[maxn - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;
    int ans;
    vector<bool> vis;
    unordered_map<int, vector<int>> match;

    int countArrangement(int n) {
        this->n = n;
        this->ans = 0;
        vis.resize(n + 1);
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j)
                if (i % j == 0 || j % i == 0)
                    match[i].push_back(j);
        dfs(1);
        return ans;
    }

    void dfs(int i) {
        if (i == n + 1)
        {
            ++ans;
            return;
        }
        for (int j : match[i])
        {
            if (!vis[j])
            {
                vis[j] = true;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
};
```

### **Go**

```go
func countArrangement(n int) int {
	ans := 0
	match := make(map[int][]int)
	for i := 1; i <= n; i++ {
		for j := 1; j <= n; j++ {
			if i%j == 0 || j%i == 0 {
				match[i] = append(match[i], j)
			}
		}
	}
	vis := make([]bool, n+1)

	var dfs func(i int)
	dfs = func(i int) {
		if i == n+1 {
			ans++
			return
		}
		for _, j := range match[i] {
			if !vis[j] {
				vis[j] = true
				dfs(i + 1)
				vis[j] = false
			}
		}
	}

	dfs(1)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
