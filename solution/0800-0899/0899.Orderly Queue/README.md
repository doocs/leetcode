# [899. 有序队列](https://leetcode.cn/problems/orderly-queue)

[English Version](/solution/0800-0899/0899.Orderly%20Queue/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> 和一个整数 <code>k</code>&nbsp;。你可以从 <code>s</code> 的前 <code>k</code> 个字母中选择一个，并把它加到字符串的末尾。</p>

<p>返回 <em>在应用上述步骤的任意数量的移动后，字典上最小的字符串&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "cba", k = 1
<strong>输出：</strong>"acb"
<strong>解释：</strong>
在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "baaca", k = 3
<strong>输出：</strong>"aaabc"
<strong>解释：
</strong>在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k&nbsp;&lt;= S.length&nbsp;&lt;= 1000</code></li>
	<li><code>s</code>&nbsp;只由小写字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**前言**

对于任何字符串，如果可以交换任意相邻字符，则可以对字符串中的字符做类似冒泡排序的操作，最终得到一个升序排列的字符串。

**方法一：分类判断**

若 $k=1$，我们每次只能将字符串首字符移动到字符串末尾，总共有 $s.length$ 种不同的状态，我们返回其中字典序最小的字符串即可。

若 $k\gt1$，对于形如 $abc[xy]def$ 的字符串，可以依次将 $a$, $b$, $c$ 移动到最后，得到 $[xy]defabc$，然后将 $y$, $x$ 移动到最后，得到 $defabc[yx]$，最后将 $d$, $e$, $f$ 移动到最后，得到 $abc[yx]def$，这样就实现了对 $y$, $x$ 的交换。

因此，只要 $k\gt1$，我们就能够交换字符串中的任何两个相邻字符，最终得到一个升序排列的字符串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def orderlyQueue(self, s: str, k: int) -> str:
        if k == 1:
            ans = s
            for _ in range(len(s) - 1):
                s = s[1:] + s[0]
                ans = min(ans, s)
            return ans
        return "".join(sorted(s))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            String ans = s;
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < s.length() - 1; ++i) {
                sb.append(sb.charAt(0)).deleteCharAt(0);
                if (sb.toString().compareTo(ans) < 0) {
                    ans = sb.toString();
                }
            }
            return ans;
        }
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        return String.valueOf(cs);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string orderlyQueue(string s, int k) {
        if (k == 1) {
            string ans = s;
            for (int i = 0; i < s.size() - 1; ++i) {
                s = s.substr(1) + s[0];
                if (s < ans) ans = s;
            }
            return ans;
        }
        sort(s.begin(), s.end());
        return s;
    }
};
```

### **Go**

```go
func orderlyQueue(s string, k int) string {
	if k == 1 {
		ans := s
		for i := 0; i < len(s)-1; i++ {
			s = s[1:] + s[:1]
			if s < ans {
				ans = s
			}
		}
		return ans
	}
	t := []byte(s)
	sort.Slice(t, func(i, j int) bool { return t[i] < t[j] })
	return string(t)
}
```

### **TypeScript**

```ts
function orderlyQueue(s: string, k: number): string {
    if (k > 1) {
        return [...s].sort().join('');
    }
    const n = s.length;
    let min = s;
    for (let i = 1; i < n; i++) {
        const t = s.slice(i) + s.slice(0, i);
        if (t < min) {
            min = t;
        }
    }
    return min;
}
```

### **...**

```

```

<!-- tabs:end -->
