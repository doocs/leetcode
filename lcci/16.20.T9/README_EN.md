# [16.20. T9](https://leetcode.cn/problems/t9-lcci)

[中文文档](/lcci/16.20.T9/README.md)

## Description

<p>On old cell phones, users typed on a numeric keypad and the phone would provide a list of words that matched these numbers. Each digit mapped to a set of 0&nbsp;- 4 letters. Implement an algo&shy;rithm to return a list of matching words, given a sequence of digits. You are provided a list of valid words. The mapping is shown in the diagram below:</p>
![](./images/17_telephone_keypad.png)
<p><strong>Example 1:</strong></p>
<pre>

<strong>Input:</strong> num = &quot;8733&quot;, words = [&quot;tree&quot;, &quot;used&quot;]

<strong>Output:</strong> [&quot;tree&quot;, &quot;used&quot;]

</pre>
<p><strong>Example 2:</strong></p>
<pre>

<strong>Input:</strong> num = &quot;2&quot;, words = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;d&quot;]

<strong>Output:</strong> [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]</pre>

<p>Note:</p>
<ul>
	<li><code>num.length &lt;= 1000</code></li>
	<li><code>words.length &lt;= 500</code></li>
	<li><code>words[i].length == num.length</code></li>
	<li><code>There are no number 0 and 1 in num</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getValidT9Words(self, num: str, words: List[str]) -> List[str]:
        def check(w: str) -> bool:
            return all(d[c] == num[i] for i, c in enumerate(w))

        d = {c: d for c, d in zip(ascii_lowercase, "22233344455566677778889999")}
        return [w for w in words if check(w)]
```

```python
class Solution:
    def getValidT9Words(self, num: str, words: List[str]) -> List[str]:
        trans = str.maketrans(ascii_lowercase, "22233344455566677778889999")
        return [w for w in words if w.translate(trans) == num]
```

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
