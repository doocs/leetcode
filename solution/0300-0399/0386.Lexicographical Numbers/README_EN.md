# [386. Lexicographical Numbers](https://leetcode.com/problems/lexicographical-numbers)

[中文文档](/solution/0300-0399/0386.Lexicographical%20Numbers/README.md)

## Description

<p>Given an integer <code>n</code>, return all the numbers in the range <code>[1, n]</code> sorted in lexicographical order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> n = 13
<strong>Output:</strong> [1,10,11,12,13,2,3,4,5,6,7,8,9]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> n = 2
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you optimize your solution to use <code>O(n)</code> runtime and <code>O(1)</code> space?</p>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

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
