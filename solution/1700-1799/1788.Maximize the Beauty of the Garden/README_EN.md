# [1788. Maximize the Beauty of the Garden 🔒](https://leetcode.com/problems/maximize-the-beauty-of-the-garden)

[中文文档](/solution/1700-1799/1788.Maximize%20the%20Beauty%20of%20the%20Garden/README.md)

<!-- tags:Greedy,Array,Prefix Sum -->

<!-- difficulty:Hard -->

## Description

<p>There is a garden of <code>n</code> flowers, and each flower has an integer beauty value. The flowers are arranged in a line. You are given an integer array <code>flowers</code> of size <code>n</code> and each <code>flowers[i]</code> represents the beauty of the <code>i<sup>th</sup></code> flower.</p>

<p>A garden is <strong>valid</strong> if it meets these conditions:</p>

<ul>

    <li>The garden has at least two flowers.</li>

    <li>The first and the last flower of the garden have the same beauty value.</li>

</ul>

<p>As the appointed gardener, you have the ability to <strong>remove</strong> any (possibly none) flowers from the garden. You want to remove flowers in a way that makes the remaining garden <strong>valid</strong>. The beauty of the garden is the sum of the beauty of all the remaining flowers.</p>

<p>Return the maximum possible beauty of some <strong>valid</strong> garden after you have removed any (possibly none) flowers.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> flowers = [1,2,3,1,2]

<strong>Output:</strong> 8

<strong>Explanation:</strong> You can produce the valid garden [2,3,1,2] to have a total beauty of 2 + 3 + 1 + 2 = 8.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> flowers = [100,1,1,-3,1]

<strong>Output:</strong> 3

<strong>Explanation:</strong> You can produce the valid garden [1,1,1] to have a total beauty of 1 + 1 + 1 = 3.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> flowers = [-1,-2,0,-1]

<strong>Output:</strong> -2

<strong>Explanation:</strong> You can produce the valid garden [-1,-1] to have a total beauty of -1 + -1 = -2.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>2 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>

    <li><code>-10<sup>4</sup> &lt;= flowers[i] &lt;= 10<sup>4</sup></code></li>

    <li>It is possible to create a valid garden by removing some (possibly none) flowers.</li>

</ul>

## Solutions

### Solution 1: Hash Table + Prefix Sum

We use a hash table $d$ to record the first occurrence of each aesthetic value, and a prefix sum array $s$ to record the sum of the aesthetic values before the current position. If an aesthetic value $v$ appears at positions $i$ and $j$ (where $i \lt j$), then we can get a valid garden $[i+1,j]$, whose aesthetic value is $s[i] - s[j + 1] + v \times 2$. We use this value to update the answer. Otherwise, we record the current position $i$ of the aesthetic value in the hash table $d$. Next, we update the prefix sum. If the aesthetic value $v$ is negative, we treat it as $0$.

After traversing all the aesthetic values, we can get the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of flowers.

<!-- tabs:start -->

```python
class Solution:
    def maximumBeauty(self, flowers: List[int]) -> int:
        s = [0] * (len(flowers) + 1)
        d = {}
        ans = -inf
        for i, v in enumerate(flowers):
            if v in d:
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2)
            else:
                d[v] = i
            s[i + 1] = s[i] + max(v, 0)
        return ans
```

```java
class Solution {
    public int maximumBeauty(int[] flowers) {
        int n = flowers.length;
        int[] s = new int[n + 1];
        Map<Integer, Integer> d = new HashMap<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int v = flowers[i];
            if (d.containsKey(v)) {
                ans = Math.max(ans, s[i] - s[d.get(v) + 1] + v * 2);
            } else {
                d.put(v, i);
            }
            s[i + 1] = s[i] + Math.max(v, 0);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumBeauty(vector<int>& flowers) {
        int n = flowers.size();
        vector<int> s(n + 1);
        unordered_map<int, int> d;
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int v = flowers[i];
            if (d.count(v)) {
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2);
            } else {
                d[v] = i;
            }
            s[i + 1] = s[i] + max(v, 0);
        }
        return ans;
    }
};
```

```go
func maximumBeauty(flowers []int) int {
	n := len(flowers)
	s := make([]int, n+1)
	d := map[int]int{}
	ans := math.MinInt32
	for i, v := range flowers {
		if j, ok := d[v]; ok {
			ans = max(ans, s[i]-s[j+1]+v*2)
		} else {
			d[v] = i
		}
		s[i+1] = s[i] + max(v, 0)
	}
	return ans
}
```

```ts
function maximumBeauty(flowers: number[]): number {
    const n = flowers.length;
    const s: number[] = Array(n + 1).fill(0);
    const d: Map<number, number> = new Map();
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        const v = flowers[i];
        if (d.has(v)) {
            ans = Math.max(ans, s[i] - s[d.get(v)! + 1] + v * 2);
        } else {
            d.set(v, i);
        }
        s[i + 1] = s[i] + Math.max(v, 0);
    }
    return ans;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn maximum_beauty(flowers: Vec<i32>) -> i32 {
        let mut s = vec![0; flowers.len() + 1];
        let mut d = HashMap::new();
        let mut ans = i32::MIN;

        for (i, &v) in flowers.iter().enumerate() {
            if let Some(&j) = d.get(&v) {
                ans = ans.max(s[i] - s[j + 1] + v * 2);
            } else {
                d.insert(v, i);
            }
            s[i + 1] = s[i] + v.max(0);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
