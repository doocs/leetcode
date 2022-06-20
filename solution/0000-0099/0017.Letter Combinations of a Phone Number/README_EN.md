# [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number)

[中文文档](/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/README.md)

## Description

<p>Given a string containing digits from <code>2-9</code> inclusive, return all possible letter combinations that the number could represent. Return the answer in <strong>any order</strong>.</p>

<p>A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/images/1200px-telephone-keypad2svg.png" style="width: 300px; height: 243px;" />
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;23&quot;
<strong>Output:</strong> [&quot;ad&quot;,&quot;ae&quot;,&quot;af&quot;,&quot;bd&quot;,&quot;be&quot;,&quot;bf&quot;,&quot;cd&quot;,&quot;ce&quot;,&quot;cf&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;&quot;
<strong>Output:</strong> []
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;2&quot;
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= digits.length &lt;= 4</code></li>
	<li><code>digits[i]</code> is a digit in the range <code>[&#39;2&#39;, &#39;9&#39;]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        n = len(digits)
        if n == 0:
            return []
        chars = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz']
        strs = [chars[int(d) - 2] for d in digits]
        res = []
        for s in strs:
            if not res:
                res = list(s)
            else:
                cache = []
                for item in res:
                    for letter in s:
                        cache.append(item + letter)
                res = cache
        return res
```

### **Java**

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        int n;
        if ((n = digits.length()) == 0) return Collections.emptyList();
        List<String> chars = Arrays.asList("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");

        List<String> strs = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            strs.add(chars.get(c - '0' - 2));
        }
        List<String> res = new ArrayList<>();
        for (String str : strs) {
            if (res.size() == 0) {
                for (char c : str.toCharArray()) {
                    res.add(String.valueOf(c));
                }
            } else {
                List<String> cache = new ArrayList<>();
                for (String item : res) {
                    for (char c : str.toCharArray()) {
                        cache.add(item + String.valueOf(c));
                    }
                }
                res = cache;
            }
        }
        return res;
    }
}
```

### **Go**

```go
var table = map[string][]string{
	"2": {"a", "b", "c"},
	"3": {"d", "e", "f"},
	"4": {"g", "h", "i"},
	"5": {"j", "k", "l"},
	"6": {"m", "n", "o"},
	"7": {"p", "q", "r", "s"},
	"8": {"t", "u", "v"},
	"9": {"w", "x", "y", "z"},
}

func letterCombinations(digits string) []string {
	if digits == "" {
		return make([]string, 0)
	}
	var result = table[string(digits[0])]
	for i := 1; i < len(digits); i++ {
		t := table[string(digits[i])]
		nr := make([]string, len(result)*len(t))
		for j := 0; j < len(result); j++ {
			for k := 0; k < len(t); k++ {
				nr[len(t)*j+k] = result[j] + t[k]
			}
		}
		result = nr
	}
	return result
}
```

### **C#**

```cs
using System.Collections.Generic;
using System.Linq;

public class Solution {
    private static string[] chars = {
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    };

    public IList<string> LetterCombinations(string digits) {
        var numbers = digits.Where(d => d >= '2' && d <= '9').Select(d => d - '2').ToArray();
        var states = new int[numbers.Length];
        var results = new List<string>();
        if (numbers.Length == 0) return results;
        while (true) {
            results.Add(new string(states.Select((s, j) => chars[numbers[j]][s]).ToArray()));
            var i = states.Length - 1;
            ++states[i];
            while (i >= 0 && states[i] == chars[numbers[i]].Length)
            {
                states[i] = 0;
                --i;
                if (i >= 0)
                {
                    ++states[i];
                }
            }
            if (i < 0) return results;
        }
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
