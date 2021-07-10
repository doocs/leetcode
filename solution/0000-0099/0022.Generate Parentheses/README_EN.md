# [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses)

[中文文档](/solution/0000-0099/0022.Generate%20Parentheses/README.md)

## Description

<p>Given <code>n</code> pairs of parentheses, write a function to <em>generate all combinations of well-formed parentheses</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> n = 3
<strong>Output:</strong> ["((()))","(()())","(())()","()(())","()()()"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> n = 1
<strong>Output:</strong> ["()"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

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
