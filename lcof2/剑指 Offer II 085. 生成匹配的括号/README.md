---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20085.%20%E7%94%9F%E6%88%90%E5%8C%B9%E9%85%8D%E7%9A%84%E6%8B%AC%E5%8F%B7/README.md
---

# [剑指 Offer II 085. 生成匹配的括号](https://leetcode.cn/problems/IDBivT)

## 题目描述

<!-- 这里写题目描述 -->

<p>正整数&nbsp;<code>n</code>&nbsp;代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>[&quot;((()))&quot;,&quot;(()())&quot;,&quot;(())()&quot;,&quot;()(())&quot;,&quot;()()()&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>[&quot;()&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 22&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/generate-parentheses/">https://leetcode.cn/problems/generate-parentheses/</a></p>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def dfs(left, right, t):
            if left == n and right == n:
                ans.append(t)
                return
            if left < n:
                dfs(left + 1, right, t + '(')
            if right < left:
                dfs(left, right + 1, t + ')')

        ans = []
        dfs(0, 0, '')
        return ans
```

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

```go
func generateParenthesis(n int) []string {
	var ans []string
	var dfs func(left, right int, t string)
	dfs = func(left, right int, t string) {
		if left == n && right == n {
			ans = append(ans, t)
			return
		}
		if left < n {
			dfs(left+1, right, t+"(")
		}
		if right < left {
			dfs(left, right+1, t+")")
		}
	}
	dfs(0, 0, "")
	return ans
}
```

```ts
function generateParenthesis(n: number): string[] {
    let ans = [];
    let dfs = function (left, right, t) {
        if (left == n && right == n) {
            ans.push(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, t + '(');
        }
        if (right < left) {
            dfs(left, right + 1, t + ')');
        }
    };
    dfs(0, 0, '');
    return ans;
}
```

```js
/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function (n) {
    let ans = [];
    let dfs = function (left, right, t) {
        if (left == n && right == n) {
            ans.push(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, t + '(');
        }
        if (right < left) {
            dfs(left, right + 1, t + ')');
        }
    };
    dfs(0, 0, '');
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
