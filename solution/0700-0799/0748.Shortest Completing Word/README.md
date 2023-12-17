# [748. 最短补全词](https://leetcode.cn/problems/shortest-completing-word)

[English Version](/solution/0700-0799/0748.Shortest%20Completing%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>licensePlate</code> 和一个字符串数组 <code>words</code> ，请你找出&nbsp;<code>words</code> 中的 <strong>最短补全词</strong> 。</p>

<p><strong>补全词 </strong>是一个包含 <code>licensePlate</code> 中所有字母的单词。<strong>忽略</strong>&nbsp;<code>licensePlate</code> 中的 <strong>数字和空格 </strong>。<strong>不区分大小写</strong>。如果某个字母在 <code>licensePlate</code> 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。</p>

<p>例如：<code>licensePlate</code><code> = "aBc 12c"</code>，那么它的补全词应当包含字母 <code>'a'</code>、<code>'b'</code> （忽略大写）和两个 <code>'c'</code> 。可能的 <strong>补全词</strong> 有 <code>"abccdef"</code>、<code>"caaacab"</code> 以及 <code>"cbca"</code> 。</p>

<p>请返回 <code>words</code> 中的 <strong>最短补全词</strong> 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 <code>words</code> 中 <strong>第一个</strong> 出现的那个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
<strong>输出：</strong>"steps"
<strong>解释：</strong>最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
"step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
"steps" 包含 "t"、"p" 和两个 "s"。
"stripe" 缺一个 "s"。
"stepple" 缺一个 "s"。
因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
<strong>输出：</strong>"pest"
<strong>解释：</strong>licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= licensePlate.length &lt;= 7</code></li>
	<li><code>licensePlate</code> 由数字、大小写字母或空格 <code>' '</code> 组成</li>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 15</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们先用哈希表或者一个长度为 $26$ 的数组 $cnt$ 统计字符串 `licensePlate` 中每个字母出现的次数，注意这里我们统一将字母转换为小写进行计数。

然后，我们遍历数组 `words` 中的每个单词 $w$，如果单词 $w$ 的长度比答案 $ans$ 的长度长，那么我们直接跳过该单词。否则，我们再用哈希表或者一个长度为 $26$ 的数组 $t$ 统计单词 $w$ 中每个字母出现的次数。如果对于任意一个字母，$t$ 中该字母出现的次数小于 $cnt$ 中该字母出现的次数，那么我们也可以直接跳过该单词。否则，我们就找到了一个满足条件的单词，我们更新答案 $ans$ 为当前单词 $w$。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是数组 `words` 的长度，而 $\Sigma$ 是字符集，这里字符集为所有小写字母，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestCompletingWord(self, licensePlate: str, words: List[str]) -> str:
        cnt = Counter(c.lower() for c in licensePlate if c.isalpha())
        ans = None
        for w in words:
            if ans and len(w) >= len(ans):
                continue
            t = Counter(w)
            if all(v <= t[c] for c, v in cnt.items()):
                ans = w
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] cnt = new int[26];
        for (int i = 0; i < licensePlate.length(); ++i) {
            char c = licensePlate.charAt(i);
            if (Character.isLetter(c)) {
                cnt[Character.toLowerCase(c) - 'a']++;
            }
        }
        String ans = "";
        for (String w : words) {
            if (!ans.isEmpty() && w.length() >= ans.length()) {
                continue;
            }
            int[] t = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                t[w.charAt(i) - 'a']++;
            }
            boolean ok = true;
            for (int i = 0; i < 26; ++i) {
                if (t[i] < cnt[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = w;
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
    string shortestCompletingWord(string licensePlate, vector<string>& words) {
        int cnt[26]{};
        for (char& c : licensePlate) {
            if (isalpha(c)) {
                ++cnt[tolower(c) - 'a'];
            }
        }
        string ans;
        for (auto& w : words) {
            if (ans.size() && ans.size() <= w.size()) {
                continue;
            }
            int t[26]{};
            for (char& c : w) {
                ++t[c - 'a'];
            }
            bool ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = w;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestCompletingWord(licensePlate string, words []string) (ans string) {
	cnt := [26]int{}
	for _, c := range licensePlate {
		if unicode.IsLetter(c) {
			cnt[unicode.ToLower(c)-'a']++
		}
	}
	for _, w := range words {
		if len(ans) > 0 && len(ans) <= len(w) {
			continue
		}
		t := [26]int{}
		for _, c := range w {
			t[c-'a']++
		}
		ok := true
		for i, v := range cnt {
			if t[i] < v {
				ok = false
				break
			}
		}
		if ok {
			ans = w
		}
	}
	return
}
```

### **TypeScript**

```ts
function shortestCompletingWord(licensePlate: string, words: string[]): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of licensePlate) {
        const i = c.toLowerCase().charCodeAt(0) - 97;
        if (0 <= i && i < 26) {
            ++cnt[i];
        }
    }
    let ans = '';
    for (const w of words) {
        if (ans.length && ans.length <= w.length) {
            continue;
        }
        const t = Array(26).fill(0);
        for (const c of w) {
            ++t[c.charCodeAt(0) - 97];
        }
        let ok = true;
        for (let i = 0; i < 26; ++i) {
            if (t[i] < cnt[i]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            ans = w;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn shortest_completing_word(license_plate: String, words: Vec<String>) -> String {
        let mut cnt = vec![0; 26];
        for c in license_plate.chars() {
            if c.is_ascii_alphabetic() {
                cnt[((c.to_ascii_lowercase() as u8) - b'a') as usize] += 1;
            }
        }
        let mut ans = String::new();
        for w in words {
            if !ans.is_empty() && w.len() >= ans.len() {
                continue;
            }
            let mut t = vec![0; 26];
            for c in w.chars() {
                t[((c as u8) - b'a') as usize] += 1;
            }
            let mut ok = true;
            for i in 0..26 {
                if t[i] < cnt[i] {
                    ok = false;
                    break;
                }
            }
            if ok {
                ans = w;
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
