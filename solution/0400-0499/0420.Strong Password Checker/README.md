# [420. 强密码检验器](https://leetcode.cn/problems/strong-password-checker)

[English Version](/solution/0400-0499/0420.Strong%20Password%20Checker/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>&nbsp;</p>
如果一个密码满足下述所有条件，则认为这个密码是强密码：

<ul>
	<li>由至少 <code>6</code> 个，至多 <code>20</code> 个字符组成。</li>
	<li>至少包含 <strong>一个小写 </strong>字母，<strong>一个大写</strong> 字母，和 <strong>一个数字</strong> 。</li>
	<li>同一字符 <strong>不能 </strong>连续出现三次 (比如 <code>"...aaa..."</code> 是不允许的, 但是&nbsp;<code>"...aa...a..."</code> 如果满足其他条件也可以算是强密码)。</li>
</ul>

<p>给你一个字符串 <code>password</code> ，返回&nbsp;<em>将 <code>password</code> 修改到满足强密码条件需要的最少修改步数。如果 <code>password</code> 已经是强密码，则返回 <code>0</code> 。</em></p>

<p>在一步修改操作中，你可以：</p>

<ul>
	<li>插入一个字符到 <code>password</code> ，</li>
	<li>从 <code>password</code> 中删除一个字符，或</li>
	<li>用另一个字符来替换 <code>password</code> 中的某个字符。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>password = "a"
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>password = "aA1"
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>password = "1337C0d3"
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= password.length &lt;= 50</code></li>
	<li><code>password</code> 由字母、数字、点 <code>'.'</code> 或者感叹号 <code>'!'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def strongPasswordChecker(self, password: str) -> int:
        def countTypes(s):
            a = b = c = 0
            for ch in s:
                if ch.islower():
                    a = 1
                elif ch.isupper():
                    b = 1
                elif ch.isdigit():
                    c = 1
            return a + b + c

        types = countTypes(password)
        n = len(password)
        if n < 6:
            return max(6 - n, 3 - types)
        if n <= 20:
            replace = cnt = 0
            prev = '~'
            for curr in password:
                if curr == prev:
                    cnt += 1
                else:
                    replace += cnt // 3
                    cnt = 1
                    prev = curr
            replace += cnt // 3
            return max(replace, 3 - types)
        replace = cnt = 0
        remove, remove2 = n - 20, 0
        prev = '~'
        for curr in password:
            if curr == prev:
                cnt += 1
            else:
                if remove > 0 and cnt >= 3:
                    if cnt % 3 == 0:
                        remove -= 1
                        replace -= 1
                    elif cnt % 3 == 1:
                        remove2 += 1
                replace += cnt // 3
                cnt = 1
                prev = curr
        if remove > 0 and cnt >= 3:
            if cnt % 3 == 0:
                remove -= 1
                replace -= 1
            elif cnt % 3 == 1:
                remove2 += 1
        replace += cnt // 3
        use2 = min(replace, remove2, remove // 2)
        replace -= use2
        remove -= use2 * 2

        use3 = min(replace, remove // 3)
        replace -= use3
        remove -= use3 * 3
        return n - 20 + max(replace, 3 - types)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int strongPasswordChecker(String password) {
        int types = countTypes(password);
        int n = password.length();
        if (n < 6) {
            return Math.max(6 - n, 3 - types);
        }
        char[] chars = password.toCharArray();
        if (n <= 20) {
            int replace = 0;
            int cnt = 0;
            char prev = '~';
            for (char curr : chars) {
                if (curr == prev) {
                    ++cnt;
                } else {
                    replace += cnt / 3;
                    cnt = 1;
                    prev = curr;
                }
            }
            replace += cnt / 3;
            return Math.max(replace, 3 - types);
        }
        int replace = 0, remove = n - 20;
        int remove2 = 0;
        int cnt = 0;
        char prev = '~';
        for (char curr : chars) {
            if (curr == prev) {
                ++cnt;
            } else {
                if (remove > 0 && cnt >= 3) {
                    if (cnt % 3 == 0) {
                        --remove;
                        --replace;
                    } else if (cnt % 3 == 1) {
                        ++remove2;
                    }
                }
                replace += cnt / 3;
                cnt = 1;
                prev = curr;
            }
        }
        if (remove > 0 && cnt >= 3) {
            if (cnt % 3 == 0) {
                --remove;
                --replace;
            } else if (cnt % 3 == 1) {
                ++remove2;
            }
        }
        replace += cnt / 3;

        int use2 = Math.min(Math.min(replace, remove2), remove / 2);
        replace -= use2;
        remove -= use2 * 2;

        int use3 = Math.min(replace, remove / 3);
        replace -= use3;
        remove -= use3 * 3;
        return (n - 20) + Math.max(replace, 3 - types);
    }

    private int countTypes(String s) {
        int a = 0, b = 0, c = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                a = 1;
            } else if (Character.isUpperCase(ch)) {
                b = 1;
            } else if (Character.isDigit(ch)) {
                c = 1;
            }
        }
        return a + b + c;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int strongPasswordChecker(string password) {
        int types = countTypes(password);
        int n = password.size();
        if (n < 6) return max(6 - n, 3 - types);
        if (n <= 20) {
            int replace = 0, cnt = 0;
            char prev = '~';
            for (char& curr : password) {
                if (curr == prev)
                    ++cnt;
                else {
                    replace += cnt / 3;
                    cnt = 1;
                    prev = curr;
                }
            }
            replace += cnt / 3;
            return max(replace, 3 - types);
        }
        int replace = 0, remove = n - 20;
        int remove2 = 0;
        int cnt = 0;
        char prev = '~';
        for (char& curr : password) {
            if (curr == prev)
                ++cnt;
            else {
                if (remove > 0 && cnt >= 3) {
                    if (cnt % 3 == 0) {
                        --remove;
                        --replace;
                    } else if (cnt % 3 == 1)
                        ++remove2;
                }
                replace += cnt / 3;
                cnt = 1;
                prev = curr;
            }
        }
        if (remove > 0 && cnt >= 3) {
            if (cnt % 3 == 0) {
                --remove;
                --replace;
            } else if (cnt % 3 == 1)
                ++remove2;
        }
        replace += cnt / 3;

        int use2 = min(min(replace, remove2), remove / 2);
        replace -= use2;
        remove -= use2 * 2;

        int use3 = min(replace, remove / 3);
        replace -= use3;
        remove -= use3 * 3;
        return (n - 20) + max(replace, 3 - types);
    }

    int countTypes(string& s) {
        int a = 0, b = 0, c = 0;
        for (char& ch : s) {
            if (islower(ch))
                a = 1;
            else if (isupper(ch))
                b = 1;
            else if (isdigit(ch))
                c = 1;
        }
        return a + b + c;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
