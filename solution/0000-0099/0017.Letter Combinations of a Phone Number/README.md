---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/README.md
tags:
    - 哈希表
    - 字符串
    - 回溯
---

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

### 方法一：遍历

我们先用一个数组或者哈希表存储每个数字对应的字母，然后遍历每个数字，将其对应的字母与之前的结果进行组合，得到新的结果。

时间复杂度 $O(4^n)$。空间复杂度 $O(4^n)$。其中 $n$ 是输入数字的长度。

<!-- tabs:start -->

```python
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []
        d = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        ans = [""]
        for i in digits:
            s = d[int(i) - 2]
            ans = [a + b for a in ans for b in s]
        return ans
```

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

```cpp
class Solution {
public:
    vector<string> letterCombinations(string digits) {
        if (digits.empty()) {
            return {};
        }
        vector<string> d = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        vector<string> ans = {""};
        for (auto& i : digits) {
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

```ts
function letterCombinations(digits: string): string[] {
    if (digits.length == 0) {
        return [];
    }
    const ans: string[] = [''];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    for (const i of digits) {
        const s = d[parseInt(i) - 2];
        const t: string[] = [];
        for (const a of ans) {
            for (const b of s) {
                t.push(a + b);
            }
        }
        ans.splice(0, ans.length, ...t);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn letter_combinations(digits: String) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();
        if digits.is_empty() {
            return ans;
        }
        ans.push("".to_string());
        let d = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"];
        for i in digits.chars() {
            let s = &d[((i as u8) - b'2') as usize];
            let mut t: Vec<String> = Vec::new();
            for a in &ans {
                for b in s.chars() {
                    t.push(format!("{}{}", a, b));
                }
            }
            ans = t;
        }
        ans
    }
}
```

```js
/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function (digits) {
    if (digits.length == 0) {
        return [];
    }
    const ans = [''];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    for (const i of digits) {
        const s = d[parseInt(i) - 2];
        const t = [];
        for (const a of ans) {
            for (const b of s) {
                t.push(a + b);
            }
        }
        ans.splice(0, ans.length, ...t);
    }
    return ans;
};
```

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

<!-- tabs:end -->

### 方法二：DFS

我们可以使用深度优先搜索的方法，枚举所有可能的字母组合。假设当前已经产生了一部分字母组合，但是还有一些数字没有被穷举到，此时我们取出下一个数字所对应的字母，然后依次枚举这个数字所对应的每一个字母，将它们添加到前面已经产生的字母组合后面，形成所有可能的组合。

时间复杂度 $O(4^n)$。空间复杂度 $O(n)$。其中 $n$ 是输入数字的长度。

<!-- tabs:start -->

```python
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        def dfs(i: int):
            if i >= len(digits):
                ans.append("".join(t))
                return
            for c in d[int(digits[i]) - 2]:
                t.append(c)
                dfs(i + 1)
                t.pop()

        if not digits:
            return []
        d = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        ans = []
        t = []
        dfs(0)
        return ans
```

```java
class Solution {
    private final String[] d = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private String digits;
    private List<String> ans = new ArrayList<>();
    private StringBuilder t = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return ans;
        }
        this.digits = digits;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= digits.length()) {
            ans.add(t.toString());
            return;
        }
        String s = d[digits.charAt(i) - '2'];
        for (char c : s.toCharArray()) {
            t.append(c);
            dfs(i + 1);
            t.deleteCharAt(t.length() - 1);
        }
    }
}
```

```cpp
class Solution {
public:
    vector<string> letterCombinations(string digits) {
        if (digits.empty()) {
            return {};
        }
        vector<string> d = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        vector<string> ans;
        string t;
        function<void(int)> dfs = [&](int i) {
            if (i >= digits.size()) {
                ans.push_back(t);
                return;
            }
            for (auto& c : d[digits[i] - '2']) {
                t.push_back(c);
                dfs(i + 1);
                t.pop_back();
            }
        };
        dfs(0);
        return ans;
    }
};
```

```go
func letterCombinations(digits string) (ans []string) {
	d := []string{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}
	t := []rune{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(digits) {
			ans = append(ans, string(t))
			return
		}
		for _, c := range d[digits[i]-'2'] {
			t = append(t, c)
			dfs(i + 1)
			t = t[:len(t)-1]
		}
	}
	if len(digits) == 0 {
		return
	}
	dfs(0)
	return
}
```

```ts
function letterCombinations(digits: string): string[] {
    if (digits.length == 0) {
        return [];
    }
    const ans: string[] = [];
    const t: string[] = [];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    const dfs = (i: number) => {
        if (i >= digits.length) {
            ans.push(t.join(''));
            return;
        }
        const s = d[parseInt(digits[i]) - 2];
        for (const c of s) {
            t.push(c);
            dfs(i + 1);
            t.pop();
        }
    };
    dfs(0);
    return ans;
}
```

```rust
impl Solution {
    pub fn letter_combinations(digits: String) -> Vec<String> {
        let d = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"];
        let mut ans = Vec::new();
        let mut t = String::new();
        if digits.is_empty() {
            return ans;
        }
        Solution::dfs(&digits, &d, &mut t, &mut ans, 0);
        ans
    }

    fn dfs(digits: &String, d: &[&str; 8], t: &mut String, ans: &mut Vec<String>, i: usize) {
        if i >= digits.len() {
            ans.push(t.clone());
            return;
        }
        let s = d[((digits.chars().nth(i).unwrap() as u8) - b'2') as usize];
        for c in s.chars() {
            t.push(c);
            Solution::dfs(digits, d, t, ans, i + 1);
            t.pop();
        }
    }
}
```

```js
/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function (digits) {
    if (digits.length == 0) {
        return [];
    }
    const ans = [];
    const t = [];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    const dfs = i => {
        if (i >= digits.length) {
            ans.push(t.join(''));
            return;
        }
        const s = d[parseInt(digits[i]) - 2];
        for (const c of s) {
            t.push(c);
            dfs(i + 1);
            t.pop();
        }
    };
    dfs(0);
    return ans;
};
```

```cs
public class Solution {
    private readonly string[] d = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private string digits;
    private List<string> ans = new List<string>();
    private System.Text.StringBuilder t = new System.Text.StringBuilder();

    public IList<string> LetterCombinations(string digits) {
        if (digits.Length == 0) {
            return ans;
        }
        this.digits = digits;
        Dfs(0);
        return ans;
    }

    private void Dfs(int i) {
        if (i >= digits.Length) {
            ans.Add(t.ToString());
            return;
        }
        string s = d[digits[i] - '2'];
        foreach (char c in s) {
            t.Append(c);
            Dfs(i + 1);
            t.Remove(t.Length - 1, 1);
        }
    }
}
```

```php
class Solution {
    /**
     * @param string $digits
     * @return string[]
     */

    function letterCombinations($digits) {
        $digitMap = [
            '2' => ['a', 'b', 'c'],
            '3' => ['d', 'e', 'f'],
            '4' => ['g', 'h', 'i'],
            '5' => ['j', 'k', 'l'],
            '6' => ['m', 'n', 'o'],
            '7' => ['p', 'q', 'r', 's'],
            '8' => ['t', 'u', 'v'],
            '9' => ['w', 'x', 'y', 'z'],
        ];

        $combinations = [];

        backtrack($digits, '', 0, $digitMap, $combinations);

        return $combinations;
    }

    function backtrack($digits, $current, $index, $digitMap, &$combinations) {
        if ($index === strlen($digits)) {
            if ($current !== '') {
                $combinations[] = $current;
            }
            return;
        }

        $digit = $digits[$index];
        $letters = $digitMap[$digit];

        foreach ($letters as $letter) {
            backtrack($digits, $current . $letter, $index + 1, $digitMap, $combinations);
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
