# [423. 从英文中重建数字](https://leetcode-cn.com/problems/reconstruct-original-digits-from-english)

[English Version](/solution/0400-0499/0423.Reconstruct%20Original%20Digits%20from%20English/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个<strong>非空</strong>字符串，其中包含字母顺序打乱的英文单词表示的数字<code>0-9</code>。按升序输出原始的数字。</p>

<p><strong>注意:</strong></p>

<ol>
	<li>输入只包含小写英文字母。</li>
	<li>输入保证合法并可以转换为原始的数字，这意味着像 &quot;abc&quot; 或 &quot;zerone&quot; 的输入是不允许的。</li>
	<li>输入字符串的长度小于 50,000。</li>
</ol>

<p><strong>示例 1:</strong></p>

<pre>
输入: &quot;owoztneoer&quot;

输出: &quot;012&quot; (zeroonetwo)
</pre>

<p><strong>示例 2:</strong></p>

<pre>
输入: &quot;fviefuro&quot;

输出: &quot;45&quot; (fourfive)
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

统计 `["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"]` 每个字母在哪些数字出现过。

| 字母 | 数字          |
| ---- | ------------- |
| e    | 0 1 3 5 7 8 9 |
| g    | 8             |
| f    | 4 5           |
| i    | 5 6 8 9       |
| h    | 3 8           |
| o    | 0 1 2 4       |
| n    | 1 7 9         |
| s    | 6 7           |
| r    | 0 3 4         |
| u    | 4             |
| t    | 2 3 8         |
| w    | 2             |
| v    | 5 7           |
| x    | 6             |
| z    | 0             |

由于部分字母只在某个数字出现过，比如字母 `z` 只在 `0` 出现过，因此我们统计英文中 `z` 的数量，就可以推断数字 0 的个数，依次类推。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def originalDigits(self, s: str) -> str:
        counter = Counter(s)
        cnt = [0] * 10

        cnt[0] = counter['z']
        cnt[2] = counter['w']
        cnt[4] = counter['u']
        cnt[6] = counter['x']
        cnt[8] = counter['g']

        cnt[3] = counter['h'] - cnt[8]
        cnt[5] = counter['f'] - cnt[4]
        cnt[7] = counter['s'] - cnt[6]

        cnt[1] = counter['o'] - cnt[0] - cnt[2] - cnt[4]
        cnt[9] = counter['i'] - cnt[5] - cnt[6] - cnt[8]

        return ''.join(cnt[i] * str(i) for i in range(10))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String originalDigits(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int[] cnt = new int[10];
        cnt[0] = counter['z' - 'a'];
        cnt[2] = counter['w' - 'a'];
        cnt[4] = counter['u' - 'a'];
        cnt[6] = counter['x' - 'a'];
        cnt[8] = counter['g' - 'a'];

        cnt[3] = counter['h' - 'a'] - cnt[8];
        cnt[5] = counter['f' - 'a'] - cnt[4];
        cnt[7] = counter['s' - 'a'] - cnt[6];

        cnt[1] = counter['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = counter['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string originalDigits(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        vector<int> cnt(10);
        cnt[0] = counter['z' - 'a'];
        cnt[2] = counter['w' - 'a'];
        cnt[4] = counter['u' - 'a'];
        cnt[6] = counter['x' - 'a'];
        cnt[8] = counter['g' - 'a'];

        cnt[3] = counter['h' - 'a'] - cnt[8];
        cnt[5] = counter['f' - 'a'] - cnt[4];
        cnt[7] = counter['s' - 'a'] - cnt[6];

        cnt[1] = counter['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = counter['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];

        string ans;
        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < cnt[i]; ++j)
                ans += char(i + '0');
        return ans;
    }
};
```

### **Go**

```go
func originalDigits(s string) string {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	cnt := make([]int, 10)
	cnt[0] = counter['z'-'a']
	cnt[2] = counter['w'-'a']
	cnt[4] = counter['u'-'a']
	cnt[6] = counter['x'-'a']
	cnt[8] = counter['g'-'a']

	cnt[3] = counter['h'-'a'] - cnt[8]
	cnt[5] = counter['f'-'a'] - cnt[4]
	cnt[7] = counter['s'-'a'] - cnt[6]

	cnt[1] = counter['o'-'a'] - cnt[0] - cnt[2] - cnt[4]
	cnt[9] = counter['i'-'a'] - cnt[5] - cnt[6] - cnt[8]

	ans := []byte{}
	for i, c := range cnt {
		ans = append(ans, bytes.Repeat([]byte{byte('0' + i)}, c)...)
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
