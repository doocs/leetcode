---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2103.Rings%20and%20Rods/README_EN.md
rating: 1257
source: Weekly Contest 271 Q1
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

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

<!-- solution:start -->

### Solution 1: Bit Manipulation

We can use an array $mask$ of length $10$ to represent the color situation of the rings on each rod, where $mask[i]$ represents the color situation of the ring on the $i$th rod. If there are red, green, and blue rings on the $i$th rod, then the binary representation of $mask[i]$ is $111$, that is, $mask[i] = 7$.

We traverse the string $rings$. For each color position pair $(c, j)$, where $c$ represents the color of the ring and $j$ represents the number of the rod where the ring is located, we set the corresponding binary bit of $mask[j]$, that is, $mask[j] |= d[c]$, where $d[c]$ represents the binary bit corresponding to color $c$.

Finally, we count the number of elements in $mask$ that are $7$, which is the number of rods that have collected all three colors of rings.

The time complexity is $O(n)$, and the space complexity is $O(|\Sigma|)$, where $n$ represents the length of the string $rings$, and $|\Sigma|$ represents the size of the character set.

<!-- tabs:start -->

```python
class Solution:
    def countPoints(self, rings: str) -> int:
        mask = [0] * 10
        d = {"R": 1, "G": 2, "B": 4}
        for i in range(0, len(rings), 2):
            c = rings[i]
            j = int(rings[i + 1])
            mask[j] |= d[c]
        return mask.count(7)
```

```java
class Solution {
    public int countPoints(String rings) {
        int[] d = new int['Z'];
        d['R'] = 1;
        d['G'] = 2;
        d['B'] = 4;
        int[] mask = new int[10];
        for (int i = 0, n = rings.length(); i < n; i += 2) {
            int c = rings.charAt(i);
            int j = rings.charAt(i + 1) - '0';
            mask[j] |= d[c];
        }
        int ans = 0;
        for (int x : mask) {
            if (x == 7) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countPoints(string rings) {
        int d['Z']{['R'] = 1, ['G'] = 2, ['B'] = 4};
        int mask[10]{};
        for (int i = 0, n = rings.size(); i < n; i += 2) {
            int c = rings[i];
            int j = rings[i + 1] - '0';
            mask[j] |= d[c];
        }
        return count(mask, mask + 10, 7);
    }
};
```

```go
func countPoints(rings string) (ans int) {
	d := ['Z']int{'R': 1, 'G': 2, 'B': 4}
	mask := [10]int{}
	for i, n := 0, len(rings); i < n; i += 2 {
		c := rings[i]
		j := int(rings[i+1] - '0')
		mask[j] |= d[c]
	}
	for _, x := range mask {
		if x == 7 {
			ans++
		}
	}
	return
}
```

```ts
function countPoints(rings: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'A'.charCodeAt(0);
    const d: number[] = Array(26).fill(0);
    d[idx('R')] = 1;
    d[idx('G')] = 2;
    d[idx('B')] = 4;
    const mask: number[] = Array(10).fill(0);
    for (let i = 0; i < rings.length; i += 2) {
        const c = rings[i];
        const j = rings[i + 1].charCodeAt(0) - '0'.charCodeAt(0);
        mask[j] |= d[idx(c)];
    }
    return mask.filter(x => x === 7).length;
}
```

```rust
impl Solution {
    pub fn count_points(rings: String) -> i32 {
        let mut d: [i32; 90] = [0; 90];
        d['R' as usize] = 1;
        d['G' as usize] = 2;
        d['B' as usize] = 4;

        let mut mask: [i32; 10] = [0; 10];

        let cs: Vec<char> = rings.chars().collect();

        for i in (0..cs.len()).step_by(2) {
            let c = cs[i] as usize;
            let j = (cs[i + 1] as usize) - ('0' as usize);
            mask[j] |= d[c];
        }

        mask
            .iter()
            .filter(|&&x| x == 7)
            .count() as i32
    }
}
```

```c
int countPoints(char* rings) {
    int d['Z'];
    memset(d, 0, sizeof(d));
    d['R'] = 1;
    d['G'] = 2;
    d['B'] = 4;

    int mask[10];
    memset(mask, 0, sizeof(mask));

    for (int i = 0, n = strlen(rings); i < n; i += 2) {
        int c = rings[i];
        int j = rings[i + 1] - '0';
        mask[j] |= d[c];
    }

    int ans = 0;
    for (int i = 0; i < 10; i++) {
        if (mask[i] == 7) {
            ans++;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```ts
function countPoints(rings: string): number {
    let c = 0;
    for (let i = 0; i <= 9; i++) {
        if (rings.includes('B' + i) && rings.includes('R' + i) && rings.includes('G' + i)) c++;
    }
    return c;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
