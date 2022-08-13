# [2193. 得到回文串的最少操作次数](https://leetcode.cn/problems/minimum-number-of-moves-to-make-palindrome)

[English Version](/solution/2100-2199/2193.Minimum%20Number%20of%20Moves%20to%20Make%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个只包含小写英文字母的字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>每一次 <strong>操作</strong>&nbsp;，你可以选择 <code>s</code>&nbsp;中两个 <strong>相邻</strong>&nbsp;的字符，并将它们交换。</p>

<p>请你返回将 <code>s</code>&nbsp;变成回文串的 <strong>最少操作次数</strong>&nbsp;。</p>

<p><strong>注意</strong>&nbsp;，输入数据会确保&nbsp;<code>s</code>&nbsp;一定能变成一个回文串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "aabb"
<b>输出：</b>2
<strong>解释：</strong>
我们可以将 s 变成 2 个回文串，"abba" 和 "baab" 。
- 我们可以通过 2 次操作得到 "abba" ："a<em><strong>ab</strong></em>b" -&gt; "ab<em><strong>ab</strong></em>" -&gt; "abba" 。
- 我们可以通过 2 次操作得到 "baab" ："a<em><strong>ab</strong></em>b" -&gt; "<em><strong>ab</strong></em>ab" -&gt; "baab" 。
因此，得到回文串的最少总操作次数为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "letelt"
<b>输出：</b>2
<strong>解释：</strong>
通过 2 次操作从 s 能得到回文串 "lettel" 。
其中一种方法是："lete<em><strong>lt</strong></em>" -&gt; "let<em><strong>et</strong></em>l" -&gt; "lettel" 。
其他回文串比方说 "tleelt" 也可以通过 2 次操作得到。
可以证明少于 2 次操作，无法得到回文串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>s</code>&nbsp;可以通过有限次操作得到一个回文串。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

由于题目保证原串一定可以变成回文串，那么原串中最多只有一种字母出现奇数次。如果有一种字母出现奇数次，那么将该字母中排在最中间的字符移动到字符串中间，剩下的字符可以转化为所有字母均出现偶数次的情况。

贪心算法是：每次固定字符串最左边的字母 $a$ 不变，找出距离字符串右侧最近的 $a$，把它交换到字符串最右边。这样字符串的头尾字母就相等了。把字符串的头尾去掉，就变成了子问题。把所有子问题的答案加起来就是最少交换次数。

由于数据范围较小，通过 ${O}(n^2)$ 的模拟即可通过本题。

证明：

构造回文串的过程，实际上是每次选择一对字母并把它们交换到字符串头尾的过程。考虑字母 $x$ 和字母 $y$ 哪个先选，分以下情况讨论：

-   字母 $x$ 和 $y$ 的位置满足 $\underbrace{\cdots}_{a\text{ 个}}x\underbrace{\cdots}_{b\text{ 个}}y\underbrace{\cdots}_{c\text{ 个}}y\underbrace{\cdots}_{d\text{ 个}}x\underbrace{\cdots}_{e\text{ 个}}$。如果先把 $x$ 换到头尾，再把 $y$ 换到头尾，那么需要 $(a + e) + (b + d)$ 次交换；如果先换 $y$ 再换 $x$，那么需要 $(a + b + 1 + d + e + 1) + (a + e)$ 次交换。显然先换 $x$ 更优。
-   字母 $x$ 和 $y$ 的位置满足 $\underbrace{\cdots}_{a\text{ 个}}x\underbrace{\cdots}_{b\text{ 个}}y\underbrace{\cdots}_{c\text{ 个}}x\underbrace{\cdots}_{d\text{ 个}}y\underbrace{\cdots}_{e\text{ 个}}$。如果先换 $x$ 再换 $y$，那么需要 $(a + d + e + 1) + (a + b + e)$ 次交换；如果先换 $y$ 再换 $x$，那么需要 $(a + b + 1 + e) + (a + d + e)$ 次交换。先换哪个都一样。
-   字母 $x$ 和 $y$ 的位置满足 $\underbrace{\cdots}_{a\text{ 个}}x\underbrace{\cdots}_{b\text{ 个}}x\underbrace{\cdots}_{c\text{ 个}}y\underbrace{\cdots}_{d\text{ 个}}y\underbrace{\cdots}_{e\text{ 个}}$。如果先换 $x$ 再换 $y$，那么需要 $(a + c + d + e + 2) + (a + b + c + e)$ 次交换；如果先换 $y$ 再换 $x$，那么需要 $(a + b + c + 2 + e) + (a + c + d + e)$ 次交换。先换哪个都一样。

上述讨论可以得到结论：每次交换最外边出现的字母不劣。因此贪心解法成立。

> 出处：https://leetcode.cn/problems/minimum-number-of-moves-to-make-palindrome/solution/tan-xin-zheng-ming-geng-da-shu-ju-fan-we-h57i/

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMovesToMakePalindrome(self, s: str) -> int:
        cs = list(s)
        ans, n = 0, len(s)
        i, j = 0, n - 1
        while i < j:
            even = False
            for k in range(j, i, -1):
                if cs[i] == cs[k]:
                    even = True
                    while k < j:
                        cs[k], cs[k + 1] = cs[k + 1], cs[k]
                        k += 1
                        ans += 1
                    j -= 1
                    break
            if not even:
                ans += n // 2 - i
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMovesToMakePalindrome(String s) {
        int n = s.length();
        int ans = 0;
        char[] cs = s.toCharArray();
        for (int i = 0, j = n - 1; i < j; ++i) {
            boolean even = false;
            for (int k = j; k != i; --k) {
                if (cs[i] == cs[k]) {
                    even = true;
                    for (; k < j; ++k) {
                        char t = cs[k];
                        cs[k] = cs[k + 1];
                        cs[k + 1] = t;
                        ++ans;
                    }
                    --j;
                    break;
                }
            }
            if (!even) {
                ans += n / 2 - i;
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
    int minMovesToMakePalindrome(string s) {
        int n = s.size();
        int ans = 0;
        for (int i = 0, j = n - 1; i < j; ++i) {
            bool even = false;
            for (int k = j; k != i; --k) {
                if (s[i] == s[k]) {
                    even = true;
                    for (; k < j; ++k) {
                        swap(s[k], s[k + 1]);
                        ++ans;
                    }
                    --j;
                    break;
                }
            }
            if (!even) ans += n / 2 - i;
        }
        return ans;
    }
};
```

### **Go**

```go
func minMovesToMakePalindrome(s string) int {
	cs := []byte(s)
	ans, n := 0, len(s)
	for i, j := 0, n-1; i < j; i++ {
		even := false
		for k := j; k != i; k-- {
			if cs[i] == cs[k] {
				even = true
				for ; k < j; k++ {
					cs[k], cs[k+1] = cs[k+1], cs[k]
					ans++
				}
				j--
				break
			}
		}
		if !even {
			ans += n/2 - i
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
