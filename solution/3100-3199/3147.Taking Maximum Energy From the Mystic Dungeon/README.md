---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3147.Taking%20Maximum%20Energy%20From%20the%20Mystic%20Dungeon/README.md
---

# [3147. 从魔法师身上吸取的最大能量](https://leetcode.cn/problems/taking-maximum-energy-from-the-mystic-dungeon)

[English Version](/solution/3100-3199/3147.Taking%20Maximum%20Energy%20From%20the%20Mystic%20Dungeon/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在神秘的地牢中，<code>n</code> 个魔法师站成一排。每个魔法师都拥有一个属性，这个属性可以给你提供能量。有些魔法师可能会给你负能量，即从你身上吸取能量。</p>

<p>你被施加了一种诅咒，当你从魔法师 <code>i</code> 处吸收能量后，你将被立即传送到魔法师 <code>(i + k)</code> 处。这一过程将重复进行，直到你到达一个不存在 <code>(i + k)</code> 的魔法师为止。</p>

<p>换句话说，你将选择一个起点，然后以 <code>k</code> 为间隔跳跃，直到到达魔法师序列的末端，<strong>在过程中吸收所有的能量</strong>。</p>

<p>给定一个数组 <code>energy</code> 和一个整数<code>k</code>，返回你能获得的<strong> 最大 </strong>能量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

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
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> energy = [5,2,-10,-5,1], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> 3</span></p>

<p><strong>解释：</strong>可以从魔法师 1 开始，吸收能量 2 + 1 = 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

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
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> energy = [-2,-3,-1], k = 2</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
"> -1</span></p>

<p><strong>解释：</strong>可以从魔法师 2 开始，吸收能量 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= energy.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-1000 &lt;= energy[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= energy.length - 1</code></li>
</ul>

<p>&nbsp;</p>

## 解法

### 方法一：枚举 + 后缀和

我们可以在 $[n - k, n)$ 的范围内枚举终点，然后从终点开始向前遍历，每次累加间隔为 $k$ 的魔法师的能量值，更新答案。

时间复杂度 $O(n)$，其中 $n$ 是数组 `energy` 的长度。空间复杂度 $O(1)$。

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

<!-- end -->
