# [859. 亲密字符串](https://leetcode-cn.com/problems/buddy-strings)

[English Version](/solution/0800-0899/0859.Buddy%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个由小写字母构成的字符串 <code>A</code> 和 <code>B</code> ，只要我们可以通过交换 <code>A</code> 中的两个字母得到与 <code>B</code> 相等的结果，就返回 <code>true</code> ；否则返回 <code>false</code> 。</p>

<p>交换字母的定义是取两个下标 <code>i</code> 和 <code>j</code> （下标从 <code>0</code> 开始），只要 <code>i!=j</code> 就交换 <code>A[i]</code> 和 <code>A[j]</code> 处的字符。例如，在 <code>"abcd"</code> 中交换下标 <code>0</code> 和下标 <code>2</code> 的元素可以生成 <code>"cbad"</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入： </strong>A = "ab", B = "ba"
<strong>输出： </strong>true<strong>
解释： </strong>你可以交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 相等。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入： </strong>A = "ab", B = "ab"
<strong>输出： </strong>false
<strong>解释： </strong>你只能交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 不相等。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入： </strong>A = "aa", B = "aa"
<strong>输出： </strong>true
<strong>解释： </strong>你可以交换 A[0] = 'a' 和 A[1] = 'a' 生成 "aa"，此时 A 和 B 相等。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入： </strong>A = "aaaaaaabc", B = "aaaaaaacb"
<strong>输出： </strong>true
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入： </strong>A = "", B = "aa"
<strong>输出： </strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 <= A.length <= 20000</code></li>
	<li><code>0 <= B.length <= 20000</code></li>
	<li><code>A</code> 和 <code>B</code> 仅由小写字母构成。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def buddyStrings(self, s: str, goal: str) -> bool:
        m, n = len(s), len(goal)
        if m != n:
            return False
        diff = sum(1 for i in range(n) if s[i] != goal[i])
        cnt1, cnt2 = Counter(s), Counter(goal)
        if cnt1 != cnt2:
            return False
        return diff == 2 or (diff == 0 and any(e > 1 for e in cnt1.values()))
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
        for (int i = 0; i < n; ++i)
        {
            ++cnt1[s[i] - 'a'];
            ++cnt2[goal[i] - 'a'];
            if (s[i] != goal[i]) ++diff;
        }
        bool f = false;
        for (int i = 0; i < 26; ++i)
        {
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

### **...**

```

```

<!-- tabs:end -->
