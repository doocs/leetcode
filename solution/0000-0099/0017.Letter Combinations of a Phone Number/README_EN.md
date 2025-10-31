---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/README_EN.md
tags:
    - Hash Table
    - String
    - Backtracking
---

<!-- problem:start -->

# [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number)

[中文文档](/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/README.md)

## Description

<!-- description:start -->

<p>Given a string containing digits from <code>2-9</code> inclusive, return all possible letter combinations that the number could represent. Return the answer in <strong>any order</strong>.</p>

<p>A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/images/1200px-telephone-keypad2svg.png" style="width: 300px; height: 243px;" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;23&quot;
<strong>Output:</strong> [&quot;ad&quot;,&quot;ae&quot;,&quot;af&quot;,&quot;bd&quot;,&quot;be&quot;,&quot;bf&quot;,&quot;cd&quot;,&quot;ce&quot;,&quot;cf&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;2&quot;
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 4</code></li>
	<li><code>digits[i]</code> is a digit in the range <code>[&#39;2&#39;, &#39;9&#39;]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

First, we use an array or hash table to store the letters corresponding to each digit. Then we traverse each digit, combine its corresponding letters with the previous results to get the new results.

The time complexity is $O(4^n)$, and the space complexity is $O(4^n)$. Here, $n$ is the length of the input digits.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
function letterCombinations(digits: string): string[] {
    if (digits.length === 0) {
        return [];
    }
    const ans: string[] = [''];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    for (const i of digits) {
        const s = d[+i - 2];
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

#### Rust

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

#### JavaScript

```js
/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function (digits) {
    if (digits.length === 0) {
        return [];
    }
    const ans = [''];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    for (const i of digits) {
        const s = d[+i - 2];
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

#### C#

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

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: DFS

We can use the method of depth-first search to enumerate all possible letter combinations. Suppose that a part of the letter combination has been generated, but some digits have not been exhausted. At this time, we take out the letters corresponding to the next digit, and then enumerate each letter corresponding to this digit one by one, add them to the letter combination that has been generated before, to form all possible combinations.

The time complexity is $O(4^n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the input digits.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
function letterCombinations(digits: string): string[] {
    if (digits.length === 0) {
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
        const s = d[+digits[i] - 2];
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

#### Rust

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

#### JavaScript

```js
/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function (digits) {
    if (digits.length === 0) {
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
        const s = d[+digits[i] - 2];
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

#### C#

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

#### PHP

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

#### C

```c
char* d[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

char** letterCombinations(char* digits, int* returnSize) {
    if (!*digits) {
        *returnSize = 0;
        return NULL;
    }

    int size = 1;
    char** ans = (char**) malloc(sizeof(char*));
    ans[0] = strdup("");

    for (int x = 0; digits[x]; ++x) {
        char* s = d[digits[x] - '2'];
        int len = strlen(s);
        char** t = (char**) malloc(sizeof(char*) * size * len);
        int tSize = 0;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < len; ++j) {
                int oldLen = strlen(ans[i]);
                char* tmp = (char*) malloc(oldLen + 2);
                strcpy(tmp, ans[i]);
                tmp[oldLen] = s[j];
                tmp[oldLen + 1] = '\0';
                t[tSize++] = tmp;
            }
            free(ans[i]);
        }
        free(ans);
        ans = t;
        size = tSize;
    }

    *returnSize = size;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
