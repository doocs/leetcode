# [77. 组合](https://leetcode-cn.com/problems/combinations)

[English Version](/solution/0000-0099/0077.Combinations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数 <em>n</em> 和 <em>k</em>，返回 1 ... <em>n </em>中所有可能的 <em>k</em> 个数的组合。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>&nbsp;n = 4, k = 2
<strong>输出:</strong>
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索 DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        res = []

        def dfs(i, n, k, t):
            if len(t) == k:
                res.append(t.copy())
                return
            for j in range(i, n + 1):
                t.append(j)
                dfs(j + 1, n, k, t)
                t.pop()

        dfs(1, n, k, [])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int i, int n, int k, List<Integer> t, List<List<Integer>> res) {
        if (t.size() == k) {
            res.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.add(j);
            dfs(j + 1, n, k, t, res);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        vector<int> t;
        dfs(1, n, k, t, res);
        return res;
    }

    void dfs(int i, int n, int k, vector<int> t, vector<vector<int>>& res) {
        if (t.size() == k)
        {
            res.push_back(t);
            return;
        }
        for (int j = i; j <= n; ++j)
        {
            t.push_back(j);
            dfs(j + 1, n, k, t, res);
            t.pop_back();
        }
    }
};
```

### **Go**

```go
func combine(n int, k int) [][]int {
	var res [][]int
	var t []int
	dfs(1, n, k, t, &res)
	return res
}

func dfs(i, n, k int, t []int, res *[][]int) {
	if len(t) == k {
		cp := make([]int, k)
		copy(cp, t)
		*res = append(*res, cp)
		return
	}
	for j := i; j <= n; j++ {
		t = append(t, j)
		dfs(j+1, n, k, t, res)
		t = t[:len(t)-1]
	}
}
```

### **...**

```

```

<!-- tabs:end -->
