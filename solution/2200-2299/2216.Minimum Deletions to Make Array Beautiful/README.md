# [2216. 美化数组的最少删除数](https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful)

[English Version](/solution/2200-2299/2216.Minimum%20Deletions%20to%20Make%20Array%20Beautiful/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

不需要修改数组，只统计不符合规则的元素数量即可。

```txt
COUNT(A){
	n = A.length
	i = 0
	r = 0
	while i < n - 1
		if nums[i] == nums[i + 1]
			r += 1
			i += 1
		else
			i += 2
	return r
```

完成统计后，计算删除元素之后的数组长度是否为奇数，若为奇数，还需要进行一次删除（返回值 + 1）。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        if (n - ans) % 2:
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        if ((n - ans) % 2 == 1) {
            ++ans;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minDeletion(nums: number[]): number {
    const n = nums.length;
    let res = 0;
    let i = 0;
    while (i < n - 1) {
        if (nums[i] === nums[i + 1]) {
            i++;
            res++;
        } else {
            i += 2;
        }
    }
    if ((n - res) % 2 === 1) {
        res++;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_deletion(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = 0;
        let mut i = 0;
        while i < n - 1 {
            if nums[i] == nums[i + 1] {
                res += 1;
                i += 1;
            } else {
                i += 2;
            }
        }
        if (n - res) % 2 == 1 {
            res += 1;
        }
        res as i32
    }
}
```

### **C++**

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
        if ((n - ans) % 2) ++ans;
        return ans;
    }
};
```

### **Go**

```go
func minDeletion(nums []int) int {
	n := len(nums)
	ans := 0
	for i := 0; i < n-1; i++ {
		if nums[i] == nums[i+1] {
			ans++
		} else {
			i++
		}
	}
	if (n-ans)%2 == 1 {
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
