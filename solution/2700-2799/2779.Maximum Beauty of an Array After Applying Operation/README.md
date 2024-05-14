# [2779. 数组的最大美丽值](https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation)

[English Version](/solution/2700-2799/2779.Maximum%20Beauty%20of%20an%20Array%20After%20Applying%20Operation/README_EN.md)

<!-- tags:数组,二分查找,排序,滑动窗口 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和一个 <strong>非负</strong> 整数 <code>k</code> 。</p>

<p>在一步操作中，你可以执行下述指令：</p>

<ul>
	<li>在范围&nbsp;<code>[0, nums.length - 1]</code> 中选择一个 <strong>此前没有选过</strong> 的下标 <code>i</code> 。</li>
	<li>将 <code>nums[i]</code> 替换为范围 <code>[nums[i] - k, nums[i] + k]</code> 内的任一整数。</li>
</ul>

<p>数组的 <strong>美丽值</strong> 定义为数组中由相等元素组成的最长子序列的长度。</p>

<p>对数组 <code>nums</code> 执行上述操作任意次后，返回数组可能取得的 <strong>最大</strong> 美丽值。</p>

<p><strong>注意：</strong>你 <strong>只</strong> 能对每个下标执行 <strong>一次</strong> 此操作。</p>

<p>数组的 <strong>子序列</strong> 定义是：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,6,1,2], k = 2
<strong>输出：</strong>3
<strong>解释：</strong>在这个示例中，我们执行下述操作：
- 选择下标 1 ，将其替换为 4（从范围 [4,8] 中选出），此时 nums = [4,4,1,2] 。
- 选择下标 3 ，将其替换为 4（从范围 [0,4] 中选出），此时 nums = [4,4,1,4] 。
执行上述操作后，数组的美丽值是 3（子序列由下标 0 、1 、3 对应的元素组成）。
可以证明 3 是我们可以得到的由相等元素组成的最长子序列长度。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1], k = 10
<strong>输出：</strong>4
<strong>解释：</strong>在这个示例中，我们无需执行任何操作。
数组 nums 的美丽值是 4（整个数组）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：差分数组

我们注意到，对于每一次操作，区间 $[nums[i]-k, nums[i]+k]$ 内的所有元素都会增加 $1$，因此我们可以使用差分数组来记录这些操作对美丽值的贡献。

题目中 $nums[i]-k$ 可能为负数，我们统一将所有元素加上 $k$，保证结果为非负数。因此，我们需要创建一个长度为 $\max(nums) + k \times 2 + 2$ 的差分数组 $d$。

接下来，遍历数组 $nums$，对于当前遍历到的元素 $x$，我们将 $d[x]$ 增加 $1$，将 $d[x+k\times2+1]$ 减少 $1$。这样，我们就可以通过 $d$ 数组计算出每个位置的前缀和，即为每个位置的美丽值。找到最大的美丽值即可。

时间复杂度 $O(n)$，空间复杂度 $O(M + 2 \times k)$。其中 $n$ 是数组 $nums$ 的长度，而 $M$ 是数组 $nums$ 中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def maximumBeauty(self, nums: List[int], k: int) -> int:
        m = max(nums) + k * 2 + 2
        d = [0] * m
        for x in nums:
            d[x] += 1
            d[x + k * 2 + 1] -= 1
        ans = s = 0
        for x in d:
            s += x
            ans = max(ans, s)
        return ans
```

```java
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int m = Arrays.stream(nums).max().getAsInt() + k * 2 + 2;
        int[] d = new int[m];
        for (int x : nums) {
            d[x]++;
            d[x + k * 2 + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumBeauty(vector<int>& nums, int k) {
        int m = *max_element(nums.begin(), nums.end()) + k * 2 + 2;
        vector<int> d(m);
        for (int x : nums) {
            d[x]++;
            d[x + k * 2 + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans = max(ans, s);
        }
        return ans;
    }
};
```

```go
func maximumBeauty(nums []int, k int) (ans int) {
	m := slices.Max(nums)
	m += k*2 + 2
	d := make([]int, m)
	for _, x := range nums {
		d[x]++
		d[x+k*2+1]--
	}
	s := 0
	for _, x := range d {
		s += x
		if ans < s {
			ans = s
		}
	}
	return
}
```

```ts
function maximumBeauty(nums: number[], k: number): number {
    const m = Math.max(...nums) + k * 2 + 2;
    const d: number[] = new Array(m).fill(0);
    for (const x of nums) {
        d[x]++;
        d[x + k * 2 + 1]--;
    }
    let ans = 0;
    let s = 0;
    for (const x of d) {
        s += x;
        ans = Math.max(ans, s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
