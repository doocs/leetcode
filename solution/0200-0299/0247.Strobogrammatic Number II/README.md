# [247. 中心对称数 II 🔒](https://leetcode.cn/problems/strobogrammatic-number-ii)

[English Version](/solution/0200-0299/0247.Strobogrammatic%20Number%20II/README_EN.md)

<!-- tags:递归,数组,字符串 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数&nbsp;<code>n</code>&nbsp;，返回所有长度为&nbsp;<code>n</code>&nbsp;的 <strong>中心对称数</strong>&nbsp;。你可以以 <strong>任何顺序</strong> 返回答案。</p>

<p><strong>中心对称数&nbsp;</strong>是一个数字在旋转了&nbsp;<code>180</code> 度之后看起来依旧相同的数字（或者上下颠倒地看）。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b>n = 2
<b>输出：</b>["11","69","88","96"]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<b>输入：</b>n = 1
<b>输出：</b>["0","1","8"]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 14</code></li>
</ul>

## 解法

### 方法一：递归

若长度为 $1$，则中心对称数只有 $0, 1, 8$；若长度为 $2$，则中心对称数只有 $11, 69, 88, 96$。

我们设计递归函数 $dfs(u)$，其返回长度为 $u$ 的中心对称数。答案为 $dfs(n)$。

若 $u$ 为 $0$，返回包含一个空串的列表，即 `[""]`；若 $u$ 为 $1$，返回列表 `["0", "1", "8"]`。

若 $u$ 大于 $1$，我们对长度为 $u - 2$ 的所有中心对称数进行遍历，对于每个中心对称数 $v$，在其左右两侧分别添加 $1, 8, 6, 9$，即可得到长度为 `u` 的中心对称数。

注意，如果 $u\neq n$，我们还可以在中心对称数的左右两侧分别添加 $0$。

最终，我们将所有长度为 $n$ 的中心对称数返回即可。

时间复杂度为 $O(2^{n+2})$。

相似题目：

-   [248. 中心对称数 III 🔒](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0248.Strobogrammatic%20Number%20III/README.md)

<!-- tabs:start -->

```python
class Solution:
    def findStrobogrammatic(self, n: int) -> List[str]:
        def dfs(u):
            if u == 0:
                return ['']
            if u == 1:
                return ['0', '1', '8']
            ans = []
            for v in dfs(u - 2):
                for l, r in ('11', '88', '69', '96'):
                    ans.append(l + v + r)
                if u != n:
                    ans.append('0' + v + '0')
            return ans

        return dfs(n)
```

```java
class Solution {
    private static final int[][] PAIRS = {{1, 1}, {8, 8}, {6, 9}, {9, 6}};
    private int n;

    public List<String> findStrobogrammatic(int n) {
        this.n = n;
        return dfs(n);
    }

    private List<String> dfs(int u) {
        if (u == 0) {
            return Collections.singletonList("");
        }
        if (u == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ans = new ArrayList<>();
        for (String v : dfs(u - 2)) {
            for (var p : PAIRS) {
                ans.add(p[0] + v + p[1]);
            }
            if (u != n) {
                ans.add(0 + v + 0);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    const vector<pair<char, char>> pairs = {{'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};

    vector<string> findStrobogrammatic(int n) {
        function<vector<string>(int)> dfs = [&](int u) {
            if (u == 0) return vector<string>{""};
            if (u == 1) return vector<string>{"0", "1", "8"};
            vector<string> ans;
            for (auto& v : dfs(u - 2)) {
                for (auto& [l, r] : pairs) ans.push_back(l + v + r);
                if (u != n) ans.push_back('0' + v + '0');
            }
            return ans;
        };
        return dfs(n);
    }
};
```

```go
func findStrobogrammatic(n int) []string {
	var dfs func(int) []string
	dfs = func(u int) []string {
		if u == 0 {
			return []string{""}
		}
		if u == 1 {
			return []string{"0", "1", "8"}
		}
		var ans []string
		pairs := [][]string{{"1", "1"}, {"8", "8"}, {"6", "9"}, {"9", "6"}}
		for _, v := range dfs(u - 2) {
			for _, p := range pairs {
				ans = append(ans, p[0]+v+p[1])
			}
			if u != n {
				ans = append(ans, "0"+v+"0")
			}
		}
		return ans
	}
	return dfs(n)
}
```

<!-- tabs:end -->

<!-- end -->
