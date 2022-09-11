# [859. 亲密字符串](https://leetcode.cn/problems/buddy-strings)

[English Version](/solution/0800-0899/0859.Buddy%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>s</code> 和 <code>goal</code> ，只要我们可以通过交换 <code>s</code> 中的两个字母得到与 <code>goal</code> 相等的结果，就返回&nbsp;<code>true</code>&nbsp;；否则返回 <code>false</code> 。</p>

<p>交换字母的定义是：取两个下标 <code>i</code> 和 <code>j</code> （下标从 <code>0</code> 开始）且满足 <code>i != j</code> ，接着交换 <code>s[i]</code> 和 <code>s[j]</code> 处的字符。</p>

<ul>
	<li>例如，在 <code>"abcd"</code> 中交换下标 <code>0</code> 和下标 <code>2</code> 的元素可以生成 <code>"cbad"</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ab", goal = "ba"
<strong>输出：</strong>true
<strong>解释：</strong>你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ab", goal = "ab"
<strong>输出：</strong>false
<strong>解释：</strong>你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", goal = "aa"
<strong>输出：</strong>true
<strong>解释：</strong>你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, goal.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>s</code> 和 <code>goal</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符统计**

首先，先理解亲密字符串的意思：

-   若两个字符串的长度或字符出现的频数不等，一定不是亲密字符串；
-   若两个字符串对应位置不相等的字符数量为 2，或者数量为 0 并且字符串存在两个相同字符，则是亲密字符串。

因此，我们先判断两个字符串长度，若不等，直接返回 `false`。

接着，统计两个字符串的字符频数，记为 `cnt1` 和 `cnt2`，若 `cnt1` 不等于 `cnt2`，直接返回 `false`。

然后枚举两个字符串，统计对应位置不相等的字符数量，若为 2，则返回 `true`；若为 0，且字符串存在两个相同字符，则返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 `s` 或 `goal` 的长度；而 $C$ 为字符集大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def buddyStrings(self, s: str, goal: str) -> bool:
        m, n = len(s), len(goal)
        if m != n:
            return False
        cnt1, cnt2 = Counter(s), Counter(goal)
        if cnt1 != cnt2:
            return False
        diff = sum(s[i] != goal[i] for i in range(n))
        return diff == 2 or (diff == 0 and any(v > 1 for v in cnt1.values()))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean buddyStrings(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        int diff = 0;
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            int a = s.charAt(i), b = goal.charAt(i);
            ++cnt1[a - 'a'];
            ++cnt2[b - 'a'];
            if (a != b) {
                ++diff;
            }
        }
        boolean f = false;
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
            if (cnt1[i] > 1) {
                f = true;
            }
        }
        return diff == 2 || (diff == 0 && f);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool buddyStrings(string s, string goal) {
        int m = s.size(), n = goal.size();
        if (m != n) return false;
        int diff = 0;
        vector<int> cnt1(26);
        vector<int> cnt2(26);
        for (int i = 0; i < n; ++i) {
            ++cnt1[s[i] - 'a'];
            ++cnt2[goal[i] - 'a'];
            if (s[i] != goal[i]) ++diff;
        }
        bool f = false;
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] != cnt2[i]) return false;
            if (cnt1[i] > 1) f = true;
        }
        return diff == 2 || (diff == 0 && f);
    }
};
```

### **Go**

```go
func buddyStrings(s string, goal string) bool {
	m, n := len(s), len(goal)
	if m != n {
		return false
	}
	diff := 0
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for i := 0; i < n; i++ {
		cnt1[s[i]-'a']++
		cnt2[goal[i]-'a']++
		if s[i] != goal[i] {
			diff++
		}
	}
	f := false
	for i := 0; i < 26; i++ {
		if cnt1[i] != cnt2[i] {
			return false
		}
		if cnt1[i] > 1 {
			f = true
		}
	}
	return diff == 2 || (diff == 0 && f)
}
```

### **TypeScript**

```ts
function buddyStrings(s: string, goal: string): boolean {
    const m = s.length;
    const n = goal.length;
    if (m != n) {
        return false;
    }
    const cnt1 = new Array(26).fill(0);
    const cnt2 = new Array(26).fill(0);
    let diff = 0;
    for (let i = 0; i < n; ++i) {
        cnt1[s.charCodeAt(i) - 'a'.charCodeAt(0)]++;
        cnt2[goal.charCodeAt(i) - 'a'.charCodeAt(0)]++;
        if (s[i] != goal[i]) {
            ++diff;
        }
    }
    for (let i = 0; i < 26; ++i) {
        if (cnt1[i] != cnt2[i]) {
            return false;
        }
    }
    return diff == 2 || (diff == 0 && cnt1.some(v => v > 1));
}
```

### **...**

```

```

<!-- tabs:end -->
