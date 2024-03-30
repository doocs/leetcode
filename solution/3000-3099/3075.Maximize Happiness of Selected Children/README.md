# [3075. 幸福值最大化的选择方案](https://leetcode.cn/problems/maximize-happiness-of-selected-children)

[English Version](/solution/3000-3099/3075.Maximize%20Happiness%20of%20Selected%20Children/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的数组 <code>happiness</code> ，以及一个<strong> 正整数 </strong><code>k</code> 。</p>

<p><code>n</code> 个孩子站成一队，其中第 <code>i</code> 个孩子的 <strong>幸福值</strong> 是<strong> </strong><code>happiness[i]</code> 。你计划组织 <code>k</code> 轮筛选从这 <code>n</code> 个孩子中选出 <code>k</code> 个孩子。</p>

<p>在每一轮选择一个孩子时，所有<strong> 尚未 </strong>被选中的孩子的 <strong>幸福值 </strong>将减少 <code>1</code> 。注意，幸福值<strong> 不能 </strong>变成负数，且只有在它是正数的情况下才会减少。</p>

<p>选择 <code>k</code> 个孩子，并使你选中的孩子幸福值之和最大，返回你能够得到的<strong> </strong><strong>最大值</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>happiness = [1,2,3], k = 2
<strong>输出：</strong>4
<strong>解释：</strong>按以下方式选择 2 个孩子：
- 选择幸福值为 3 的孩子。剩余孩子的幸福值变为 [0,1] 。
- 选择幸福值为 1 的孩子。剩余孩子的幸福值变为 [0] 。注意幸福值不能小于 0 。
所选孩子的幸福值之和为 3 + 1 = 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>happiness = [1,1,1,1], k = 2
<strong>输出：</strong>1
<strong>解释：</strong>按以下方式选择 2 个孩子：
- 选择幸福值为 1 的任意一个孩子。剩余孩子的幸福值变为 [0,0,0] 。
- 选择幸福值为 0 的孩子。剩余孩子的幸福值变为 [0,0] 。
所选孩子的幸福值之和为 1 + 0 = 1 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>happiness = [2,3,4,5], k = 1
<strong>输出：</strong>5
<strong>解释：</strong>按以下方式选择 1 个孩子：
- 选择幸福值为 5 的孩子。剩余孩子的幸福值变为 [1,2,3] 。
所选孩子的幸福值之和为 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == happiness.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= happiness[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

### 方法一：贪心 + 排序

为了使得幸福值之和尽可能大，我们应该优先选择幸福值大的孩子。因此，我们可以对孩子按照幸福值从大到小排序，然后依次选择 $k$ 个孩子。对于当前第 $i$ 个孩子，能够得到的幸福值为 $\max(happiness[i] - i, 0)$，最后返回这 $k$ 个孩子的幸福值之和。

时间复杂度 $O(n \times \log n + k)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 `happiness` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumHappinessSum(self, happiness: List[int], k: int) -> int:
        happiness.sort(reverse=True)
        ans = 0
        for i, x in enumerate(happiness[:k]):
            x -= i
            ans += max(x, 0)
        return ans
```

```java
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long ans = 0;
        for (int i = 0, n = happiness.length; i < k; ++i) {
            int x = happiness[n - i - 1] - i;
            ans += Math.max(x, 0);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maximumHappinessSum(vector<int>& happiness, int k) {
        sort(happiness.rbegin(), happiness.rend());
        long long ans = 0;
        for (int i = 0, n = happiness.size(); i < k; ++i) {
            int x = happiness[i] - i;
            ans += max(x, 0);
        }
        return ans;
    }
};
```

```go
func maximumHappinessSum(happiness []int, k int) (ans int64) {
	sort.Ints(happiness)
	for i := 0; i < k; i++ {
		x := happiness[len(happiness)-i-1] - i
		ans += int64(max(x, 0))
	}
	return
}
```

```ts
function maximumHappinessSum(happiness: number[], k: number): number {
    happiness.sort((a, b) => b - a);
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        const x = happiness[i] - i;
        ans += Math.max(x, 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
