---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0386.Lexicographical%20Numbers/README.md
tags:
    - 深度优先搜索
    - 字典树
---

<!-- problem:start -->

# [386. 字典序排数](https://leetcode.cn/problems/lexicographical-numbers)

[English Version](/solution/0300-0399/0386.Lexicographical%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，按字典序返回范围 <code>[1, n]</code> 内所有整数。</p>

<p>你必须设计一个时间复杂度为 <code>O(n)</code> 且使用 <code>O(1)</code> 额外空间的算法。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>[1,10,11,12,13,2,3,4,5,6,7,8,9]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>[1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- tabs:start -->

```python
class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        def dfs(u):
            if u > n:
                return
            ans.append(u)
            for i in range(10):
                dfs(u * 10 + i)

        ans = []
        for i in range(1, 10):
            dfs(i)
        return ans
```

```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, ans);
        }
        return ans;
    }

    private void dfs(int u, int n, List<Integer> ans) {
        if (u > n) {
            return;
        }
        ans.add(u);
        for (int i = 0; i < 10; ++i) {
            dfs(u * 10 + i, n, ans);
        }
    }
}
```

```cpp
class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> ans;
        for (int i = 1; i < 10; ++i) dfs(i, n, ans);
        return ans;
    }

    void dfs(int u, int n, vector<int>& ans) {
        if (u > n) return;
        ans.push_back(u);
        for (int i = 0; i < 10; ++i) dfs(u * 10 + i, n, ans);
    }
};
```

```go
func lexicalOrder(n int) []int {
	var ans []int
	var dfs func(u int)
	dfs = func(u int) {
		if u > n {
			return
		}
		ans = append(ans, u)
		for i := 0; i < 10; i++ {
			dfs(u*10 + i)
		}
	}
	for i := 1; i < 10; i++ {
		dfs(i)
	}
	return ans
}
```

```rust
impl Solution {
    fn dfs(mut num: i32, n: i32, res: &mut Vec<i32>) {
        if num > n {
            return;
        }
        res.push(num);
        for i in 0..10 {
            Self::dfs(num * 10 + i, n, res);
        }
    }

    pub fn lexical_order(n: i32) -> Vec<i32> {
        let mut res = vec![];
        for i in 1..10 {
            Self::dfs(i, n, &mut res);
        }
        res
    }
}
```

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var lexicalOrder = function (n) {
    let ans = [];
    function dfs(u) {
        if (u > n) {
            return;
        }
        ans.push(u);
        for (let i = 0; i < 10; ++i) {
            dfs(u * 10 + i);
        }
    }
    for (let i = 1; i < 10; ++i) {
        dfs(i);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int v = 1;
        for (int i = 0; i < n; ++i) {
            ans.add(v);
            if (v * 10 <= n) {
                v *= 10;
            } else {
                while (v % 10 == 9 || v + 1 > n) {
                    v /= 10;
                }
                ++v;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> ans;
        int v = 1;
        for (int i = 0; i < n; ++i) {
            ans.push_back(v);
            if (v * 10 <= n)
                v *= 10;
            else {
                while (v % 10 == 9 || v + 1 > n) v /= 10;
                ++v;
            }
        }
        return ans;
    }
};
```

```go
func lexicalOrder(n int) []int {
	var ans []int
	v := 1
	for i := 0; i < n; i++ {
		ans = append(ans, v)
		if v*10 <= n {
			v *= 10
		} else {
			for v%10 == 9 || v+1 > n {
				v /= 10
			}
			v++
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
