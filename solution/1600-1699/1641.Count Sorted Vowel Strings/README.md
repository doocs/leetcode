# [1641. 统计字典序元音字符串的数目](https://leetcode.cn/problems/count-sorted-vowel-strings)

[English Version](/solution/1600-1699/1641.Count%20Sorted%20Vowel%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code>，请返回长度为 <code>n</code> 、仅由元音 (<code>a</code>, <code>e</code>, <code>i</code>, <code>o</code>, <code>u</code>) 组成且按 <strong>字典序排列</strong> 的字符串数量。</p>

<p>字符串 <code>s</code> 按 <strong>字典序排列</strong> 需要满足：对于所有有效的 <code>i</code>，<code>s[i]</code> 在字母表中的位置总是与 <code>s[i+1]</code> 相同或在 <code>s[i+1]</code> 之前。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>5
<strong>解释：</strong>仅由元音组成的 5 个字典序字符串为 <code>["a","e","i","o","u"]</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>15
<strong>解释：</strong>仅由元音组成的 15 个字典序字符串为
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
注意，"ea" 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 33
<strong>输出：</strong>66045
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 50</code> </li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

```bash
a	e	i	o 	u
1	1	1	1	1		n=1
5	4	3	2	1		n=2
15	10	6	3	1		n=3
...						n=...
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countVowelStrings(self, n: int) -> int:
        cnt = [1] * 5
        for i in range(2, n + 1):
            for j in range(3, -1, -1):
                cnt[j] += cnt[j + 1]
        return sum(cnt)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countVowelStrings(int n) {
        int[] cnt = new int[5];
        Arrays.fill(cnt, 1);
        for (int i = 2; i <= n; ++i) {
            for (int j = 3; j >= 0; --j) {
                cnt[j] += cnt[j + 1];
            }
        }
        return Arrays.stream(cnt).sum();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countVowelStrings(int n) {
        vector<int> cnt(5, 1);
        for (int i = 2; i <= n; ++i)
            for (int j = 3; j >= 0; --j)
                cnt[j] += cnt[j + 1];
        return accumulate(cnt.begin(), cnt.end(), 0);
    }
};
```

### **Go**

```go
func countVowelStrings(n int) int {
	cnt := make([]int, 5)
	for i := range cnt {
		cnt[i] = 1
	}
	for i := 2; i <= n; i++ {
		for j := 3; j >= 0; j-- {
			cnt[j] += cnt[j+1]
		}
	}
	ans := 0
	for _, v := range cnt {
		ans += v
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
