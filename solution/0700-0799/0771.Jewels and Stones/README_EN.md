# [771. Jewels and Stones](https://leetcode.com/problems/jewels-and-stones)

[中文文档](/solution/0700-0799/0771.Jewels%20and%20Stones/README.md)

## Description

<p>You&#39;re given strings <code>jewels</code> representing the types of stones that are jewels, and <code>stones</code> representing the stones you have. Each character in <code>stones</code> is a type of stone you have. You want to know how many of the stones you have are also jewels.</p>

<p>Letters are case sensitive, so <code>&quot;a&quot;</code> is considered a different type of stone from <code>&quot;A&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> jewels = "aA", stones = "aAAbbbb"
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> jewels = "z", stones = "ZZ"
<strong>Output:</strong> 0
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;jewels.length, stones.length &lt;= 50</code></li>
	<li><code>jewels</code> and <code>stones</code> consist of only English letters.</li>
	<li>All the characters of&nbsp;<code>jewels</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        s = set(jewels)
        return sum(c in s for c in stones)
```

### **Java**

```java
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int[] s = new int[128];
        for (char c : jewels.toCharArray()) {
            s[c] = 1;
        }
        int ans = 0;
        for (char c : stones.toCharArray()) {
            ans += s[c];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numJewelsInStones(string jewels, string stones) {
        int s[128] = {0};
        for (char c : jewels) s[c] = 1;
        int ans = 0;
        for (char c : stones) ans += s[c];
        return ans;
    }
};
```

### **Go**

```go
func numJewelsInStones(jewels string, stones string) (ans int) {
	s := make([]int, 128)
	for _, c := range jewels {
		s[c] = 1
	}
	for _, c := range stones {
		ans += s[c]
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {string} jewels
 * @param {string} stones
 * @return {number}
 */
var numJewelsInStones = function (jewels, stones) {
    const s = new Set(jewels.split(''));
    return stones.split('').reduce((prev, val) => prev + s.has(val), 0);
};
```

### **TypeScript**

```ts
function numJewelsInStones(jewels: string, stones: string): number {
    const set = new Set([...jewels]);
    let ans = 0;
    for (const c of stones) {
        set.has(c) && ans++;
    }
    return ans;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn num_jewels_in_stones(jewels: String, stones: String) -> i32 {
        let mut set = jewels.as_bytes().iter().collect::<HashSet<&u8>>();
        let mut ans = 0;
        for c in stones.as_bytes() {
            if set.contains(c) {
                ans += 1;
            }
        }
        ans
    }
}
```

### **C**

```c
int numJewelsInStones(char *jewels, char *stones) {
    int set[128] = {0};
    for (int i = 0; jewels[i]; i++) {
        set[jewels[i]] = 1;
    }
    int ans = 0;
    for (int i = 0; stones[i]; i++) {
        set[stones[i]] && ans++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
