# [3012. 通过操作使数组长度最小](https://leetcode.cn/problems/minimize-length-of-array-using-operations)

[English Version](/solution/3000-3099/3012.Minimize%20Length%20of%20Array%20Using%20Operations/README_EN.md)

<!-- tags:贪心,数组,数学,数论 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它只包含 <strong>正</strong>&nbsp;整数。</p>

<p>你的任务是通过进行以下操作&nbsp;<strong>任意次</strong>&nbsp;（可以是 0 次）&nbsp;<strong>最小化</strong>&nbsp;<code>nums</code>&nbsp;的长度：</p>

<ul>
	<li>在 <code>nums</code>&nbsp;中选择 <strong>两个不同</strong>&nbsp;的下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;，满足&nbsp;<code>nums[i] &gt; 0</code>&nbsp;且&nbsp;<code>nums[j] &gt; 0</code>&nbsp;。</li>
	<li>将结果&nbsp;<code>nums[i] % nums[j]</code>&nbsp;插入&nbsp;<code>nums</code>&nbsp;的结尾。</li>
	<li>将 <code>nums</code>&nbsp;中下标为&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;的元素删除。</li>
</ul>

<p>请你返回一个整数，它表示进行任意次操作以后<em>&nbsp;</em><code>nums</code>&nbsp;的 <strong>最小长度</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,3,1]
<b>输出：</b>1
<b>解释：</b>使数组长度最小的一种方法是：
操作 1 ：选择下标 2 和 1 ，插入 nums[2] % nums[1] 到数组末尾，得到 [1,4,3,1,3] ，然后删除下标为 2 和 1 的元素。
nums 变为 [1,1,3] 。
操作 2 ：选择下标 1 和 2 ，插入 nums[1] % nums[2] 到数组末尾，得到 [1,1,3,1] ，然后删除下标为 1 和 2 的元素。
nums 变为 [1,1] 。
操作 3 ：选择下标 1 和 0 ，插入 nums[1] % nums[0] 到数组末尾，得到 [1,1,0] ，然后删除下标为 1 和 0 的元素。
nums 变为 [0] 。
nums 的长度无法进一步减小，所以答案为 1 。
1 是可以得到的最小长度。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,5,10,5]
<b>输出：</b>2
<b>解释：</b>使数组长度最小的一种方法是：
操作 1 ：选择下标 0 和 3 ，插入 nums[0] % nums[3] 到数组末尾，得到 [5,5,5,10,5,5] ，然后删除下标为 0 和 3 的元素。
nums 变为 [5,5,5,5] 。
操作 2 ：选择下标 2 和 3 ，插入 nums[2] % nums[3] 到数组末尾，得到 [5,5,5,5,0] ，然后删除下标为 2 和 3 的元素。
nums 变为 [5,5,0] 。
操作 3 ：选择下标 0 和 1 ，插入 nums[0] % nums[1] 到数组末尾，得到 [5,5,0,0] ，然后删除下标为 0 和 1 的元素。
nums 变为 [0,0] 。
nums 的长度无法进一步减小，所以答案为 2 。
2 是可以得到的最小长度。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,4]
<b>输出：</b>1
<b>解释：</b>使数组长度最小的一种方法是：
操作 1 ：选择下标 1 和 2 ，插入 nums[1] % nums[2] 到数组末尾，得到 [2,3,4,3] ，然后删除下标为 1 和 2 的元素。
nums 变为 [2,3] 。
操作 2 ：选择下标 1 和 0 ，插入 nums[1] % nums[0] 到数组末尾，得到 [2,3,1] ，然后删除下标为 1 和 0 的元素。
nums 变为 [1] 。
nums 的长度无法进一步减小，所以答案为 1 。
1 是可以得到的最小长度。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：分情况讨论

我们不妨记数组 $nums$ 的最小的元素为 $mi$。

如果 $mi$ 只出现一次，那么我们将 $mi$ 与数组 $nums$ 的其他元素进行操作，可以将其他元素全部消去，最终剩下 $mi$ 一个元素，答案为 $1$。

如果 $mi$ 出现多次，我们判断数组 $nums$ 中的元素是否都是 $mi$ 的倍数。如果不是，即存在至少一个元素 $x$，使得 $0 \lt x \bmod mi \lt mi$，说明我们可以通过操作，构造出一个小于 $mi$ 的元素，那么这个小于 $mi$ 的元素与其他元素进行操作，可以将其他元素全部消去，最终剩下这个小于 $mi$ 的元素，答案为 $1$；如果都是 $mi$ 的倍数，我们可以先借助 $mi$，将所有大于 $mi$ 的元素消去，最终剩下的元素都是 $mi$，个数为 $cnt$，两两配对，每两个元素进行一次操作，最终剩下 $\lceil cnt / 2 \rceil$ 个元素，答案为 $\lceil cnt / 2 \rceil$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumArrayLength(self, nums: List[int]) -> int:
        mi = min(nums)
        if any(x % mi for x in nums):
            return 1
        return (nums.count(mi) + 1) // 2
```

```java
class Solution {
    public int minimumArrayLength(int[] nums) {
        int mi = Arrays.stream(nums).min().getAsInt();
        int cnt = 0;
        for (int x : nums) {
            if (x % mi != 0) {
                return 1;
            }
            if (x == mi) {
                ++cnt;
            }
        }
        return (cnt + 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    int minimumArrayLength(vector<int>& nums) {
        int mi = *min_element(nums.begin(), nums.end());
        int cnt = 0;
        for (int x : nums) {
            if (x % mi) {
                return 1;
            }
            cnt += x == mi;
        }
        return (cnt + 1) / 2;
    }
};
```

```go
func minimumArrayLength(nums []int) int {
	mi := slices.Min(nums)
	cnt := 0
	for _, x := range nums {
		if x%mi != 0 {
			return 1
		}
		if x == mi {
			cnt++
		}
	}
	return (cnt + 1) / 2
}
```

```ts
function minimumArrayLength(nums: number[]): number {
    const mi = Math.min(...nums);
    let cnt = 0;
    for (const x of nums) {
        if (x % mi) {
            return 1;
        }
        if (x === mi) {
            ++cnt;
        }
    }
    return (cnt + 1) >> 1;
}
```

<!-- tabs:end -->

<!-- end -->
