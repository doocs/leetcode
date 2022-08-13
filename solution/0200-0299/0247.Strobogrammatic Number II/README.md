# [247. 中心对称数 II](https://leetcode.cn/problems/strobogrammatic-number-ii)

[English Version](/solution/0200-0299/0247.Strobogrammatic%20Number%20II/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
                for l, r in [['1', '1'], ['8', '8'], ['6', '9'], ['9', '6']]:
                    ans.append(l + v + r)
                if u != n:
                    ans.append('0' + v + '0')
            return ans

        return dfs(n)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
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
        int[][] pairs = new int[][]{{1, 1}, {8, 8}, {6, 9}, {9, 6}};
        for (String v : dfs(u - 2)) {
            for (int[] p : pairs) {
                ans.add(p[0] + v + p[1]);
            }
            if (u != n) {
                ans.add("0" + v + "0");
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;
    vector<string> findStrobogrammatic(int n) {
        this->n = n;
        return dfs(n);
    }

    vector<string> dfs(int u) {
        if (u == 0) return {""};
        if (u == 1) return {"0", "1", "8"};
        vector<string> ans;
        vector<vector<char>> pairs = {{'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
        for (string v : dfs(u - 2)) {
            for (auto& p : pairs) ans.push_back({p[0] + v + p[1]});
            if (u != n) ans.push_back('0' + v + '0');
        }
        return ans;
    }
};
```

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
