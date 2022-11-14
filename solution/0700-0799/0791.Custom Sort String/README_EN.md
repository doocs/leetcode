# [791. Custom Sort String](https://leetcode.com/problems/custom-sort-string)

[中文文档](/solution/0700-0799/0791.Custom%20Sort%20String/README.md)

## Description

<p>You are given two strings order and s. All the characters of <code>order</code> are <strong>unique</strong> and were sorted in some custom order previously.</p>

<p>Permute the characters of <code>s</code> so that they match the order that <code>order</code> was sorted. More specifically, if a character <code>x</code> occurs before a character <code>y</code> in <code>order</code>, then <code>x</code> should occur before <code>y</code> in the permuted string.</p>

<p>Return <em>any permutation of </em><code>s</code><em> that satisfies this property</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> order = &quot;cba&quot;, s = &quot;abcd&quot;
<strong>Output:</strong> &quot;cbad&quot;
<strong>Explanation:</strong> 
&quot;a&quot;, &quot;b&quot;, &quot;c&quot; appear in order, so the order of &quot;a&quot;, &quot;b&quot;, &quot;c&quot; should be &quot;c&quot;, &quot;b&quot;, and &quot;a&quot;. 
Since &quot;d&quot; does not appear in order, it can be at any position in the returned string. &quot;dcba&quot;, &quot;cdba&quot;, &quot;cbda&quot; are also valid outputs.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> order = &quot;cbafg&quot;, s = &quot;abcd&quot;
<strong>Output:</strong> &quot;cbad&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= order.length &lt;= 26</code></li>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>order</code> and <code>s</code> consist of lowercase English letters.</li>
	<li>All the characters of <code>order</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        d = {c: i for i, c in enumerate(order)}
        return ''.join(sorted(s, key=lambda x: d.get(x, 0)))
```

```python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        cnt = Counter(s)
        ans = []
        for c in order:
            ans.append(c * cnt[c])
            cnt[c] = 0
        for c, v in cnt.items():
            ans.append(c * v)
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] d = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            d[order.charAt(i) - 'a'] = i;
        }
        List<Character> cs = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            cs.add(s.charAt(i));
        }
        cs.sort((a, b) -> d[a - 'a'] - d[b - 'a']);
        return cs.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
```

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < order.length(); ++i) {
            char c = order.charAt(i);
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]-- > 0) {
                ans.append((char) ('a' + i));
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string customSortString(string order, string s) {
        int d[26] = {0};
        for (int i = 0; i < order.size(); ++i) d[order[i] - 'a'] = i;
        sort(s.begin(), s.end(), [&](auto a, auto b) { return d[a - 'a'] < d[b - 'a']; });
        return s;
    }
};
```

```cpp
class Solution {
public:
    string customSortString(string order, string s) {
        int cnt[26] = {0};
        for (char& c : s) ++cnt[c - 'a'];
        string ans;
        for (char& c : order) while (cnt[c - 'a']-- > 0) ans += c;
        for (int i = 0; i < 26; ++i) if (cnt[i] > 0) ans += string(cnt[i], i + 'a');
        return ans;
    }
};
```

### **Go**

```go
func customSortString(order string, s string) string {
	d := [26]int{}
	for i := range order {
		d[order[i]-'a'] = i
	}
	cs := []byte(s)
	sort.Slice(cs, func(i, j int) bool { return d[cs[i]-'a'] < d[cs[j]-'a'] })
	return string(cs)
}
```

```go
func customSortString(order string, s string) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	ans := []rune{}
	for _, c := range order {
		for cnt[c-'a'] > 0 {
			ans = append(ans, c)
			cnt[c-'a']--
		}
	}
	for i, v := range cnt {
		for j := 0; j < v; j++ {
			ans = append(ans, rune('a'+i))
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function customSortString(order: string, s: string): string {
    const toIndex = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = order.length;
    const d = new Array(26).fill(n);
    for (let i = 0; i < n; i++) {
        d[toIndex(order[i])] = i;
    }
    return [...s].sort((a, b) => d[toIndex(a)] - d[toIndex(b)]).join('');
}
```

```ts
function customSortString(order: string, s: string): string {
    const toIndex = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const count = new Array(26).fill(0);
    for (const c of s) {
        count[toIndex(c)]++;
    }
    const ans: string[] = [];
    for (const c of order) {
        const i = toIndex(c);
        ans.push(c.repeat(count[i]));
        count[i] = 0;
    }
    for (let i = 0; i < 26; i++) {
        if (!count[i]) continue;
        ans.push(String.fromCharCode('a'.charCodeAt(0) + i).repeat(count[i]));
    }
    return ans.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn custom_sort_string(order: String, s: String) -> String {
        let n = order.len();
        let mut d = [n; 26];
        for (i, c) in order.as_bytes().iter().enumerate() {
            d[(c - b'a') as usize] = i;
        }
        let mut ans = s.chars().collect::<Vec<_>>();
        ans.sort_by(|&a, &b| d[(a as u8 - 'a' as u8) as usize].cmp(&d[(b as u8 - 'a' as u8) as usize]));
        ans.into_iter().collect()
    }
}
```

```rust
impl Solution {
    pub fn custom_sort_string(order: String, s: String) -> String {
        let mut count = [0; 26];
        for c in s.as_bytes() {
            count[(c - b'a') as usize] += 1;
        }
        let mut ans = String::new();
        for c in order.as_bytes() {
            for _ in 0..count[(c - b'a') as usize] {
                ans.push(char::from(*c));
            }
            count[(c - b'a') as usize] = 0;
        }
        for i in 0..count.len() {
            for _ in 0..count[i] {
                ans.push(char::from(b'a' + i as u8));
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
