# [面试题 16.20. T9 键盘](https://leetcode.cn/problems/t9-lcci)

[English Version](/lcci/16.20.T9/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：</p>
![](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcci/16.20.T9/images/17_telephone_keypad.png)
<p><strong>示例 1:</strong></p>
<pre><strong>输入:</strong> num = &quot;8733&quot;, words = [&quot;tree&quot;, &quot;used&quot;]
<strong>输出:</strong> [&quot;tree&quot;, &quot;used&quot;]
</pre>
<p><strong>示例 2:</strong></p>
<pre><strong>输入:</strong> num = &quot;2&quot;, words = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;d&quot;]
<strong>输出:</strong> [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]</pre>
<p>提示：</p>
<ul>
	<li><code>num.length &lt;= 1000</code></li>
	<li><code>words.length &lt;= 500</code></li>
	<li><code>words[i].length == num.length</code></li>
	<li><code>num</code>中不会出现 0, 1 这两个数字</li>
</ul>

## 解法

### 方法一：逆向思维

我们考虑一种正向的解法，遍历字符串 $num$ 中的每个数字，将其映射到对应的字母，然后将所有的字母组合起来，得到所有可能的单词，再与给定的单词列表进行比较，若单词在列表中，则将其加入答案。这种解法的时间复杂度为 $O(4^n)$，其中 $n$ 为字符串 $num$ 的长度，显然会超时。

我们不妨考虑逆向的解法，遍历给定的单词列表，对于每个单词 $w$，判断其是否能够由字符串 $num$ 中的数字组成。若能够组成，则将其加入答案。那么问题的关键在于如何判断一个单词是否能够由字符串 $num$ 中的数字组成。我们只需要遍历单词 $w$ 的每个字母，将其还原为对应的数字，逐个与字符串 $num$ 中的数字进行比较，若相同，则说明单词 $w$ 可以由字符串 $num$ 中的数字组成。

时间复杂度 $O(m \times n)$，空间复杂度 $O(C)$。其中 $m$ 和 $n$ 分别是单词列表的长度和字符串 $num$ 的长度；而 $C$ 为字符集大小，本题中字符集大小为 $26$。

<!-- tabs:start -->

```python
class Solution:
    def getValidT9Words(self, num: str, words: List[str]) -> List[str]:
        def check(w: str) -> bool:
            return all(d[c] == num[i] for i, c in enumerate(w))

        d = {c: d for c, d in zip(ascii_lowercase, "22233344455566677778889999")}
        return [w for w in words if check(w)]
```

```java
class Solution {
    public List<String> getValidT9Words(String num, String[] words) {
        String s = "22233344455566677778889999";
        int[] d = new int[26];
        for (int i = 0; i < 26; ++i) {
            d[i] = s.charAt(i);
        }
        List<String> ans = new ArrayList<>();
        int n = num.length();
        for (String w : words) {
            boolean ok = true;
            for (int i = 0; i < n; ++i) {
                if (d[w.charAt(i) - 'a'] != num.charAt(i)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.add(w);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> getValidT9Words(string num, vector<string>& words) {
        string s = "22233344455566677778889999";
        int d[26];
        for (int i = 0; i < 26; ++i) {
            d[i] = s[i];
        }
        vector<string> ans;
        int n = num.size();
        for (auto& w : words) {
            bool ok = true;
            for (int i = 0; i < n; ++i) {
                if (d[w[i] - 'a'] != num[i]) {
                    ok = false;
                }
            }
            if (ok) {
                ans.emplace_back(w);
            }
        }
        return ans;
    }
};
```

```go
func getValidT9Words(num string, words []string) (ans []string) {
	s := "22233344455566677778889999"
	d := [26]rune{}
	for i, c := range s {
		d[i] = c
	}
	for _, w := range words {
		ok := true
		for i, c := range w {
			if d[c-'a'] != rune(num[i]) {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, w)
		}
	}
	return
}
```

```ts
function getValidT9Words(num: string, words: string[]): string[] {
    const s = '22233344455566677778889999';
    const d: string[] = Array(26);
    for (let i = 0; i < 26; ++i) {
        d[i] = s[i];
    }
    const ans: string[] = [];
    const n = num.length;
    for (const w of words) {
        let ok = true;
        for (let i = 0; i < n; ++i) {
            if (d[w[i].charCodeAt(0) - 97] !== num[i]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            ans.push(w);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def getValidT9Words(self, num: str, words: List[str]) -> List[str]:
        trans = str.maketrans(ascii_lowercase, "22233344455566677778889999")
        return [w for w in words if w.translate(trans) == num]
```

<!-- tabs:end -->

<!-- end -->
