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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def dfs(ans, l, r, n):
            if len(ans) == (n << 1):
                self.res.append(ans)
                return
            if l < n:
                dfs(ans + '(', l + 1, r, n)
            if r < l:
                dfs(ans + ')', l, r + 1, n)
        
        self.res = []
        dfs('', 0, 0, n)
        return self.res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, "", 0, 0, n);
        return res;
    }

    private void dfs(List<String> res, String ans, int l, int r, int n) {
        if (ans.length() == (n << 1)) {
            res.add(ans);
            return;
        }
        if (l < n) {
            dfs(res, ans + "(", l + 1, r, n);
        }
        if (r < l) {
            dfs(res, ans + ")", l, r + 1, n);
        }
    }
}
```

### **TypeScript**

```ts
function generateParenthesis(n: number): string[] {
    let ans = [];
    dfs(n, 0, 0, '', ans);
    return ans;
};

function dfs(n: number, left: number, right: number, str: string, ans: string[]) {
    if (str.length == 2 * n) {
        ans.push(str);
        return;
    }
    if (left < n) {
        dfs(n, left + 1, right, str + '(', ans);
    }
    if (right < left) {
        dfs(n, left, right + 1, str + ')', ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        dfs(res, "", 0, 0, n);
        return res;
    }

private:
    void dfs(vector<string>& res, string ans, int l, int r, int n) {
        if (ans.size() == (n << 1)) {
            res.push_back(ans);
            return;
        }
        if (l < n) dfs(res, ans + "(", l + 1, r, n);
        if (r < l) dfs(res, ans + ")", l, r + 1, n);
    }
};
```

### **Go**

```go
func generateParenthesis(n int) []string {
	res := new([]string)
	dfs(res, "", 0, 0, n)
	return *res
}

func dfs(res *[]string, ans string, l, r, n int) {
	if len(ans) == (n << 1) {
		*res = append(*res, ans)
		return
	}
	if l < n {
		dfs(res, ans+"(", l+1, r, n)
	}
	if r < l {
		dfs(res, ans+")", l, r+1, n)
	}
}
```

### **...**

```

```

<!-- tabs:end -->
