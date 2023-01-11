# [2283. Check if Number Has Equal Digit Count and Digit Value](https://leetcode.com/problems/check-if-number-has-equal-digit-count-and-digit-value)

[中文文档](/solution/2200-2299/2283.Check%20if%20Number%20Has%20Equal%20Digit%20Count%20and%20Digit%20Value/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>num</code> of length <code>n</code> consisting of digits.</p>

<p>Return <code>true</code> <em>if for <strong>every</strong> index </em><code>i</code><em> in the range </em><code>0 &lt;= i &lt; n</code><em>, the digit </em><code>i</code><em> occurs </em><code>num[i]</code><em> times in </em><code>num</code><em>, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;1210&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
num[0] = &#39;1&#39;. The digit 0 occurs once in num.
num[1] = &#39;2&#39;. The digit 1 occurs twice in num.
num[2] = &#39;1&#39;. The digit 2 occurs once in num.
num[3] = &#39;0&#39;. The digit 3 occurs zero times in num.
The condition holds true for every index in &quot;1210&quot;, so return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;030&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong>
num[0] = &#39;0&#39;. The digit 0 should occur zero times, but actually occurs twice in num.
num[1] = &#39;3&#39;. The digit 1 should occur three times, but actually occurs zero times in num.
num[2] = &#39;0&#39;. The digit 2 occurs zero times in num.
The indices 0 and 1 both violate the condition, so return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == num.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>num</code> consists of digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def digitCount(self, num: str) -> bool:
        cnt = Counter(num)
        return all(cnt[str(i)] == int(v) for i, v in enumerate(num))
```

### **Java**

```java
class Solution {
    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        int n = num.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[num.charAt(i) - '0'];
        }
        for (int i = 0; i < n; ++i) {
            if (cnt[i] != num.charAt(i) - '0') {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool digitCount(string num) {
        int cnt[10]{};
        for (char& c : num) {
            ++cnt[c - '0'];
        }
        for (int i = 0; i < num.size(); ++i) {
            if (cnt[i] != num[i] - '0') {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func digitCount(num string) bool {
	cnt := [10]int{}
	for _, c := range num {
		cnt[c-'0']++
	}
	for i, v := range num {
		if cnt[i] != int(v-'0') {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function digitCount(num: string): boolean {
    const n = num.length;
    const count = new Array(10).fill(0);
    for (let i = 0; i < n; i++) {
        count[i] = Number(num[i]);
    }
    for (const c of num) {
        count[c]--;
    }
    return count.every(v => v === 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn digit_count(num: String) -> bool {
        let s = num.as_bytes();
        let n = num.len();
        let mut count = [0; 10];
        for i in 0..n {
            count[i] = s[i] - b'0';
        }
        for c in s {
            count[(c - b'0') as usize] -= 1;
        }
        count.iter().all(|v| *v == 0)
    }
}
```

### **C**

```c
bool digitCount(char *num) {
    int count[10] = {0};
    for (int i = 0; num[i]; i++) {
        count[i] = num[i] - '0';
    }
    for (int i = 0; num[i]; i++) {
        count[num[i] - '0']--;
    }
    for (int i = 0; i < 10; i++) {
        if (count[i] != 0) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
