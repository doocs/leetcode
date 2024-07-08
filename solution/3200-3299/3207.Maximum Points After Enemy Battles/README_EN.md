---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3207.Maximum%20Points%20After%20Enemy%20Battles/README_EN.md
---

<!-- problem:start -->

# [3207. Maximum Points After Enemy Battles](https://leetcode.com/problems/maximum-points-after-enemy-battles)

[中文文档](/solution/3200-3299/3207.Maximum%20Points%20After%20Enemy%20Battles/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>enemyEnergies</code> denoting the energy values of various enemies.</p>

<p>You are also given an integer <code>currentEnergy</code> denoting the amount of energy you have initially.</p>

<p>You start with 0 points, and all the enemies are unmarked initially.</p>

<p>You can perform <strong>either</strong> of the following operations <strong>zero </strong>or multiple times to gain points:</p>

<ul>
	<li>Choose an <strong>unmarked</strong> enemy, <code>i</code>, such that <code>currentEnergy &gt;= enemyEnergies[i]</code>. By choosing this option:

    <ul>
    	<li>You gain 1 point.</li>
    	<li>Your energy is reduced by the enemy&#39;s energy, i.e. <code>currentEnergy = currentEnergy - enemyEnergies[i]</code>.</li>
    </ul>
    </li>
    <li>If you have <strong>at least</strong> 1 point, you can choose an <strong>unmarked</strong> enemy, <code>i</code>. By choosing this option:
    <ul>
    	<li>Your energy increases by the enemy&#39;s energy, i.e. <code>currentEnergy = currentEnergy + enemyEnergies[i]</code>.</li>
    	<li>The <font face="monospace">e</font>nemy <code>i</code> is <strong>marked</strong>.</li>
    </ul>
    </li>

</ul>

<p>Return an integer denoting the <strong>maximum</strong> points you can get in the end by optimally performing operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">enemyEnergies = [3,2,2], currentEnergy = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The following operations can be performed to get 3 points, which is the maximum:</p>

<ul>
	<li>First operation on enemy 1: <code>points</code> increases by 1, and <code>currentEnergy</code> decreases by 2. So, <code>points = 1</code>, and <code>currentEnergy = 0</code>.</li>
	<li>Second operation on enemy 0: <code>currentEnergy</code> increases by 3, and enemy 0 is marked. So, <code>points = 1</code>, <code>currentEnergy = 3</code>, and marked enemies = <code>[0]</code>.</li>
	<li>First operation on enemy 2: <code>points</code> increases by 1, and <code>currentEnergy</code> decreases by 2. So, <code>points = 2</code>, <code>currentEnergy = 1</code>, and marked enemies = <code>[0]</code>.</li>
	<li>Second operation on enemy 2: <code>currentEnergy</code> increases by 2, and enemy 2 is marked. So, <code>points = 2</code>, <code>currentEnergy = 3</code>, and marked enemies = <code>[0, 2]</code>.</li>
	<li>First operation on enemy 1: <code>points</code> increases by 1, and <code>currentEnergy</code> decreases by 2. So, <code>points = 3</code>, <code>currentEnergy = 1</code>, and marked enemies = <code>[0, 2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">enemyEnergies = </span>[2]<span class="example-io">, currentEnergy = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation: </strong></p>

<p>Performing the first operation 5 times on enemy 0 results in the maximum number of points.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= enemyEnergies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= enemyEnergies[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= currentEnergy &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

According to the problem description, we need to score by defeating enemies with the lowest energy value and increase our energy value by defeating enemies with the highest energy value and marking them.

Therefore, we can sort the enemies by their energy values, then start from the enemy with the highest energy value, always choose the enemy with the lowest energy value to score and consume energy. Next, we add the energy value of the enemy with the highest energy to our current energy and mark that enemy. Repeat the above steps until all enemies are marked.

The time complexity is $O(n \log n)$, and the space complexity is $O(\log n)$, where $n$ is the number of enemies.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumPoints(self, enemyEnergies: List[int], currentEnergy: int) -> int:
        enemyEnergies.sort()
        if currentEnergy < enemyEnergies[0]:
            return 0
        ans = 0
        for i in range(len(enemyEnergies) - 1, -1, -1):
            ans += currentEnergy // enemyEnergies[0]
            currentEnergy %= enemyEnergies[0]
            currentEnergy += enemyEnergies[i]
        return ans
```

#### Java

```java
class Solution {
    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        Arrays.sort(enemyEnergies);
        if (currentEnergy < enemyEnergies[0]) {
            return 0;
        }
        long ans = 0;
        for (int i = enemyEnergies.length - 1; i >= 0; --i) {
            ans += currentEnergy / enemyEnergies[0];
            currentEnergy %= enemyEnergies[0];
            currentEnergy += enemyEnergies[i];
        }
        return ans;
    }
};
```

#### C++

```cpp
class Solution {
public:
    long long maximumPoints(vector<int>& enemyEnergies, int currentEnergy) {
        sort(enemyEnergies.begin(), enemyEnergies.end());
        if (currentEnergy < enemyEnergies[0]) {
            return 0;
        }
        long long ans = 0;
        for (int i = enemyEnergies.size() - 1; i >= 0; --i) {
            ans += currentEnergy / enemyEnergies[0];
            currentEnergy %= enemyEnergies[0];
            currentEnergy += enemyEnergies[i];
        }
        return ans;
    }
};
```

#### Go

```go
func maximumPoints(enemyEnergies []int, currentEnergy int) (ans int64) {
	sort.Ints(enemyEnergies)
	if currentEnergy < enemyEnergies[0] {
		return 0
	}
	for i := len(enemyEnergies) - 1; i >= 0; i-- {
		ans += int64(currentEnergy / enemyEnergies[0])
		currentEnergy %= enemyEnergies[0]
		currentEnergy += enemyEnergies[i]
	}
	return
}
```

#### TypeScript

```ts
function maximumPoints(enemyEnergies: number[], currentEnergy: number): number {
    enemyEnergies.sort((a, b) => a - b);
    if (currentEnergy < enemyEnergies[0]) {
        return 0;
    }
    let ans = 0;
    for (let i = enemyEnergies.length - 1; ~i; --i) {
        ans += Math.floor(currentEnergy / enemyEnergies[0]);
        currentEnergy %= enemyEnergies[0];
        currentEnergy += enemyEnergies[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
