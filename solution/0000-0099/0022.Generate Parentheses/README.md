# [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses)

[English Version](/solution/0000-0099/0022.Generate%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>数字 <code>n</code> 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>["((()))","(()())","(())()","()(())","()()()"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>["()"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 8</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索 DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        ans = []

        def dfs(left, right, t):
            if left == n and right == n:
                ans.append(t)
                return
            if left < n:
                dfs(left + 1, right, t + '(')
            if right < left:
                dfs(left, right + 1, t + ')')

        dfs(0, 0, '')
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(0, 0, n, "", ans);
        return ans;
    }

    private void dfs(int left, int right, int n, String t, List<String> ans) {
        if (left == n && right == n) {
            ans.add(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, n, t + "(", ans);
        }
        if (right < left) {
            dfs(left, right + 1, n, t + ")", ans);
        }
    }
}
```

### **TypeScript**

```ts
function generateParenthesis(n: number): string[] {
    let ans = [];
    dfs(0, 0, n, '', ans);
    return ans;
};

function dfs(left: number, right: number, n: number, t: string, ans: string[]) {
    if (left == n && right == n) {
        ans.push(t);
        return;
    }
    if (left < n) {
        dfs(left + 1, right, n, t + '(', ans);
    }
    if (right < left) {
        dfs(left, right + 1, n, t + ')', ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        dfs(0, 0, n, "", ans);
        return ans;
    }

    void dfs(int left, int right, int n, string t, vector<string>& ans) {
        if (left == n && right == n)
        {
            ans.push_back(t);
            return;
        }
        if (left < n) dfs(left + 1, right, n, t + "(", ans);
        if (right < left) dfs(left, right + 1, n, t + ")", ans);
    }
};
```

### **Go**

```go
func generateParenthesis(n int) []string {
	var ans []string
	dfs(0, 0, n, "", &ans)
	return ans
}

func dfs(left, right, n int, t string, ans *[]string) {
	if left == n && right == n {
		*ans = append(*ans, t)
		return
	}
	if left < n {
		dfs(left+1, right, n, t+"(", ans)
	}
	if right < left {
		dfs(left, right+1, n, t+")", ans)
	}
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {string[]}
 */
 var generateParenthesis = function(n) {
    let res = [];
    dfs(n, 0, 0, '', res);
    return res;
};

function dfs(n, left, right, prev, res) {
    if (left == n && right == n) {
        res.push(prev);
        return;
    }
    if (left < n) {
        dfs(n, left + 1, right, prev + '(', res);
    }
    if (right < left) {
        dfs(n, left, right + 1, prev + ')', res);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
