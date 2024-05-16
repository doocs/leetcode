---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3147.Taking%20Maximum%20Energy%20From%20the%20Mystic%20Dungeon/README_EN.md
---

<!-- problem:start -->

# [3147. Taking Maximum Energy From the Mystic Dungeon](https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon)

[中文文档](/solution/3100-3199/3147.Taking%20Maximum%20Energy%20From%20the%20Mystic%20Dungeon/README.md)

## Description

<!-- description:start -->

<p>In a mystic dungeon, <code>n</code> magicians are standing in a line. Each magician has an attribute that gives you energy. Some magicians can give you negative energy, which means taking energy from you.</p>

<p>You have been cursed in such a way that after absorbing energy from magician <code>i</code>, you will be instantly transported to magician <code>(i + k)</code>. This process will be repeated until you reach the magician where <code>(i + k)</code> does not exist.</p>

<p>In other words, you will choose a starting point and then teleport with <code>k</code> jumps until you reach the end of the magicians&#39; sequence, <strong>absorbing all the energy</strong> during the journey.</p>

<p>You are given an array <code>energy</code> and an integer <code>k</code>. Return the <strong>maximum</strong> possible energy you can gain.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> energy = [5,2,-10,-5,1], k = 3</span></p>

<p><strong>Output:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> 3</span></p>

<p><strong>Explanation:</strong> We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> energy = [-2,-3,-1], k = 2</span></p>

<p><strong>Output:</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> -1</span></p>

<p><strong>Explanation:</strong> We can gain a total energy of -1 by starting from magician 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= energy.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-1000 &lt;= energy[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= energy.length - 1</code></li>
</ul>

<p>&nbsp;</p>
​​​​​​

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Suffix Sum

We can enumerate the endpoint in the range of $[n - k, n)$, then start from the endpoint and traverse backwards, adding the energy value of the magician at each $k$ interval, and update the answer.

The time complexity is $O(n)$, where $n$ is the length of the array `energy`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximumEnergy(self, energy: List[int], k: int) -> int:
        ans = -inf
        n = len(energy)
        for i in range(n - k, n):
            j, s = i, 0
            while j >= 0:
                s += energy[j]
                ans = max(ans, s)
                j -= k
        return ans
```

```java
class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int ans = -(1 << 30);
        int n = energy.length;
        for (int i = n - k; i < n; ++i) {
            for (int j = i, s = 0; j >= 0; j -= k) {
                s += energy[j];
                ans = Math.max(ans, s);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumEnergy(vector<int>& energy, int k) {
        int ans = -(1 << 30);
        int n = energy.size();
        for (int i = n - k; i < n; ++i) {
            for (int j = i, s = 0; j >= 0; j -= k) {
                s += energy[j];
                ans = max(ans, s);
            }
        }
        return ans;
    }
};
```

```go
func maximumEnergy(energy []int, k int) int {
	ans := -(1 << 30)
	n := len(energy)
	for i := n - k; i < n; i++ {
		for j, s := i, 0; j >= 0; j -= k {
			s += energy[j]
			ans = max(ans, s)
		}
	}
	return ans
}
```

```ts
function maximumEnergy(energy: number[], k: number): number {
    const n = energy.length;
    let ans = -Infinity;
    for (let i = n - k; i < n; ++i) {
        for (let j = i, s = 0; j >= 0; j -= k) {
            s += energy[j];
            ans = Math.max(ans, s);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
