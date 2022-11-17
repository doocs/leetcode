# [2468. 根据限制分割消息](https://leetcode.cn/problems/split-message-based-on-limit)

[English Version](/solution/2400-2499/2468.Split%20Message%20Based%20on%20Limit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>message</code>&nbsp;和一个正整数&nbsp;<code>limit</code>&nbsp;。</p>

<p>你需要根据 <code>limit</code>&nbsp;将&nbsp;<code>message</code> <strong>分割</strong>&nbsp;成一个或多个 <strong>部分</strong>&nbsp;。每个部分的结尾都是&nbsp;<code>"&lt;a/b&gt;"</code>&nbsp;，其中&nbsp;<code>"b"</code>&nbsp;用分割出来的总数 <b>替换</b>，&nbsp;<code>"a"</code>&nbsp;用当前部分所在的编号 <strong>替换</strong>&nbsp;，编号从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>b</code>&nbsp;依次编号。除此以外，除了最后一部分长度 <strong>小于等于</strong>&nbsp;<code>limit</code>&nbsp;以外，其他每一部分（包括结尾部分）的长度都应该&nbsp;<strong>等于</strong>&nbsp;<code>limit</code>&nbsp;。</p>

<p>你需要确保分割后的结果数组，删掉每部分的结尾并<strong>&nbsp;按顺序&nbsp;</strong>连起来后，能够得到&nbsp;<code>message</code>&nbsp;。同时，结果数组越短越好。</p>

<p>请你返回<em>&nbsp;</em><code>message</code>&nbsp; 分割后得到的结果数组。如果无法按要求分割&nbsp;<code>message</code>&nbsp;，返回一个空数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>message = "this is really a very awesome message", limit = 9
<b>输出：</b>["thi&lt;1/14&gt;","s i&lt;2/14&gt;","s r&lt;3/14&gt;","eal&lt;4/14&gt;","ly &lt;5/14&gt;","a v&lt;6/14&gt;","ery&lt;7/14&gt;"," aw&lt;8/14&gt;","eso&lt;9/14&gt;","me&lt;10/14&gt;"," m&lt;11/14&gt;","es&lt;12/14&gt;","sa&lt;13/14&gt;","ge&lt;14/14&gt;"]
<strong>解释：</strong>
前面 9 个部分分别从 message 中得到 3 个字符。
接下来的 5 个部分分别从 message 中得到 2 个字符。
这个例子中，包含最后一个部分在内，每个部分的长度都为 9 。
可以证明没有办法分割成少于 14 个部分。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>message = "short message", limit = 15
<b>输出：</b>["short mess&lt;1/2&gt;","age&lt;2/2&gt;"]
<strong>解释：</strong>
在给定限制下，字符串可以分成两个部分：
- 第一个部分包含 10 个字符，长度为 15 。
- 第二个部分包含 3 个字符，长度为 8 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= message.length &lt;= 10<sup>4</sup></code></li>
	<li><code>message</code>&nbsp;只包含小写英文字母和&nbsp;<code>' '</code>&nbsp;。</li>
	<li><code>1 &lt;= limit &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举分段数量 + 模拟**

我们设字符串 `message` 的长度为 $n$，分段数量为 $k$。

根据题意，如果 $k \gt n$，表示我们可以将字符串划分成超过 $n$ 段，由于字符串长度仅为 $n$，若划分成超过 $n$ 段必然导致有部分段的长度为 $0$，可以删去。因此，我们只需要将 $k$ 的取值范围限制在 $[1,.. n]$ 即可。

从小到大枚举分段数量 $k$，记所有分段中 $a$ 段的长度为 $sa$，所有分段中 $b$ 段的长度为 $sb$，所有分段中符号（包括尖括号和斜杠）的长度为 $sc$。

那么 $sa$ 的值为 ${\textstyle \sum_{j=1}^{k}} len(s_j)$，可以直接通过前缀和求出；而 $sb$ 的值为 $len(str(k)) \times k$；另外 $sc$ 的值为 $3 \times k$。

因此，所有分段剩余可填充的字符数为 $limit\times k - (sa + sb + sc)$，如果该值大于等于 $n$ 则说明可以将字符串划分成 $k$ 段，直接构造答案返回即可。

时间复杂度 $O(n\times \log n)$，其中 $n$ 为字符串 `message` 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def splitMessage(self, message: str, limit: int) -> List[str]:
        n = len(message)
        sa = 0
        for k in range(1, n + 1):
            sa += len(str(k))
            sb = len(str(k)) * k
            sc = 3 * k
            if limit * k - (sa + sb + sc) >= n:
                ans = []
                i = 0
                for j in range(1, k + 1):
                    tail = f'<{j}/{k}>'
                    t = message[i: i + limit - len(tail)] + tail
                    ans.append(t)
                    i += limit - len(tail)
                return ans
        return []
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] splitMessage(String message, int limit) {
        int n = message.length();
        int sa = 0;
        String[] ans = new String[0];
        for (int k = 1; k <= n; ++k) {
            int lk = (k + "").length();
            sa += lk;
            int sb = lk * k;
            int sc = 3 * k;
            if (limit * k - (sa + sb + sc) >= n) {
                int i = 0;
                ans = new String[k];
                for (int j = 1; j <= k; ++j) {
                    String tail = String.format("<%d/%d>", j, k);
                    String t = message.substring(i, Math.min(n, i + limit - tail.length())) + tail;
                    ans[j - 1] = t;
                    i += limit - tail.length();
                }
                break;
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
    vector<string> splitMessage(string message, int limit) {
        int n = message.size();
        int sa = 0;
        vector<string> ans;
        for (int k = 1; k <= n; ++k) {
            int lk = to_string(k).size();
            sa += lk;
            int sb = lk * k;
            int sc = 3 * k;
            if (k * limit - (sa + sb + sc) >= n) {
                int i = 0;
                for (int j = 1; j <= k; ++j) {
                    string tail = "<" + to_string(j) + "/" + to_string(k) + ">";
                    string t = message.substr(i, limit - tail.size()) + tail;
                    ans.emplace_back(t);
                    i += limit - tail.size();
                }
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func splitMessage(message string, limit int) (ans []string) {
	n := len(message)
	sa := 0
	for k := 1; k <= n; k++ {
		lk := len(strconv.Itoa(k))
		sa += lk
		sb := lk * k
		sc := 3 * k
		if limit*k-(sa+sb+sc) >= n {
			i := 0
			for j := 1; j <= k; j++ {
				tail := "<" + strconv.Itoa(j) + "/" + strconv.Itoa(k) + ">"
				t := message[i:min(i+limit-len(tail), n)] + tail
				ans = append(ans, t)
				i += limit - len(tail)
			}
			break
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
