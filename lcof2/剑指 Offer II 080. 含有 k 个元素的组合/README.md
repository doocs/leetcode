---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20080.%20%E5%90%AB%E6%9C%89%20k%20%E4%B8%AA%E5%85%83%E7%B4%A0%E7%9A%84%E7%BB%84%E5%90%88/README.md
---

<!-- problem:start -->

# [剑指 Offer II 080. 含有 k 个元素的组合](https://leetcode.cn/problems/uUsW3B)

## 题目描述

<!-- description:start -->

<p>给定两个整数 <code>n</code> 和 <code>k</code>，返回 <code>1 ... n</code> 中所有可能的 <code>k</code> 个数的组合。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;n = 4, k = 2
<strong>输出:</strong>
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;n = 1, k = 1
<strong>输出: </strong>[[1]]</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 77&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/combinations/">https://leetcode.cn/problems/combinations/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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
        if (t.size() == k) {
            res.push_back(t);
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.push_back(j);
            dfs(j + 1, n, k, t, res);
            t.pop_back();
        }
    }
};
```

#### Go

```go
func combine(n int, k int) [][]int {
	var res [][]int
	var t []int
	dfs(1, n, k, t, &res)
	return res
}

func dfs(i, n, k int, t []int, res *[][]int) {
	if len(t) == k {
		*res = append(*res, slices.Clone(t))
		return
	}
	for j := i; j <= n; j++ {
		t = append(t, j)
		dfs(j+1, n, k, t, res)
		t = t[:len(t)-1]
	}
}
```

#### Swift

```swift
class Solution {
    func combine(_ n: Int, _ k: Int) -> [[Int]] {
        var res = [[Int]]()
        dfs(1, n, k, [], &res)
        return res
    }

    private func dfs(_ start: Int, _ n: Int, _ k: Int, _ current: [Int], _ res: inout [[Int]]) {
        if current.count == k {
            res.append(current)
            return
        }

        if start > n {
            return
        }

        for i in start...n {
            var newCurrent = current
            newCurrent.append(i)
            dfs(i + 1, n, k, newCurrent, &res)
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
