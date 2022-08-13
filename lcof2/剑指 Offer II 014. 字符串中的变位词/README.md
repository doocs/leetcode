# [剑指 Offer II 014. 字符串中的变位词](https://leetcode.cn/problems/MPnaiL)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的某个变位词。</p>

<p>换句话说，第一个字符串的排列之一是第二个字符串的 <strong>子串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>s1 = &quot;ab&quot; s2 = &quot;eidbaooo&quot;
<strong>输出: </strong>True
<strong>解释:</strong> s2 包含 s1 的排列之一 (&quot;ba&quot;).
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>s1= &quot;ab&quot; s2 = &quot;eidboaoo&quot;
<strong>输出:</strong> False
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 567&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/permutation-in-string/">https://leetcode.cn/problems/permutation-in-string/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

维护一个长度固定的窗口向前滑动

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        n1, n2 = len(s1), len(s2)
        if n1 > n2:
            return False
        window = [0 for _ in range(26)]
        for i in range(n1):
            window[ord(s1[i]) - ord('a')] += 1
            window[ord(s2[i]) - ord('a')] -= 1
        if self.check(window):
            return True
        for i in range(n1, n2):
            window[ord(s2[i]) - ord('a')] -= 1
            window[ord(s2[i - n1]) - ord('a')] += 1
            if self.check(window):
                return True
        return False

    def check(self, window: List[int]) -> bool:
        return all([cnt == 0 for cnt in window])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        int[] window = new int[26];
        for (int i = 0; i < n1; i++) {
            window[s1.charAt(i) - 'a']++;
            window[s2.charAt(i) - 'a']--;
        }
        if (check(window)) {
            return true;
        }
        for (int i = n1; i < n2; i++) {
            window[s2.charAt(i) - 'a']--;
            window[s2.charAt(i - n1) - 'a']++;
            if (check(window)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] window) {
        return Arrays.stream(window).allMatch(cnt -> cnt == 0);
    }
}
```

### **Go**

```go
func checkInclusion(s1 string, s2 string) bool {
	n1, n2 := len(s1), len(s2)
	if n1 > n2 {
		return false
	}
	window := make([]int, 26)
	for i := 0; i < n1; i++ {
		window[s1[i]-'a']++
		window[s2[i]-'a']--
	}
	if check(window) {
		return true
	}
	for i := n1; i < n2; i++ {
		window[s2[i]-'a']--
		window[s2[i-n1]-'a']++
		if check(window) {
			return true
		}
	}
	return false
}

func check(window []int) bool {
	for _, cnt := range window {
		if cnt != 0 {
			return false
		}
	}
	return true
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkInclusion(string s1, string s2) {

        int len1 = s1.size();
        int len2 = s2.size();

        if (len2 < len1) {
            return false;
        }

        int count[30] = {0};

        for (int i = 0; i < len1; ++i) {
            ++count[s1[i] - 'a'];
            --count[s2[i] - 'a'];
        }

        int l = 0;
        int r = len1 - 1;

        while (r < len2) {

            bool flag = true;

            for (int i : count) {
                if (i != 0) {
                    flag = false;
                }
            }

            if (flag) {
                return true;
            }

            if (r + 1 >= len2) {
                break;
            }

            ++count[s2[l++] - 'a'];
            --count[s2[++r] - 'a'];
        }

        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
