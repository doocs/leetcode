# [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number)

[English Version](/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个仅包含数字&nbsp;<code>2-9</code>&nbsp;的字符串，返回所有它能表示的字母组合。答案可以按 <strong>任意顺序</strong> 返回。</p>

<p>给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/images/200px-telephone-keypad2svg.png" style="width: 200px;" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = "23"
<strong>输出：</strong>["ad","ae","af","bd","be","bf","cd","ce","cf"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = ""
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>digits = "2"
<strong>输出：</strong>["a","b","c"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= digits.length &lt;= 4</code></li>
	<li><code>digits[i]</code> 是范围 <code>['2', '9']</code> 的一个数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历**

我们先用一个数组或者哈希表存储每个数字对应的字母，然后遍历每个数字，将其对应的字母与之前的结果进行组合，得到新的结果。

时间复杂度 $O(4^n)$。其中 $n$ 是输入数字的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []
        d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz']
        ans = ['']
        for i in digits:
            s = d[int(i) - 2]
            ans = [a + b for a in ans for b in s]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        ans.add("");
        String[] d = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (char i : digits.toCharArray()) {
            String s = d[i - '2'];
            List<String> t = new ArrayList<>();
            for (String a : ans) {
                for (String b : s.split("")) {
                    t.add(a + b);
                }
            }
            ans = t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> letterCombinations(string digits) {
        if (digits.empty()) return {};
        vector<string> d = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        vector<string> ans = {""};
        for (auto i : digits) {
            string s = d[i - '2'];
            vector<string> t;
            for (auto& a : ans) {
                for (auto& b : s) {
                    t.push_back(a + b);
                }
            }
            ans = move(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func letterCombinations(digits string) []string {
	ans := []string{}
	if len(digits) == 0 {
		return ans
	}
	ans = append(ans, "")
	d := []string{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}
	for _, i := range digits {
		s := d[i-'2']
		t := []string{}
		for _, a := range ans {
			for _, b := range s {
				t = append(t, a+string(b))
			}
		}
		ans = t
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function (digits) {
    if (digits.length == 0) {
        return [];
    }
    let ans = [''];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    for (const i of digits) {
        const s = d[parseInt(i) - 2];
        const t = [];
        for (const a of ans) {
            for (const b of s) {
                t.push(a + b);
            }
        }
        ans = t;
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public IList<string> LetterCombinations(string digits) {
        var ans = new List<string>();
        if (digits.Length == 0) {
            return ans;
        }
        ans.Add("");
        string[] d = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        foreach (char i in digits) {
            string s = d[i - '2'];
            var t = new List<string>();
            foreach (string a in ans) {
                foreach (char b in s) {
                    t.Add(a + b);
                }
            }
            ans = t;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
const map = {
    '2': ['a', 'b', 'c'],
    '3': ['d', 'e', 'f'],
    '4': ['g', 'h', 'i'],
    '5': ['j', 'k', 'l'],
    '6': ['m', 'n', 'o'],
    '7': ['p', 'q', 'r', 's'],
    '8': ['t', 'u', 'v'],
    '9': ['w', 'x', 'y', 'z'],
};

function letterCombinations(digits: string): string[] {
    const n = digits.length;
    if (n === 0) {
        return [];
    }
    const res = [];
    const dfs = (i: number, str: string) => {
        if (i === n) {
            res.push(str);
            return;
        }
        for (const c of map[digits[i]]) {
            dfs(i + 1, str + c);
        }
    };
    dfs(0, '');
    return res;
}
```

```ts
const map = {
    '2': ['a', 'b', 'c'],
    '3': ['d', 'e', 'f'],
    '4': ['g', 'h', 'i'],
    '5': ['j', 'k', 'l'],
    '6': ['m', 'n', 'o'],
    '7': ['p', 'q', 'r', 's'],
    '8': ['t', 'u', 'v'],
    '9': ['w', 'x', 'y', 'z'],
};

function letterCombinations(digits: string): string[] {
    const n = digits.length;
    if (n === 0) {
        return [];
    }
    const dfs = (i: number, ss: string[]) => {
        if (i === n) {
            return ss;
        }
        const t = [];
        for (const c of map[digits[i]]) {
            for (const s of ss) {
                t.push(s + c);
            }
        }
        return dfs(i + 1, t);
    };
    return dfs(1, map[digits[0]]);
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    fn dfs(
        i: usize,
        s: &mut String,
        cs: &Vec<char>,
        map: &HashMap<char, String>,
        res: &mut Vec<String>,
    ) {
        if i == cs.len() {
            res.push(s.clone());
            return;
        }
        for c in map.get(&cs[i]).unwrap().chars() {
            s.push(c);
            Self::dfs(i + 1, s, cs, map, res);
            s.pop();
        }
    }

    pub fn letter_combinations(digits: String) -> Vec<String> {
        let mut res = vec![];
        if digits.is_empty() {
            return res;
        }

        let mut map = HashMap::new();
        map.insert('2', String::from("abc"));
        map.insert('3', String::from("def"));
        map.insert('4', String::from("ghi"));
        map.insert('5', String::from("jkl"));
        map.insert('6', String::from("mno"));
        map.insert('7', String::from("pqrs"));
        map.insert('8', String::from("tuv"));
        map.insert('9', String::from("wxyz"));

        Self::dfs(
            0,
            &mut String::new(),
            &digits.chars().collect(),
            &map,
            &mut res,
        );
        res
    }
}
```

```rust
impl Solution {
    fn dfs(i: usize, digits: &[u8], map: &Vec<Vec<char>>, s: &mut String, res: &mut Vec<String>) {
        if i == digits.len() {
            res.push(s.clone());
            return;
        }
        for c in map[(digits[i] - b'2') as usize].iter() {
            s.push(*c);
            Self::dfs(i + 1, digits, map, s, res);
            s.pop();
        }
    }

    pub fn letter_combinations(digits: String) -> Vec<String> {
        if digits.is_empty() {
            return Vec::new();
        }
        let digits = digits.as_bytes();
        let map = vec![
            vec!['a', 'b', 'c'],
            vec!['d', 'e', 'f'],
            vec!['g', 'h', 'i'],
            vec!['j', 'k', 'l'],
            vec!['m', 'n', 'o'],
            vec!['p', 'q', 'r', 's'],
            vec!['t', 'u', 'v'],
            vec!['w', 'x', 'y', 'z'],
        ];
        let mut res = Vec::new();
        Self::dfs(0, digits, &map, &mut String::new(), &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
