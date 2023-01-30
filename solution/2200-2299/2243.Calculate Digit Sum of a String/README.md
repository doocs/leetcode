# [2243. 计算字符串的数字和](https://leetcode.cn/problems/calculate-digit-sum-of-a-string)

[English Version](/solution/2200-2299/2243.Calculate%20Digit%20Sum%20of%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由若干数字（<code>0</code> - <code>9</code>）组成的字符串 <code>s</code> ，和一个整数。</p>

<p>如果 <code>s</code> 的长度大于 <code>k</code> ，则可以执行一轮操作。在一轮操作中，需要完成以下工作：</p>

<ol>
	<li>将 <code>s</code> <strong>拆分 </strong>成长度为 <code>k</code> 的若干 <strong>连续数字组</strong> ，使得前 <code>k</code> 个字符都分在第一组，接下来的 <code>k</code> 个字符都分在第二组，依此类推。<strong>注意</strong>，最后一个数字组的长度可以小于 <code>k</code> 。</li>
	<li>用表示每个数字组中所有数字之和的字符串来 <strong>替换</strong> 对应的数字组。例如，<code>"346"</code> 会替换为 <code>"13"</code> ，因为 <code>3 + 4 + 6 = 13</code> 。</li>
	<li><strong>合并</strong> 所有组以形成一个新字符串。如果新字符串的长度大于 <code>k</code> 则重复第一步。</li>
</ol>

<p>返回在完成所有轮操作后的 <code>s</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "11111222223", k = 3
<strong>输出：</strong>"135"
<strong>解释：</strong>
- 第一轮，将 s 分成："111"、"112"、"222" 和 "23" 。
  接着，计算每一组的数字和：1 + 1 + 1 = 3、1 + 1 + 2 = 4、2 + 2 + 2 = 6 和 2 + 3 = 5 。 
&nbsp; 这样，s 在第一轮之后变成 "3" + "4" + "6" + "5" = "3465" 。
- 第二轮，将 s 分成："346" 和 "5" 。
&nbsp; 接着，计算每一组的数字和：3 + 4 + 6 = 13 、5 = 5 。
&nbsp; 这样，s 在第二轮之后变成 "13" + "5" = "135" 。 
现在，s.length &lt;= k ，所以返回 "135" 作为答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "00000000", k = 3
<strong>输出：</strong>"000"
<strong>解释：</strong>
将 "000", "000", and "00".
接着，计算每一组的数字和：0 + 0 + 0 = 0 、0 + 0 + 0 = 0 和 0 + 0 = 0 。 
s 变为 "0" + "0" + "0" = "000" ，其长度等于 k ，所以返回 "000" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= 100</code></li>
	<li><code>s</code> 仅由数字（<code>0</code> - <code>9</code>）组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意，我们可以模拟题目中的操作过程，直到字符串长度小于等于 $k$ 为止，最后返回字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            t = []
            n = len(s)
            for i in range(0, n, k):
                x = 0
                for j in range(i, min(i + k, n)):
                    x += int(s[j])
                t.append(str(x))
            s = "".join(t)
        return s
```

```python
class Solution:
    def digitSum(self, s: str, k: int) -> str:
        if len(s) <= k:
            return s
        t = []
        while s:
            t.append(str(sum(int(v) for v in s[:k])))
            s = s[k:]
        return self.digitSum(''.join(t), k)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < n; i += k) {
                int x = 0;
                for (int j = i; j < Math.min(i + k, n); ++j) {
                    x += s.charAt(j) - '0';
                }
                t.append(x);
            }
            s = t.toString();
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string digitSum(string s, int k) {
        while (s.size() > k) {
            string t;
            int n = s.size();
            for (int i = 0; i < n; i += k) {
                int x = 0;
                for (int j = i; j < min(i + k, n); ++j) {
                    x += s[j] - '0';
                }
                t += to_string(x);
            }
            s = t;
        }
        return s;
    }
};
```

### **Go**

```go
func digitSum(s string, k int) string {
	for len(s) > k {
		t := &strings.Builder{}
		n := len(s)
		for i := 0; i < n; i += k {
			x := 0
			for j := i; j < i+k && j < n; j++ {
				x += int(s[j] - '0')
			}
			t.WriteString(strconv.Itoa(x))
		}
		s = t.String()
	}
	return s
}
```

### **TypeScript**

```ts
function digitSum(s: string, k: number): string {
    let ans = [];
    while (s.length > k) {
        for (let i = 0; i < s.length; i += k) {
            let cur = s.slice(i, i + k);
            ans.push(cur.split('').reduce((a, c) => a + parseInt(c), 0));
        }
        s = ans.join('');
        ans = [];
    }
    return s;
}
```

### **...**

```

```

<!-- tabs:end -->
