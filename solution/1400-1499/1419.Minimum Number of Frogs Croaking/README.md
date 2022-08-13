# [1419. 数青蛙](https://leetcode.cn/problems/minimum-number-of-frogs-croaking)

[English Version](/solution/1400-1499/1419.Minimum%20Number%20of%20Frogs%20Croaking/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>croakOfFrogs</code>，它表示不同青蛙发出的蛙鸣声（字符串 <code>"croak"</code> ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以&nbsp;<code>croakOfFrogs</code> 中会混合多个 <code>“croak”</code> <em>。</em></p>

<p>请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。</p>

<p>要想发出蛙鸣 "croak"，青蛙必须 <strong>依序</strong> 输出 <code>‘c’, ’r’, ’o’, ’a’, ’k’</code> 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 <code>croakOfFrogs</code> 不是由若干有效的 "croak" 字符混合而成，请返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "croakcroak"
<strong>输出：</strong>1 
<strong>解释：</strong>一只青蛙 “呱呱” 两次
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "crcoakroak"
<strong>输出：</strong>2 
<strong>解释：</strong>最少需要两只青蛙，“呱呱” 声用黑体标注
第一只青蛙 "<strong>cr</strong>c<strong>oak</strong>roak"
第二只青蛙 "cr<strong>c</strong>oak<strong>roak</strong>"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "croakcrook"
<strong>输出：</strong>-1
<strong>解释：</strong>给出的字符串不是 "croak<strong>"</strong> 的有效组合。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= croakOfFrogs.length &lt;= 10<sup>5</sup></code></li>
	<li>字符串中的字符只有 <code>'c'</code>, <code>'r'</code>, <code>'o'</code>, <code>'a'</code> 或者 <code>'k'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

维护 croak 的个数，如果遇到当前字母，则肯定是由前面字母过来，前面字母数减 1。如遇到 r，则必是 `c->r`，所以 c 减 1。

k 代表结尾，表示一次喊叫结束，所以遇到 c 的时候，先去消耗 k，没有 k 了，需要新青蛙，ans 加 1。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minNumberOfFrogs(self, croakOfFrogs: str) -> int:
        c = r = o = a = k = ans = 0
        for ch in croakOfFrogs:
            if ch == 'c':
                c += 1
                if k > 0:
                    k -= 1
                else:
                    ans += 1
            elif ch == 'r':
                r += 1
                c -= 1
            elif ch == 'o':
                o += 1
                r -= 1
            elif ch == 'a':
                a += 1
                o -= 1
            else:
                k += 1
                a -= 1
            if c < 0 or r < 0 or o < 0 or a < 0:
                return -1
        return -1 if c != 0 or r != 0 or o != 0 or a != 0 else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int ans = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            if (ch == 'c') {
                ++c;
                if (k > 0) {
                    --k;
                } else {
                    ++ans;
                }
            } else if (ch == 'r') {
                ++r;
                --c;
            } else if (ch == 'o') {
                ++o;
                --r;
            } else if (ch == 'a') {
                ++a;
                --o;
            } else {
                ++k;
                --a;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) {
                return -1;
            }
        }
        return c == 0 && r == 0 && o == 0 && a == 0 ? ans : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minNumberOfFrogs(string croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, ans = 0;
        for (char ch : croakOfFrogs) {
            if (ch == 'c') {
                ++c;
                if (k > 0)
                    --k;
                else
                    ++ans;
            } else if (ch == 'r') {
                ++r;
                --c;
            } else if (ch == 'o') {
                ++o;
                --r;
            } else if (ch == 'a') {
                ++a;
                --o;
            } else {
                ++k;
                --a;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) return -1;
        }
        return c == 0 && r == 0 && o == 0 && a == 0 ? ans : -1;
    }
};
```

### **Go**

```go
func minNumberOfFrogs(croakOfFrogs string) int {
	c, r, o, a, k, ans := 0, 0, 0, 0, 0, 0
	for i := range croakOfFrogs {
		ch := croakOfFrogs[i]
		if ch == 'c' {
			c++
			if k > 0 {
				k--
			} else {
				ans++
			}
		} else if ch == 'r' {
			r++
			c--
		} else if ch == 'o' {
			o++
			r--
		} else if ch == 'a' {
			a++
			o--
		} else {
			k++
			a--
		}
		if c < 0 || r < 0 || o < 0 || a < 0 {
			return -1
		}
	}
	if c == 0 && r == 0 && o == 0 && a == 0 {
		return ans
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
