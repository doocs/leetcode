---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3167.Better%20Compression%20of%20String/README_EN.md
tags:
    - Hash Table
    - String
    - Counting
    - Sorting
---

<!-- problem:start -->

# [3167. Better Compression of String 🔒](https://leetcode.com/problems/better-compression-of-string)

[中文文档](/solution/3100-3199/3167.Better%20Compression%20of%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>compressed</code> representing a compressed version of a string. The format is a character followed by its frequency. For example, <code>&quot;a3b1a1c2&quot;</code> is a compressed version of the string <code>&quot;aaabacc&quot;</code>.</p>

<p>We seek a <strong>better compression</strong> with the following conditions:</p>

<ol>
	<li>Each character should appear <strong>only once</strong> in the compressed version.</li>
	<li>The characters should be in <strong>alphabetical order</strong>.</li>
</ol>

<p>Return the <em>better compression</em> of <code>compressed</code>.</p>

<p><strong>Note:</strong> In the better version of compression, the order of letters may change, which is acceptable.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">compressed = &quot;a3c9b2c1&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;a3b2c10&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Characters &quot;a&quot; and &quot;b&quot; appear only once in the input, but &quot;c&quot; appears twice, once with a size of 9 and once with a size of 1.</p>

<p>Hence, in the resulting string, it should have a size of 10.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">compressed = &quot;c2b3a1&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;a1b3c2&quot;</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">compressed = &quot;a2b4c1&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;a2b4c1&quot;</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= compressed.length &lt;= 6 * 10<sup>4</sup></code></li>
	<li><code>compressed</code> consists only of lowercase English letters and digits.</li>
	<li><code>compressed</code> is a valid compression, i.e., each character is followed by its frequency.</li>
	<li>Frequencies are in the range <code>[1, 10<sup>4</sup>]</code> and have no leading zeroes.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Two Pointers

We can use a hash table to count the frequency of each character, and then use two pointers to traverse the `compressed` string, adding the frequency of each character to the hash table. Finally, we concatenate the characters and frequencies into a string in alphabetical order.

The time complexity is $O(n + |\Sigma| \log |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Where $n$ is the length of the string `compressed`, and $|\Sigma|$ is the size of the character set. Here, the character set is lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def betterCompression(self, compressed: str) -> str:
        cnt = Counter()
        i, n = 0, len(compressed)
        while i < n:
            j = i + 1
            x = 0
            while j < n and compressed[j].isdigit():
                x = x * 10 + int(compressed[j])
                j += 1
            cnt[compressed[i]] += x
            i = j
        return "".join(sorted(f"{k}{v}" for k, v in cnt.items()))
```

#### Java

```java
class Solution {
    public String betterCompression(String compressed) {
        Map<Character, Integer> cnt = new TreeMap<>();
        int i = 0;
        int n = compressed.length();
        while (i < n) {
            char c = compressed.charAt(i);
            int j = i + 1;
            int x = 0;
            while (j < n && Character.isDigit(compressed.charAt(j))) {
                x = x * 10 + (compressed.charAt(j) - '0');
                j++;
            }
            cnt.merge(c, x, Integer::sum);
            i = j;
        }
        StringBuilder ans = new StringBuilder();
        for (var e : cnt.entrySet()) {
            ans.append(e.getKey()).append(e.getValue());
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string betterCompression(string compressed) {
        map<char, int> cnt;
        int i = 0;
        int n = compressed.length();
        while (i < n) {
            char c = compressed[i];
            int j = i + 1;
            int x = 0;
            while (j < n && isdigit(compressed[j])) {
                x = x * 10 + (compressed[j] - '0');
                j++;
            }
            cnt[c] += x;
            i = j;
        }
        stringstream ans;
        for (const auto& entry : cnt) {
            ans << entry.first << entry.second;
        }
        return ans.str();
    }
};
```

#### Go

```go
func betterCompression(compressed string) string {
	cnt := map[byte]int{}
	n := len(compressed)
	for i := 0; i < n; {
		c := compressed[i]
		j := i + 1
		x := 0
		for j < n && compressed[j] >= '0' && compressed[j] <= '9' {
			x = x*10 + int(compressed[j]-'0')
			j++
		}
		cnt[c] += x
		i = j
	}
	ans := strings.Builder{}
	for c := byte('a'); c <= byte('z'); c++ {
		if cnt[c] > 0 {
			ans.WriteByte(c)
			ans.WriteString(strconv.Itoa(cnt[c]))
		}
	}
	return ans.String()
}
```

#### TypeScript

```ts
function betterCompression(compressed: string): string {
    const cnt = new Map<string, number>();
    const n = compressed.length;
    let i = 0;

    while (i < n) {
        const c = compressed[i];
        let j = i + 1;
        let x = 0;
        while (j < n && /\d/.test(compressed[j])) {
            x = x * 10 + +compressed[j];
            j++;
        }
        cnt.set(c, (cnt.get(c) || 0) + x);
        i = j;
    }
    const keys = Array.from(cnt.keys()).sort();
    const ans: string[] = [];
    for (const k of keys) {
        ans.push(`${k}${cnt.get(k)}`);
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
