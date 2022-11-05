# [2452. 距离字典两次编辑以内的单词](https://leetcode.cn/problems/words-within-two-edits-of-dictionary)

[English Version](/solution/2400-2499/2452.Words%20Within%20Two%20Edits%20of%20Dictionary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串数组&nbsp;<code>queries</code> 和&nbsp;<code>dictionary</code>&nbsp;。数组中所有单词都只包含小写英文字母，且长度都相同。</p>

<p>一次 <strong>编辑</strong>&nbsp;中，你可以从 <code>queries</code>&nbsp;中选择一个单词，将任意一个字母修改成任何其他字母。从&nbsp;<code>queries</code>&nbsp;中找到所有满足以下条件的字符串：<strong>不超过</strong>&nbsp;两次编辑内，字符串与&nbsp;<code>dictionary</code>&nbsp;中某个字符串相同。</p>

<p>请你返回<em>&nbsp;</em><code>queries</code>&nbsp;中的单词列表，这些单词距离&nbsp;<code>dictionary</code>&nbsp;中的单词&nbsp;<strong>编辑次数</strong>&nbsp;不超过&nbsp;<strong>两次</strong>&nbsp;。单词返回的顺序需要与&nbsp;<code>queries</code>&nbsp;中原本顺序相同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
<b>输出：</b>["word","note","wood"]
<strong>解释：</strong>
- 将 "word" 中的 'r' 换成 'o' ，得到 dictionary 中的单词 "wood" 。
- 将 "note" 中的 'n' 换成 'j' 且将 't' 换成 'k' ，得到 "joke" 。
- "ants" 需要超过 2 次编辑才能得到 dictionary 中的单词。
- "wood" 不需要修改（0 次编辑），就得到 dictionary 中相同的单词。
所以我们返回 ["word","note","wood"] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>queries = ["yes"], dictionary = ["not"]
<b>输出：</b>[]
<strong>解释：</strong>
"yes" 需要超过 2 次编辑才能得到 "not" 。
所以我们返回空数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queries.length, dictionary.length &lt;= 100</code></li>
	<li><code>n == queries[i].length == dictionary[j].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li>所有&nbsp;<code>queries[i]</code> 和&nbsp;<code>dictionary[j]</code>&nbsp;都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

遍历 `queries` 中的每个单词，对于每个单词，遍历 `dictionary` 中的每个单词，判断两个单词不同字符的位置数是否小于 $3$，如果是，则将该单词加入结果集。

时间复杂度 $O(m\times n\times k)$。其中 $m$ 和 $n$ 分别是 `queries` 和 `dictionary` 的长度，而 $k$ 是 `queries` 和 `dictionary` 中单词的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoEditWords(self, queries: List[str], dictionary: List[str]) -> List[str]:
        ans = []
        for s in queries:
            for t in dictionary:
                if sum(a != b for a, b in zip(s, t)) < 3:
                    ans.append(s)
                    break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        int n = queries[0].length();
        for (var s : queries) {
            for (var t : dictionary) {
                int cnt = 0;
                for (int i = 0; i < n; ++i) {
                    if (s.charAt(i) != t.charAt(i)) {
                        ++cnt;
                    }
                }
                if (cnt < 3) {
                    ans.add(s);
                    break;
                }
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
    vector<string> twoEditWords(vector<string>& queries, vector<string>& dictionary) {
        vector<string> ans;
        for (auto& s : queries) {
            for (auto& t : dictionary) {
                int cnt = 0;
                for (int i = 0; i < s.size(); ++i) cnt += s[i] != t[i];
                if (cnt < 3) {
                    ans.emplace_back(s);
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func twoEditWords(queries []string, dictionary []string) (ans []string) {
	for _, s := range queries {
		for _, t := range dictionary {
			cnt := 0
			for i := range s {
				if s[i] != t[i] {
					cnt++
				}
			}
			if cnt < 3 {
				ans = append(ans, s)
				break
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function twoEditWords(queries: string[], dictionary: string[]): string[] {
    const n = queries[0].length;
    return queries.filter(querie => {
        for (const s of dictionary) {
            let diff = 0;
            for (let i = 0; i < n; i++) {
                if (querie[i] !== s[i] && ++diff > 2) {
                    break;
                }
            }
            if (diff <= 2) {
                return true;
            }
        }
        return false;
    });
}
```

### **Rust**

```rust
impl Solution {
    pub fn two_edit_words(queries: Vec<String>, dictionary: Vec<String>) -> Vec<String> {
        let n = queries[0].len();
        queries
            .into_iter()
            .filter(|querie| {
                for s in dictionary.iter() {
                    let mut diff = 0;
                    for i in 0..n {
                        if querie.as_bytes()[i] != s.as_bytes()[i] {
                            diff += 1;
                        }
                    }
                    if diff <= 2 {
                        return true;
                    }
                }
                false
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
