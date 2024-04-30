# [2216. 美化数组的最少删除数](https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful)

[English Version](/solution/2200-2299/2216.Minimum%20Deletions%20to%20Make%20Array%20Beautiful/README_EN.md)

<!-- tags:栈,贪心,数组 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，如果满足下述条件，则认为数组 <code>nums</code> 是一个 <strong>美丽数组</strong> ：</p>

<ul>
	<li><code>nums.length</code> 为偶数</li>
	<li>对所有满足 <code>i % 2 == 0</code> 的下标 <code>i</code> ，<code>nums[i] != nums[i + 1]</code> 均成立</li>
</ul>

<p>注意，空数组同样认为是美丽数组。</p>

<p>你可以从 <code>nums</code> 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 <strong>不变</strong> 。</p>

<p>返回使 <code>nums</code> 变为美丽数组所需删除的 <strong>最少</strong> 元素数目<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,2,3,5]
<strong>输出：</strong>1
<strong>解释：</strong>可以删除 <code>nums[0]</code> 或 <code>nums[1]</code> ，这样得到的 <code>nums</code> = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,2,2,3,3]
<strong>输出：</strong>2
<strong>解释：</strong>可以删除 <code>nums[0]</code> 和 <code>nums[5]</code> ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：贪心

根据题目描述，我们知道，一个美丽数组有偶数个元素，且如果我们把这个数组中每相邻两个元素划分为一组，那么每一组中的两个元素都不相等。这意味着，组内的元素不能重复，但组与组之间的元素可以重复。

因此，我们考虑从左到右遍历数组，只要遇到相邻两个元素相等，我们就将其中的一个元素删除，即删除数加一；否则，我们可以保留这两个元素。

最后，我们判断删除后的数组长度是否为偶数，如果不是，则说明我们需要再删除一个元素，使得最终的数组长度为偶数。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。我们只需要遍历数组一次。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minDeletion(self, nums: List[int]) -> int:
        n = len(nums)
        i = ans = 0
        while i < n - 1:
            if nums[i] == nums[i + 1]:
                ans += 1
                i += 1
            else:
                i += 2
        ans += (n - ans) % 2
        return ans
```

```java
class Solution {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                ++ans;
            } else {
                ++i;
            }
        }
        ans += (n - ans) % 2;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minDeletion(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                ++ans;
            } else {
                ++i;
            }
        }
        ans += (n - ans) % 2;
        return ans;
    }
};
```

```go
func minDeletion(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n-1; i++ {
		if nums[i] == nums[i+1] {
			ans++
		} else {
			i++
		}
	}
	ans += (n - ans) % 2
	return
}
```

```ts
function minDeletion(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 1; ++i) {
        if (nums[i] === nums[i + 1]) {
            ++ans;
        } else {
            ++i;
        }
    }
    ans += (n - ans) % 2;
    return ans;
}
```

```rust
impl Solution {
    pub fn min_deletion(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n - 1 {
            if nums[i] == nums[i + 1] {
                ans += 1;
                i += 1;
            } else {
                i += 2;
            }
        }
        ans += (n - ans) % 2;
        ans as i32
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def minDeletion(self, nums: List[int]) -> int:
        n = len(nums)
        ans = i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j] == nums[i]:
                j += 1
                ans += 1
            i = j + 1
        ans += (n - ans) % 2
        return ans
```

```java
class Solution {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) {
                ++j;
                ++ans;
            }
            i = j + 1;
        }
        ans += (n - ans) % 2;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minDeletion(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) {
                ++j;
                ++ans;
            }
            i = j + 1;
        }
        ans += (n - ans) % 2;
        return ans;
    }
};
```

```go
func minDeletion(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n; {
		j := i + 1
		for ; j < n && nums[j] == nums[i]; j++ {
			ans++
		}
		i = j + 1
	}
	ans += (n - ans) % 2
	return
}
```

```ts
function minDeletion(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        for (; j < n && nums[j] === nums[i]; ++j) {
            ++ans;
        }
        i = j + 1;
    }
    ans += (n - ans) % 2;
    return ans;
}
```

```rust
impl Solution {
    pub fn min_deletion(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            while j < n && nums[j] == nums[i] {
                ans += 1;
                j += 1;
            }
            i = j + 1;
        }
        ans += (n - ans) % 2;
        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
