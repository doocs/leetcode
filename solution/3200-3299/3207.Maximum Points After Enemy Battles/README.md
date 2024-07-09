---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3207.Maximum%20Points%20After%20Enemy%20Battles/README.md
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [3207. 与敌人战斗后的最大分数](https://leetcode.cn/problems/maximum-points-after-enemy-battles)

[English Version](/solution/3200-3299/3207.Maximum%20Points%20After%20Enemy%20Battles/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>enemyEnergies</code>&nbsp;，它表示一个下标从 <strong>0</strong>&nbsp;开始的敌人能量数组。</p>

<p>同时给你一个整数&nbsp;<code>currentEnergy</code>&nbsp;，它表示你一开始拥有的能量值总量。</p>

<p>你一开始的分数为&nbsp;<code>0</code>&nbsp;，且一开始所有的敌人都未标记。</p>

<p>你可以通过以下操作 <b>之一</b>&nbsp;<strong>任意次</strong>（也可以&nbsp;<strong>0</strong>&nbsp;次）来得分：</p>

<ul>
	<li>选择一个 <strong>未标记</strong>&nbsp;且满足&nbsp;<code>currentEnergy &gt;= enemyEnergies[i]</code>&nbsp;的敌人&nbsp;<code>i</code>&nbsp;。在这个操作中：

    <ul>
    	<li>你会获得 <code>1</code>&nbsp;分。</li>
    	<li>你的能量值减少&nbsp;<code>enemyEnergies[i]</code>&nbsp;，也就是说&nbsp;<code>currentEnergy = currentEnergy - enemyEnergies[i]</code>&nbsp;。</li>
    </ul>
    </li>
    <li>如果你目前&nbsp;<strong>至少</strong>&nbsp;有 <code>1</code>&nbsp;分，你可以选择一个&nbsp;<strong>未标记</strong>&nbsp;的敌人&nbsp;<code>i</code>&nbsp;。在这个操作中：
    <ul>
    	<li>你的能量值增加 <code>enemyEnergies[i]</code>&nbsp;，也就是说&nbsp;<code>currentEnergy = currentEnergy + enemyEnergies[i]</code>&nbsp;。</li>
    	<li>敌人&nbsp;<code>i</code> <strong>被标记</strong>&nbsp;。</li>
    </ul>
    </li>

</ul>

<p>请你返回通过以上操作，<strong>最多</strong>&nbsp;可以获得多少分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><b>输入：</b>enemyEnergies = [3,2,2], currentEnergy = 2</p>

<p><b>输出：</b>3</p>

<p><strong>解释：</strong></p>

<p>通过以下操作可以得到最大得分 3 分：</p>

<ul>
	<li>对敌人 1 使用第一种操作：<code>points</code>&nbsp;增加 1 ，<code>currentEnergy</code>&nbsp;减少 2 。所以&nbsp;<code>points = 1</code>&nbsp;且&nbsp;<code>currentEnergy = 0</code>&nbsp;。</li>
	<li>对敌人 0 使用第二种操作：<code>currentEnergy</code>&nbsp;增加 3 ，敌人 0 被标记。所以&nbsp;<code>points = 1</code>&nbsp;，<code>currentEnergy = 3</code>&nbsp;，被标记的敌人包括&nbsp;<code>[0]</code>&nbsp;。</li>
	<li>对敌人 2 使用第一种操作：<code>points</code>&nbsp;增加 1 ，<code>currentEnergy</code>&nbsp;减少 2 。所以&nbsp;<code>points = 2</code>&nbsp;且&nbsp;<code>currentEnergy = 1</code>&nbsp;，被标记的敌人包括<code>[0]</code>&nbsp;。</li>
	<li>对敌人 2 使用第二种操作：<code>currentEnergy</code>&nbsp;增加 2 ，敌人 2 被标记。所以&nbsp;<code>points = 2</code>&nbsp;，<code>currentEnergy = 3</code>&nbsp;且被标记的敌人包括&nbsp;<code>[0, 2]</code>&nbsp;。</li>
	<li>对敌人 1 使用第一种操作：<code>points</code>&nbsp;增加 1 ，<code>currentEnergy</code>&nbsp;减少 2 。所以&nbsp;<code>points = 3</code>&nbsp;，<code>currentEnergy = 1</code>&nbsp;，被标记的敌人包括&nbsp;<code>[0, 2]</code>&nbsp;。</li>
</ul>

<p><strong>示例 2：</strong></p>

<p><b>输入：</b>enemyEnergies =&nbsp;[2], currentEnergy = 10</p>

<p><b>输出：</b>5</p>

<p><strong>解释：</strong></p>

<p>通过对敌人 0 进行第一种操作 5 次，得到最大得分。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= enemyEnergies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= enemyEnergies[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= currentEnergy &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

根据题目描述，我们每次需要通过具有最小能量值的敌人来获得分数，通过具有最大能量值的敌人来增加能量值并进行标记。

因此，我们可以对敌人的能量值进行排序，然后从能量值最大的敌人开始，每次都选择能量值最小的敌人来获得分数，并消耗能量值。然后，我们将能量值最大的敌人的能量值加到当前能量值上，并标记该敌人。重复上述操作，直到所有敌人都被标记。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是敌人的数量。

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
