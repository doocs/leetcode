# [2103. Rings and Rods](https://leetcode.com/problems/rings-and-rods)

[中文文档](/solution/2100-2199/2103.Rings%20and%20Rods/README.md)

## Description

<p>There are <code>n</code> rings and each ring is either red, green, or blue. The rings are distributed <strong>across ten rods</strong> labeled from <code>0</code> to <code>9</code>.</p>

<p>You are given a string <code>rings</code> of length <code>2n</code> that describes the <code>n</code> rings that are placed onto the rods. Every two characters in <code>rings</code> forms a <strong>color-position pair</strong> that is used to describe each ring where:</p>

<ul>
	<li>The <strong>first</strong> character of the <code>i<sup>th</sup></code> pair denotes the <code>i<sup>th</sup></code> ring&#39;s <strong>color</strong> (<code>&#39;R&#39;</code>, <code>&#39;G&#39;</code>, <code>&#39;B&#39;</code>).</li>
	<li>The <strong>second</strong> character of the <code>i<sup>th</sup></code> pair denotes the <strong>rod</strong> that the <code>i<sup>th</sup></code> ring is placed on (<code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>).</li>
</ul>

<p>For example, <code>&quot;R3G2B1&quot;</code> describes <code>n == 3</code> rings: a red ring placed onto the rod labeled 3, a green ring placed onto the rod labeled 2, and a blue ring placed onto the rod labeled 1.</p>

<p>Return <em>the number of rods that have <strong>all three colors</strong> of rings on them.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2103.Rings%20and%20Rods/images/ex1final.png" style="width: 258px; height: 130px;" />
<pre>
<strong>Input:</strong> rings = &quot;B0B6G0R6R0R6G9&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
- The rod labeled 0 holds 3 rings with all colors: red, green, and blue.
- The rod labeled 6 holds 3 rings, but it only has red and blue.
- The rod labeled 9 holds only a green ring.
Thus, the number of rods with all three colors is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2103.Rings%20and%20Rods/images/ex2final.png" style="width: 266px; height: 130px;" />
<pre>
<strong>Input:</strong> rings = &quot;B0R0G0R9R0B0G0&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
- The rod labeled 0 holds 6 rings with all colors: red, green, and blue.
- The rod labeled 9 holds only a red ring.
Thus, the number of rods with all three colors is 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> rings = &quot;G4&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
Only one ring is given. Thus, no rods have all three colors.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rings.length == 2 * n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>rings[i]</code> where <code>i</code> is <strong>even</strong> is either <code>&#39;R&#39;</code>, <code>&#39;G&#39;</code>, or <code>&#39;B&#39;</code> (<strong>0-indexed</strong>).</li>
	<li><code>rings[i]</code> where <code>i</code> is <strong>odd</strong> is a digit from <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code> (<strong>0-indexed</strong>).</li>
</ul>

## Solutions

Using hash table.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPoints(self, rings: str) -> int:
        mp = defaultdict(set)
        for i in range(1, len(rings), 2):
            c = int(rings[i])
            mp[c].add(rings[i - 1])
        return sum(len(v) == 3 for v in mp.values())
```

### **Java**

```java
class Solution {
    public int countPoints(String rings) {
        Map<Integer, Set<Character>> mp = new HashMap<>();
        for (int i = 1; i < rings.length(); i += 2) {
            int c = rings.charAt(i) - '0';
            mp.computeIfAbsent(c, k -> new HashSet<>()).add(rings.charAt(i - 1));
        }
        int ans = 0;
        for (Set<Character> e : mp.values()) {
            if (e.size() == 3) {
                ++ans;
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
    int countPoints(string rings) {
        unordered_map<int, unordered_set<char>> mp;
        for (int i = 1; i < rings.size(); i += 2) {
            int c = rings[i] - '0';
            mp[c].insert(rings[i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i)
            if (mp[i].size() == 3)
                ++ans;
        return ans;
    }
};
```

### **Go**

```go
func countPoints(rings string) int {
	mp := make(map[byte]map[byte]bool)
	for i := 1; i < len(rings); i += 2 {
		c := rings[i]
		if len(mp[c]) == 0 {
			mp[c] = make(map[byte]bool)
		}
		mp[c][rings[i-1]] = true
	}
	ans := 0
	for _, v := range mp {
		if len(v) == 3 {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function countPoints(rings: string): number {
    const helper = (c: string) => c.charCodeAt(0) - 'A'.charCodeAt(0);
    const n = rings.length;
    const target = (1 << helper('R')) + (1 << helper('G')) + (1 << helper('B'));
    const count = new Array(10).fill(0);
    for (let i = 0; i < n; i += 2) {
        count[rings[i + 1]] |= 1 << helper(rings[i]);
    }
    return count.reduce((r, v) => (r += v === target ? 1 : 0), 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_points(rings: String) -> i32 {
        let rings = rings.as_bytes();
        let target = (1 << b'R' - b'A') + (1 << b'G' - b'A') + (1 << b'B' - b'A');
        let n = rings.len();
        let mut count = [0; 10];
        let mut i = 0;
        while i < n {
            count[(rings[i + 1] - b'0') as usize] |= 1 << rings[i] - b'A';
            i += 2;
        }
        count.iter().filter(|&v| *v == target).count() as i32
    }
}
```

### **C**

```c
int countPoints(char *rings) {
    int target = (1 << ('R' - 'A')) + (1 << ('G' - 'A')) + (1 << ('B' - 'A'));
    int count[10] = {0};
    for (int i = 0; rings[i]; i += 2) {
        count[rings[i + 1] - '0'] |= 1 << (rings[i] - 'A');
    }
    int ans = 0;
    for (int i = 0; i < 10; i++) {
        if (count[i] == target) {
            ans++;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
