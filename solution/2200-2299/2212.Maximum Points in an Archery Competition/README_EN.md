---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/README_EN.md
rating: 1868
source: Weekly Contest 285 Q3
tags:
    - Bit Manipulation
    - Array
    - Backtracking
    - Enumeration
---

<!-- problem:start -->

# [2212. Maximum Points in an Archery Competition](https://leetcode.com/problems/maximum-points-in-an-archery-competition)

[中文文档](/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are opponents in an archery competition. The competition has set the following rules:</p>

<ol>
	<li>Alice first shoots <code>numArrows</code> arrows and then Bob shoots <code>numArrows</code> arrows.</li>
	<li>The points are then calculated as follows:
	<ol>
		<li>The target has integer scoring sections ranging from <code>0</code> to <code>11</code> <strong>inclusive</strong>.</li>
		<li>For <strong>each</strong> section of the target with score <code>k</code> (in between <code>0</code> to <code>11</code>), say Alice and Bob have shot <code>a<sub>k</sub></code> and <code>b<sub>k</sub></code> arrows on that section respectively. If <code>a<sub>k</sub> &gt;= b<sub>k</sub></code>, then Alice takes <code>k</code> points. If <code>a<sub>k</sub> &lt; b<sub>k</sub></code>, then Bob takes <code>k</code> points.</li>
		<li>However, if <code>a<sub>k</sub> == b<sub>k</sub> == 0</code>, then <strong>nobody</strong> takes <code>k</code> points.</li>
	</ol>
	</li>
</ol>

<ul>
	<li>
	<p>For example, if Alice and Bob both shot <code>2</code> arrows on the section with score <code>11</code>, then Alice takes <code>11</code> points. On the other hand, if Alice shot <code>0</code> arrows on the section with score <code>11</code> and Bob shot <code>2</code> arrows on that same section, then Bob takes <code>11</code> points.</p>
	</li>
</ul>

<p>You are given the integer <code>numArrows</code> and an integer array <code>aliceArrows</code> of size <code>12</code>, which represents the number of arrows Alice shot on each scoring section from <code>0</code> to <code>11</code>. Now, Bob wants to <strong>maximize</strong> the total number of points he can obtain.</p>

<p>Return <em>the array </em><code>bobArrows</code><em> which represents the number of arrows Bob shot on <strong>each</strong> scoring section from </em><code>0</code><em> to </em><code>11</code>. The sum of the values in <code>bobArrows</code> should equal <code>numArrows</code>.</p>

<p>If there are multiple ways for Bob to earn the maximum total points, return <strong>any</strong> one of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/images/ex1.jpg" style="width: 600px; height: 120px;" />
<pre>
<strong>Input:</strong> numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
<strong>Output:</strong> [0,0,0,0,1,1,0,0,1,2,3,1]
<strong>Explanation:</strong> The table above shows how the competition is scored. 
Bob earns a total point of 4 + 5 + 8 + 9 + 10 + 11 = 47.
It can be shown that Bob cannot obtain a score higher than 47 points.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/images/ex2new.jpg" style="width: 600px; height: 117px;" />
<pre>
<strong>Input:</strong> numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
<strong>Output:</strong> [0,0,0,0,0,0,0,0,1,1,1,0]
<strong>Explanation:</strong> The table above shows how the competition is scored.
Bob earns a total point of 8 + 9 + 10 = 27.
It can be shown that Bob cannot obtain a score higher than 27 points.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numArrows &lt;= 10<sup>5</sup></code></li>
	<li><code>aliceArrows.length == bobArrows.length == 12</code></li>
	<li><code>0 &lt;= aliceArrows[i], bobArrows[i] &lt;= numArrows</code></li>
	<li><code>sum(aliceArrows[i]) == numArrows</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Enumeration

Since there are only $12$ regions, we use binary enumeration to determine in which regions $\textit{Bob}$ scores. We use a variable $\textit{st}$ to represent the scheme in which $\textit{Bob}$ obtains the maximum score, and $\textit{mx}$ to represent the maximum score $\textit{Bob}$ obtains.

We enumerate $\textit{Bob}$'s scoring schemes in the range $[1, 2^m)$, where $m$ is the length of $\textit{aliceArrows}$. For each scheme, we calculate $\textit{Bob}$'s score $\textit{s}$ and the number of arrows $\textit{cnt}$. If $\textit{cnt} \leq \textit{numArrows}$ and $\textit{s} > \textit{mx}$, we update $\textit{mx}$ and $\textit{st}$.

Then, we calculate $\textit{Bob}$'s scoring scheme based on $\textit{st}$. If there are any remaining arrows, we allocate the remaining arrows to the first region, which is the region with index $0$.

The time complexity is $O(2^m \times m)$, where $m$ is the length of $\textit{aliceArrows}$. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBobPoints(self, numArrows: int, aliceArrows: List[int]) -> List[int]:
        st = mx = 0
        m = len(aliceArrows)
        for mask in range(1, 1 << m):
            cnt = s = 0
            for i, x in enumerate(aliceArrows):
                if mask >> i & 1:
                    s += i
                    cnt += x + 1
            if cnt <= numArrows and s > mx:
                mx = s
                st = mask
        ans = [0] * m
        for i, x in enumerate(aliceArrows):
            if st >> i & 1:
                ans[i] = x + 1
                numArrows -= ans[i]
        ans[0] += numArrows
        return ans
```

#### Java

```java
class Solution {
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int st = 0, mx = 0;
        int m = aliceArrows.length;
        for (int mask = 1; mask < 1 << m; ++mask) {
            int cnt = 0, s = 0;
            for (int i = 0; i < m; ++i) {
                if ((mask >> i & 1) == 1) {
                    s += i;
                    cnt += aliceArrows[i] + 1;
                }
            }
            if (cnt <= numArrows && s > mx) {
                mx = s;
                st = mask;
            }
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            if ((st >> i & 1) == 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maximumBobPoints(int numArrows, vector<int>& aliceArrows) {
        int st = 0, mx = 0;
        int m = aliceArrows.size();
        for (int mask = 1; mask < 1 << m; ++mask) {
            int cnt = 0, s = 0;
            for (int i = 0; i < m; ++i) {
                if (mask >> i & 1) {
                    s += i;
                    cnt += aliceArrows[i] + 1;
                }
            }
            if (cnt <= numArrows && s > mx) {
                mx = s;
                st = mask;
            }
        }
        vector<int> ans(m);
        for (int i = 0; i < m; ++i) {
            if (st >> i & 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
};
```

#### Go

```go
func maximumBobPoints(numArrows int, aliceArrows []int) []int {
	st, mx := 0, 0
	m := len(aliceArrows)
	for mask := 1; mask < 1<<m; mask++ {
		cnt, s := 0, 0
		for i, x := range aliceArrows {
			if mask>>i&1 == 1 {
				s += i
				cnt += x + 1
			}
		}
		if cnt <= numArrows && s > mx {
			mx = s
			st = mask
		}
	}
	ans := make([]int, m)
	for i, x := range aliceArrows {
		if (st>>i)&1 == 1 {
			ans[i] = x + 1
			numArrows -= ans[i]
		}
	}
	ans[0] += numArrows
	return ans
}
```

#### TypeScript

```ts
function maximumBobPoints(numArrows: number, aliceArrows: number[]): number[] {
    let [st, mx] = [0, 0];
    const m = aliceArrows.length;
    for (let mask = 1; mask < 1 << m; mask++) {
        let [cnt, s] = [0, 0];
        for (let i = 0; i < m; i++) {
            if ((mask >> i) & 1) {
                cnt += aliceArrows[i] + 1;
                s += i;
            }
        }
        if (cnt <= numArrows && s > mx) {
            mx = s;
            st = mask;
        }
    }
    const ans: number[] = Array(m).fill(0);
    for (let i = 0; i < m; i++) {
        if ((st >> i) & 1) {
            ans[i] = aliceArrows[i] + 1;
            numArrows -= ans[i];
        }
    }
    ans[0] += numArrows;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_bob_points(num_arrows: i32, alice_arrows: Vec<i32>) -> Vec<i32> {
        let mut st = 0;
        let mut mx = 0;
        let m = alice_arrows.len();
        for mask in 1..(1 << m) {
            let mut cnt = 0;
            let mut s = 0;
            for i in 0..m {
                if (mask >> i) & 1 == 1 {
                    s += i as i32;
                    cnt += alice_arrows[i] + 1;
                }
            }
            if cnt <= num_arrows && s > mx {
                mx = s;
                st = mask;
            }
        }
        let mut ans = vec![0; m];
        let mut num_arrows = num_arrows;
        for i in 0..m {
            if (st >> i) & 1 == 1 {
                ans[i] = alice_arrows[i] + 1;
                num_arrows -= ans[i];
            }
        }
        ans[0] += num_arrows;
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} numArrows
 * @param {number[]} aliceArrows
 * @return {number[]}
 */
var maximumBobPoints = function (numArrows, aliceArrows) {
    let [st, mx] = [0, 0];
    const m = aliceArrows.length;
    for (let mask = 1; mask < 1 << m; mask++) {
        let [cnt, s] = [0, 0];
        for (let i = 0; i < m; i++) {
            if ((mask >> i) & 1) {
                cnt += aliceArrows[i] + 1;
                s += i;
            }
        }
        if (cnt <= numArrows && s > mx) {
            mx = s;
            st = mask;
        }
    }
    const ans = Array(m).fill(0);
    for (let i = 0; i < m; i++) {
        if ((st >> i) & 1) {
            ans[i] = aliceArrows[i] + 1;
            numArrows -= ans[i];
        }
    }
    ans[0] += numArrows;
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
