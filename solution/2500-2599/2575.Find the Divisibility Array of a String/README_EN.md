# [2575. Find the Divisibility Array of a String](https://leetcode.com/problems/find-the-divisibility-array-of-a-string)

[中文文档](/solution/2500-2599/2575.Find%20the%20Divisibility%20Array%20of%20a%20String/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>word</code> of length <code>n</code>&nbsp;consisting of digits, and a positive integer&nbsp;<code>m</code>.</p>

<p>The <strong>divisibility array</strong> <code>div</code> of <code>word</code> is an integer array of length <code>n</code> such that:</p>

<ul>
	<li><code>div[i] = 1</code> if the&nbsp;<strong>numeric value</strong>&nbsp;of&nbsp;<code>word[0,...,i]</code> is divisible by <code>m</code>, or</li>
	<li><code>div[i] = 0</code> otherwise.</li>
</ul>

<p>Return<em> the divisibility array of</em><em> </em><code>word</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;998244353&quot;, m = 3
<strong>Output:</strong> [1,1,0,0,0,1,1,0,0]
<strong>Explanation:</strong> There are only 4 prefixes that are divisible by 3: &quot;9&quot;, &quot;99&quot;, &quot;998244&quot;, and &quot;9982443&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;1010&quot;, m = 10
<strong>Output:</strong> [0,1,0,1]
<strong>Explanation:</strong> There are only 2 prefixes that are divisible by 10: &quot;10&quot;, and &quot;1010&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code><font face="monospace">word.length == n</font></code></li>
	<li><code><font face="monospace">word</font></code><font face="monospace"> consists of digits from <code>0</code>&nbsp;to <code>9</code></font></li>
	<li><code><font face="monospace">1 &lt;= m &lt;= 10<sup>9</sup></font></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def divisibilityArray(self, word: str, m: int) -> List[int]:
        ans = []
        x = 0
        for c in word:
            x = (x * 10 + int(c)) % m
            ans.append(1 if x == 0 else 0)
        return ans
```

### **Java**

```java
class Solution {
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ans = new int[n];
        long x = 0;
        for (int i = 0; i < n; ++i) {
            x = (x * 10 + word.charAt(i) - '0') % m;
            if (x == 0) {
                ans[i] = 1;
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
    vector<int> divisibilityArray(string word, int m) {
        vector<int> ans;
        long long x = 0;
        for (char& c : word) {
            x = (x * 10 + c - '0') % m;
            ans.push_back(x == 0 ? 1 : 0);
        }
        return ans;
    }
};
```

### **Go**

```go
func divisibilityArray(word string, m int) (ans []int) {
	x := 0
	for _, c := range word {
		x = (x*10 + int(c-'0')) % m
		if x == 0 {
			ans = append(ans, 1)
		} else {
			ans = append(ans, 0)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function divisibilityArray(word: string, m: number): number[] {
    const ans: number[] = [];
    let x = 0;
    for (const c of word) {
        x = (x * 10 + Number(c)) % m;
        ans.push(x === 0 ? 1 : 0);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn divisibility_array(word: String, m: i32) -> Vec<i32> {
        let m = m as i64;
        let mut x = 0i64;
        word.as_bytes()
            .iter()
            .map(|&c| {
                x = (x * 10 + i64::from(c - b'0')) % m;
                if x == 0 {
                    1
                } else {
                    0
                }
            })
            .collect()
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *divisibilityArray(char *word, int m, int *returnSize) {
    int n = strlen(word);
    int *ans = malloc(sizeof(int) * n);
    long long x = 0;
    for (int i = 0; i < n; i++) {
        x = (x * 10 + word[i] - '0') % m;
        ans[i] = x == 0 ? 1 : 0;
    }
    *returnSize = n;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
