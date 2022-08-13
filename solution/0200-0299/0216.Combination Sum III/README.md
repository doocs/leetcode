# [216. 组合总和 III](https://leetcode.cn/problems/combination-sum-iii)

[English Version](/solution/0200-0299/0216.Combination%20Sum%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>找出所有相加之和为&nbsp;<code>n</code><em> </em>的&nbsp;<code>k</code><strong>&nbsp;</strong>个数的组合，且满足下列条件：</p>

<ul>
	<li>只使用数字1到9</li>
	<li>每个数字&nbsp;<strong>最多使用一次</strong>&nbsp;</li>
</ul>

<p>返回 <em>所有可能的有效组合的列表</em> 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <em><strong>k</strong></em> = 3, <em><strong>n</strong></em> = 7
<strong>输出:</strong> [[1,2,4]]
<strong>解释:</strong>
1 + 2 + 4 = 7
没有其他符合的组合了。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> <em><strong>k</strong></em> = 3, <em><strong>n</strong></em> = 9
<strong>输出:</strong> [[1,2,6], [1,3,5], [2,3,4]]
<strong>解释:
</strong>1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
没有其他符合的组合了。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> k = 4, n = 1
<strong>输出:</strong> []
<strong>解释:</strong> 不存在有效的组合。
在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 &gt; 1，没有有效的组合。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 60</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS 回溯法。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        def dfs(i, s, t):
            if i > 9 or s > n or len(t) > k:
                return
            if s == n and len(t) == k:
                ans.append(t[:])
                return
            i += 1
            t.append(i)
            dfs(i, s + i, t)
            t.pop()
            dfs(i, s, t)

        ans = []
        dfs(0, 0, [])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans;

    public List<List<Integer>> combinationSum3(int k, int n) {
        ans = new ArrayList<>();
        dfs(0, n, k, new ArrayList<>());
        return ans;
    }

    private void dfs(int i, int n, int k, List<Integer> t) {
        if (i > 9 || n < 0 || t.size() > k) {
            return;
        }
        if (n == 0 && t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        ++i;
        t.add(i);
        dfs(i, n - i, k, t);
        t.remove(t.size() - 1);
        dfs(i, n, k, t);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> ans;

    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> t;
        dfs(0, n, k, t);
        return ans;
    }

    void dfs(int i, int n, int k, vector<int>& t) {
        if (i > 9 || n < 0 || t.size() > k) return;
        if (n == 0 && t.size() == k) {
            ans.push_back(t);
            return;
        }
        ++i;
        t.push_back(i);
        dfs(i, n - i, k, t);
        t.pop_back();
        dfs(i, n, k, t);
    }
};
```

### **Go**

```go
func combinationSum3(k int, n int) [][]int {
	var ans [][]int
	var t []int
	var dfs func(i, n int, t []int)
	dfs = func(i, n int, t []int) {
		if i > 9 || n < 0 || len(t) > k {
			return
		}
		if n == 0 && len(t) == k {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		i++
		t = append(t, i)
		dfs(i, n-i, t)
		t = t[:len(t)-1]
		dfs(i, n, t)
	}

	dfs(0, n, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
