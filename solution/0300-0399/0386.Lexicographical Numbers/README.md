# [386. 字典序排数](https://leetcode-cn.com/problems/lexicographical-numbers)

[English Version](/solution/0300-0399/0386.Lexicographical%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        res = []

        def dfs(i, n):
            if i > n:
                return
            res.append(i)
            for j in range(10):
                dfs(i * 10 + j, n)

        for i in range(1, 10):
            dfs(i, n)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(res, i, n);
        }
        return res;
    }

    private void dfs(List<Integer> res, int i, int n) {
        if (i > n) {
            return;
        }
        res.add(i);
        for (int j = 0; j < 10; ++j) {
            dfs(res, i * 10 + j, n);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> res;
        for (int i = 1; i < 10; ++i)
        {
            dfs(res, i, n);
        }
        return res;
    }

    void dfs(vector<int> &res, int i, int n) {
        if (i > n)
            return;
        res.push_back(i);
        for (int j = 0; j < 10; ++j)
        {
            dfs(res, i * 10 + j, n);
        }
    }
};
```

### **Go**

```go
func lexicalOrder(n int) []int {
	var res []int
	var dfs func(int, int)
	dfs = func(i, n int) {
		if i > n {
			return
		}
		res = append(res, i)
		for j := 0; j < 10; j++ {
			dfs(i*10+j, n)
		}
	}

	for i := 1; i < 10; i++ {
		dfs(i, n)
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
