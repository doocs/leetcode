# [386. 字典序排数](https://leetcode-cn.com/problems/lexicographical-numbers)

[English Version](/solution/0300-0399/0386.Lexicographical%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数&nbsp;<em>n</em>, 返回从&nbsp;<em>1&nbsp;</em>到&nbsp;<em>n&nbsp;</em>的字典顺序。</p>

<p>例如，</p>

<p>给定 <em>n</em> =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。</p>

<p>请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据&nbsp;<em>n&nbsp;</em>小于等于&nbsp;5,000,000。</p>

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
