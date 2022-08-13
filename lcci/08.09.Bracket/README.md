# [面试题 08.09. 括号](https://leetcode.cn/problems/bracket-lcci)

[English Version](/lcci/08.09.Bracket/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。</p>

<p>说明：解集不能包含重复的子集。</p>

<p>例如，给出 n = 3，生成结果为：</p>

<pre>
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
</pre>

## 解法

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
}

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
        if (left == n && right == n) {
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
var generateParenthesis = function (n) {
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

### **Rust**

```rust
impl Solution {
    fn dfs(left: i32, right: i32, t: String, res: &mut Vec<String>) {
        if left == 0 && right == 0 {
            res.push(t);
            return;
        }
        if left > 0 {
            Self::dfs(left - 1, right, format!("{}(", t), res);
        }
        if left < right {
            Self::dfs(left, right - 1, format!("{})", t), res);
        }
    }

    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut res = vec![];
        Self::dfs(n, n, String::new(), &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
