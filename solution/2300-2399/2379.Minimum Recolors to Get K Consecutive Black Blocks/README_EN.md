# [2379. Minimum Recolors to Get K Consecutive Black Blocks](https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks)

[中文文档](/solution/2300-2399/2379.Minimum%20Recolors%20to%20Get%20K%20Consecutive%20Black%20Blocks/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>blocks</code> of length <code>n</code>, where <code>blocks[i]</code> is either <code>&#39;W&#39;</code> or <code>&#39;B&#39;</code>, representing the color of the <code>i<sup>th</sup></code> block. The characters <code>&#39;W&#39;</code> and <code>&#39;B&#39;</code> denote the colors white and black, respectively.</p>

<p>You are also given an integer <code>k</code>, which is the desired number of <strong>consecutive</strong> black blocks.</p>

<p>In one operation, you can <strong>recolor</strong> a white block such that it becomes a black block.</p>

<p>Return<em> the <strong>minimum</strong> number of operations needed such that there is at least <strong>one</strong> occurrence of </em><code>k</code><em> consecutive black blocks.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> blocks = &quot;WBBWWBBWBW&quot;, k = 7
<strong>Output:</strong> 3
<strong>Explanation:</strong>
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
so that blocks = &quot;BBBBBBBWBW&quot;. 
It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
Therefore, we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> blocks = &quot;WBWBBBW&quot;, k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == blocks.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>blocks[i]</code> is either <code>&#39;W&#39;</code> or <code>&#39;B&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumRecolors(self, blocks: str, k: int) -> int:
        ans = cnt = blocks[:k].count('W')
        for i in range(k, len(blocks)):
            cnt += blocks[i] == 'W'
            cnt -= blocks[i - k] == 'W'
            ans = min(ans, cnt)
        return ans
```

### **Java**

```java
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
        }
        int ans = cnt;
        for (int i = k; i < blocks.length(); ++i) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
            cnt -= blocks.charAt(i - k) == 'W' ? 1 : 0;
            ans = Math.min(ans, cnt);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumRecolors(string blocks, int k) {
        int cnt = count(blocks.begin(), blocks.begin() + k, 'W');
        int ans = cnt;
        for (int i = k; i < blocks.size(); ++i) {
            cnt += blocks[i] == 'W';
            cnt -= blocks[i - k] == 'W';
            ans = min(ans, cnt);
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumRecolors(blocks string, k int) int {
	cnt := strings.Count(blocks[:k], "W")
	ans := cnt
	for i := k; i < len(blocks); i++ {
		if blocks[i] == 'W' {
			cnt++
		}
		if blocks[i-k] == 'W' {
			cnt--
		}
		if ans > cnt {
			ans = cnt
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumRecolors(blocks: string, k: number): number {
    let cnt = 0;
    for (let i = 0; i < k; ++i) {
        cnt += blocks[i] === 'W' ? 1 : 0;
    }
    let ans = cnt;
    for (let i = k; i < blocks.length; ++i) {
        cnt += blocks[i] === 'W' ? 1 : 0;
        cnt -= blocks[i - k] === 'W' ? 1 : 0;
        ans = Math.min(ans, cnt);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimum_recolors(blocks: String, k: i32) -> i32 {
        let k = k as usize;
        let s = blocks.as_bytes();
        let n = s.len();
        let mut count = 0;
        for i in 0..k {
            if s[i] == b'B' {
                count += 1;
            }
        }
        let mut ans = k - count;
        for i in k..n {
            if s[i - k] == b'B' {
                count -= 1;
            }
            if s[i] == b'B' {
                count += 1;
            }
            ans = ans.min(k - count);
        }
        ans as i32
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

int minimumRecolors(char *blocks, int k) {
    int n = strlen(blocks);
    int count = 0;
    for (int i = 0; i < k; i++) {
        count += blocks[i] == 'B';
    }
    int ans = k - count;
    for (int i = k; i < n; i++) {
        count -= blocks[i - k] == 'B';
        count += blocks[i] == 'B';
        ans = min(ans, k - count);
    }
    return ans;
}
```

### **...**

```


```

<!-- tabs:end -->
