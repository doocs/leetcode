# [1408. 数组中的字符串匹配](https://leetcode.cn/problems/string-matching-in-an-array)

[English Version](/solution/1400-1499/1408.String%20Matching%20in%20an%20Array/README_EN.md)

<!-- tags:数组,字符串,字符串匹配 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> ，数组中的每个字符串都可以看作是一个单词。请你按 <strong>任意</strong> 顺序返回 <code>words</code> 中是其他单词的子字符串的所有单词。</p>

<p>如果你可以删除 <code>words[j]</code>&nbsp;最左侧和/或最右侧的若干字符得到 <code>words[i]</code> ，那么字符串 <code>words[i]</code> 就是 <code>words[j]</code> 的一个子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["mass","as","hero","superhero"]
<strong>输出：</strong>["as","hero"]
<strong>解释：</strong>"as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
["hero","as"] 也是有效的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["leetcode","et","code"]
<strong>输出：</strong>["et","code"]
<strong>解释：</strong>"et" 和 "code" 都是 "leetcode" 的子字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["blue","green","bu"]
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>words[i]</code> 仅包含小写英文字母。</li>
	<li>题目数据 <strong>保证</strong> 每个 <code>words[i]</code> 都是独一无二的。</li>
</ul>

## 解法

### 方法一：暴力枚举

我们直接枚举所有的字符串 $words[i]$，判断其是否为其他字符串的子串，如果是，将其加入答案。

时间复杂度 $O(n^3)$，空间复杂度 $O(n)$。其中 $n$ 为字符串数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def stringMatching(self, words: List[str]) -> List[str]:
        ans = []
        for i, s in enumerate(words):
            if any(i != j and s in t for j, t in enumerate(words)):
                ans.append(s)
        return ans
```

```java
class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && words[j].contains(words[i])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> stringMatching(vector<string>& words) {
        vector<string> ans;
        int n = words.size();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && words[j].find(words[i]) != string::npos) {
                    ans.push_back(words[i]);
                    break;
                }
            }
        }
        return ans;
    }
};
```

```go
func stringMatching(words []string) []string {
	ans := []string{}
	for i, w1 := range words {
		for j, w2 := range words {
			if i != j && strings.Contains(w2, w1) {
				ans = append(ans, w1)
				break
			}
		}
	}
	return ans
}
```

```ts
function stringMatching(words: string[]): string[] {
    const ans: string[] = [];
    const n = words.length;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (words[j].includes(words[i]) && i !== j) {
                ans.push(words[i]);
                break;
            }
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn string_matching(words: Vec<String>) -> Vec<String> {
        let mut ans = Vec::new();
        let n = words.len();
        for i in 0..n {
            for j in 0..n {
                if i != j && words[j].contains(&words[i]) {
                    ans.push(words[i].clone());
                    break;
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
