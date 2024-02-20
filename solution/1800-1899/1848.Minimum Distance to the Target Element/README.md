# [1848. 到目标元素的最小距离](https://leetcode.cn/problems/minimum-distance-to-the-target-element)

[English Version](/solution/1800-1899/1848.Minimum%20Distance%20to%20the%20Target%20Element/README_EN.md)

<!-- tags:数组 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> （下标 <strong>从 0 开始</strong> 计数）以及两个整数 <code>target</code> 和 <code>start</code> ，请你找出一个下标 <code>i</code> ，满足 <code>nums[i] == target</code> 且 <code>abs(i - start)</code> <strong>最小化</strong> 。注意：<code>abs(x)</code> 表示 <code>x</code> 的绝对值。</p>

<p>返回 <code>abs(i - start)</code> 。</p>

<p>题目数据保证 <code>target</code> 存在于 <code>nums</code> 中。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5], target = 5, start = 3
<strong>输出：</strong>1
<strong>解释：</strong>nums[4] = 5 是唯一一个等于 target 的值，所以答案是 abs(4 - 3) = 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1], target = 1, start = 0
<strong>输出：</strong>0
<strong>解释：</strong>nums[0] = 1 是唯一一个等于 target 的值，所以答案是 abs(0 - 0) = 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
<strong>输出：</strong>0
<strong>解释：</strong>nums 中的每个值都是 1 ，但 nums[0] 使 abs(i - start) 的结果得以最小化，所以答案是 abs(0 - 0) = 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
	<li><code>0 <= start < nums.length</code></li>
	<li><code>target</code> 存在于 <code>nums</code> 中</li>
</ul>

## 解法

### 方法一：一次遍历

遍历数组，找到所有等于 $target$ 的下标，然后计算 $|i - start|$，取最小值即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def getMinDistance(self, nums: List[int], target: int, start: int) -> int:
        return min(abs(i - start) for i, x in enumerate(nums) if x == target)
```

```java
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == target) {
                ans = Math.min(ans, Math.abs(i - start));
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int getMinDistance(vector<int>& nums, int target, int start) {
        int n = nums.size();
        int ans = n;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == target) {
                ans = min(ans, abs(i - start));
            }
        }
        return ans;
    }
};
```

```go
func getMinDistance(nums []int, target int, start int) int {
	ans := 1 << 30
	for i, x := range nums {
		if t := abs(i - start); x == target && t < ans {
			ans = t
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function getMinDistance(nums: number[], target: number, start: number): number {
    let ans = Infinity;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] === target) {
            ans = Math.min(ans, Math.abs(i - start));
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn get_min_distance(nums: Vec<i32>, target: i32, start: i32) -> i32 {
        nums.iter()
            .enumerate()
            .filter(|&(_, &x)| x == target)
            .map(|(i, _)| ((i as i32) - start).abs())
            .min()
            .unwrap_or_default()
    }
}
```

<!-- tabs:end -->

<!-- end -->
