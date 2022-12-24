# [1754. 构造字典序最大的合并字符串](https://leetcode.cn/problems/largest-merge-of-two-strings)

[English Version](/solution/1700-1799/1754.Largest%20Merge%20Of%20Two%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>word1</code> 和 <code>word2</code> 。你需要按下述方式构造一个新字符串 <code>merge</code> ：如果 <code>word1</code> 或 <code>word2</code> 非空，选择 <strong>下面选项之一</strong> 继续操作：</p>

<ul>
	<li>如果 <code>word1</code> 非空，将 <code>word1</code> 中的第一个字符附加到 <code>merge</code> 的末尾，并将其从 <code>word1</code> 中移除。
    <ul>
    	<li>例如，<code>word1 = "abc" </code>且 <code>merge = "dv"</code> ，在执行此选项操作之后，<code>word1 = "bc"</code> ，同时 <code>merge = "dva"</code> 。</li>
    </ul>
    </li>
    <li>如果 <code>word2</code> 非空，将 <code>word2</code> 中的第一个字符附加到 <code>merge</code> 的末尾，并将其从 <code>word2</code> 中移除。
    <ul>
    	<li>例如，<code>word2 = "abc" </code>且 <code>merge = ""</code> ，在执行此选项操作之后，<code>word2 = "bc"</code> ，同时 <code>merge = "a"</code> 。</li>
    </ul>
    </li>
</ul>

<p>返回你可以构造的字典序 <strong>最大</strong> 的合并字符串<em> </em><code>merge</code><em> 。</em></p>

<p>长度相同的两个字符串 <code>a</code> 和 <code>b</code> 比较字典序大小，如果在 <code>a</code> 和 <code>b</code> 出现不同的第一个位置，<code>a</code> 中字符在字母表中的出现顺序位于 <code>b</code> 中相应字符之后，就认为字符串 <code>a</code> 按字典序比字符串 <code>b</code> 更大。例如，<code>"abcd"</code> 按字典序比 <code>"abcc"</code> 更大，因为两个字符串出现不同的第一个位置是第四个字符，而 <code>d</code> 在字母表中的出现顺序位于 <code>c</code> 之后。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word1 = "cabaa", word2 = "bcaaa"
<strong>输出：</strong>"cbcabaaaaa"
<strong>解释：</strong>构造字典序最大的合并字符串，可行的一种方法如下所示：
- 从 word1 中取第一个字符：merge = "c"，word1 = "abaa"，word2 = "bcaaa"
- 从 word2 中取第一个字符：merge = "cb"，word1 = "abaa"，word2 = "caaa"
- 从 word2 中取第一个字符：merge = "cbc"，word1 = "abaa"，word2 = "aaa"
- 从 word1 中取第一个字符：merge = "cbca"，word1 = "baa"，word2 = "aaa"
- 从 word1 中取第一个字符：merge = "cbcab"，word1 = "aa"，word2 = "aaa"
- 将 word1 和 word2 中剩下的 5 个 a 附加到 merge 的末尾。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word1 = "abcabc", word2 = "abdcaba"
<strong>输出：</strong>"abdcabcabcaba"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word1.length, word2.length <= 3000</code></li>
	<li><code>word1</code> 和 <code>word2</code> 仅由小写英文组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 双指针**

我们用指针 $i$ 和 $j$ 分别指向字符串 `word1` 和 `word2` 的第一个字符。然后循环，每次比较 $word1[i:]$ 和 $word2[j:]$ 的大小，如果 $word1[i:]$ 比 $word2[j:]$ 大，那么我们就将 $word1[i]$ 加入答案，否则我们就将 $word2[j]$ 加入答案。循环，直至 $i$ 到达字符串 `word1` 的末尾，或者 $j$ 到达字符串 `word2` 的末尾。

然后我们将剩余的字符串加入答案即可。

时间复杂度 $O(n^2)$。其中 $n$ 是字符串 `word1` 和 `word2` 的长度之和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestMerge(self, word1: str, word2: str) -> str:
        i = j = 0
        ans = []
        while i < len(word1) and j < len(word2):
            if word1[i:] > word2[j:]:
                ans.append(word1[i])
                i += 1
            else:
                ans.append(word2[j])
                j += 1
        ans.append(word1[i:])
        ans.append(word2[j:])
        return "".join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String largestMerge(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < m && j < n) {
            boolean gt = word1.substring(i).compareTo(word2.substring(j)) > 0;
            ans.append(gt ? word1.charAt(i++) : word2.charAt(j++));
        }
        ans.append(word1.substring(i));
        ans.append(word2.substring(j));
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string largestMerge(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        int i = 0, j = 0;
        string ans;
        while (i < m && j < n) {
            bool gt = word1.substr(i) > word2.substr(j);
            ans += gt ? word1[i++] : word2[j++];
        }
        ans += word1.substr(i);
        ans += word2.substr(j);
        return ans;
    }
};
```

### **Go**

```go
func largestMerge(word1 string, word2 string) string {
	m, n := len(word1), len(word2)
	i, j := 0, 0
	var ans strings.Builder
	for i < m && j < n {
		if word1[i:] > word2[j:] {
			ans.WriteByte(word1[i])
			i++
		} else {
			ans.WriteByte(word2[j])
			j++
		}
	}
	ans.WriteString(word1[i:])
	ans.WriteString(word2[j:])
	return ans.String()
}
```

### **TypeScript**

```ts
function largestMerge(word1: string, word2: string): string {
    const m = word1.length;
    const n = word2.length;
    let ans = '';
    let i = 0;
    let j = 0;
    while (i < m && j < n) {
        ans += word1.slice(i) > word2.slice(j) ? word1[i++] : word2[j++];
    }
    ans += word1.slice(i);
    ans += word2.slice(j);
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn largest_merge(word1: String, word2: String) -> String {
        let word1 = word1.as_bytes();
        let word2 = word2.as_bytes();
        let m = word1.len();
        let n = word2.len();
        let mut ans = String::new();
        let mut i = 0;
        let mut j = 0;
        while i < m && j < n {
            if word1[i..] > word2[j..] {
                ans.push(word1[i] as char);
                i += 1;
            } else {
                ans.push(word2[j] as char);
                j += 1;
            }
        }
        word1[i..].iter().for_each(|c| ans.push(*c as char));
        word2[j..].iter().for_each(|c| ans.push(*c as char));
        ans
    }
}
```

### **C**

```c
char *largestMerge(char *word1, char *word2) {
    int m = strlen(word1);
    int n = strlen(word2);
    int i = 0;
    int j = 0;
    char *ans = malloc((m + n + 1) * sizeof(char));
    while (i < m && j < n) {
        int k = 0;
        while (word1[i + k] && word2[j + k] && word1[i + k] == word2[j + k]) {
            k++;
        }
        if (word1[i + k] > word2[j + k]) {
            ans[i + j] = word1[i];
            i++;
        } else {
            ans[i + j] = word2[j];
            j++;
        };
    }
    while (word1[i]) {
        ans[i + j] = word1[i];
        i++;
    }
    while (word2[j]) {
        ans[i + j] = word2[j];
        j++;
    }
    ans[m + n] = '\0';
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
