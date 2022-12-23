# [1138. 字母板上的路径](https://leetcode.cn/problems/alphabet-board-path)

[English Version](/solution/1100-1199/1138.Alphabet%20Board%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们从一块字母板上的位置&nbsp;<code>(0, 0)</code>&nbsp;出发，该坐标对应的字符为&nbsp;<code>board[0][0]</code>。</p>

<p>在本题里，字母板为<code>board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]</code>，如下所示。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1138.Alphabet%20Board%20Path/images/azboard.png" style="width: 300px;" /></p>

<p>我们可以按下面的指令规则行动：</p>

<ul>
	<li>如果方格存在，<code>'U'</code>&nbsp;意味着将我们的位置上移一行；</li>
	<li>如果方格存在，<code>'D'</code>&nbsp;意味着将我们的位置下移一行；</li>
	<li>如果方格存在，<code>'L'</code>&nbsp;意味着将我们的位置左移一列；</li>
	<li>如果方格存在，<code>'R'</code>&nbsp;意味着将我们的位置右移一列；</li>
	<li><code>'!'</code>&nbsp;会把在我们当前位置 <code>(r, c)</code> 的字符&nbsp;<code>board[r][c]</code>&nbsp;添加到答案中。</li>
</ul>

<p>（注意，字母板上只存在有字母的位置。）</p>

<p>返回指令序列，用最小的行动次数让答案和目标&nbsp;<code>target</code>&nbsp;相同。你可以返回任何达成目标的路径。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = "leet"
<strong>输出：</strong>"DDR!UURRR!!DDD!"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = "code"
<strong>输出：</strong>"RR!DDRR!UUL!R!"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>target</code>&nbsp;仅含有小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

从起点 $(0, 0)$ 出发，模拟每一步的移动，将每一步的移动结果拼接到答案中。注意移动的方向遵循“左、上、右、下”的顺序。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $target$ 的长度，需要遍历字符串 $target$ 中的每一个字符。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def alphabetBoardPath(self, target: str) -> str:
        i = j = 0
        ans = []
        for c in target:
            v = ord(c) - ord("a")
            x, y = v // 5, v % 5
            while j > y:
                j -= 1
                ans.append("L")
            while i > x:
                i -= 1
                ans.append("U")
            while j < y:
                j += 1
                ans.append("R")
            while i < x:
                i += 1
                ans.append("D")
            ans.append("!")
        return "".join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String alphabetBoardPath(String target) {
        StringBuilder ans = new StringBuilder();
        int i = 0, j = 0;
        for (int k = 0; k < target.length(); ++k) {
            int v = target.charAt(k) - 'a';
            int x = v / 5, y = v % 5;
            while (j > y) {
                --j;
                ans.append('L');
            }
            while (i > x) {
                --i;
                ans.append('U');
            }
            while (j < y) {
                ++j;
                ans.append('R');
            }
            while (i < x) {
                ++i;
                ans.append('D');
            }
            ans.append("!");
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string alphabetBoardPath(string target) {
        string ans;
        int i = 0, j = 0;
        for (char& c : target) {
            int v = c - 'a';
            int x = v / 5, y = v % 5;
            while (j > y) {
                --j;
                ans += 'L';
            }
            while (i > x) {
                --i;
                ans += 'U';
            }
            while (j < y) {
                ++j;
                ans += 'R';
            }
            while (i < x) {
                ++i;
                ans += 'D';
            }
            ans += '!';
        }
        return ans;
    }
};
```

### **Go**

```go
func alphabetBoardPath(target string) string {
	ans := []byte{}
	var i, j int
	for _, c := range target {
		v := int(c - 'a')
		x, y := v/5, v%5
		for j > y {
			j--
			ans = append(ans, 'L')
		}
		for i > x {
			i--
			ans = append(ans, 'U')
		}
		for j < y {
			j++
			ans = append(ans, 'R')
		}
		for i < x {
			i++
			ans = append(ans, 'D')
		}
		ans = append(ans, '!')
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
