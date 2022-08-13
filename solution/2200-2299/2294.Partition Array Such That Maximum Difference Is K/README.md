# [2294. 划分数组使最大差为 K](https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k)

[English Version](/solution/2200-2299/2294.Partition%20Array%20Such%20That%20Maximum%20Difference%20Is%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。你可以将 <code>nums</code> 划分成一个或多个 <strong>子序列</strong> ，使 <code>nums</code> 中的每个元素都 <strong>恰好</strong> 出现在一个子序列中。</p>

<p>在满足每个子序列中最大值和最小值之间的差值最多为 <code>k</code> 的前提下，返回需要划分的 <strong>最少</strong> 子序列数目。</p>

<p><strong>子序列</strong> 本质是一个序列，可以通过删除另一个序列中的某些元素（或者不删除）但不改变剩下元素的顺序得到。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,6,1,2,5], k = 2
<strong>输出：</strong>2
<strong>解释：</strong>
可以将 nums 划分为两个子序列 [3,1,2] 和 [6,5] 。
第一个子序列中最大值和最小值的差值是 3 - 1 = 2 。
第二个子序列中最大值和最小值的差值是 6 - 5 = 1 。
由于创建了两个子序列，返回 2 。可以证明需要划分的最少子序列数目就是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], k = 1
<strong>输出：</strong>2
<strong>解释：</strong>
可以将 nums 划分为两个子序列 [1,2] 和 [3] 。
第一个子序列中最大值和最小值的差值是 2 - 1 = 1 。
第二个子序列中最大值和最小值的差值是 3 - 3 = 0 。
由于创建了两个子序列，返回 2 。注意，另一种最优解法是将 nums 划分成子序列 [1] 和 [2,3] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,4,5], k = 0
<strong>输出：</strong>3
<strong>解释：</strong>
可以将 nums 划分为三个子序列 [2,2]、[4] 和 [5] 。
第一个子序列中最大值和最小值的差值是 2 - 2 = 0 。
第二个子序列中最大值和最小值的差值是 4 - 4 = 0 。
第三个子序列中最大值和最小值的差值是 5 - 5 = 0 。
由于创建了三个子序列，返回 3 。可以证明需要划分的最少子序列数目就是 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partitionArray(self, nums: List[int], k: int) -> int:
        nums.sort()
        d, ans = 0, 1
        for a, b in pairwise(nums):
            if (t := b - a) + d <= k:
                d += t
            else:
                d, ans = 0, ans + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int d = 0, ans = 1;
        for (int i = 1; i < nums.length; ++i) {
            int a = nums[i - 1], b = nums[i];
            int t = b - a;
            if (d + t <= k) {
                d += t;
            } else {
                d = 0;
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int partitionArray(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int d = 0, ans = 1;
        for (int i = 1; i < nums.size(); ++i) {
            int a = nums[i - 1], b = nums[i];
            int t = b - a;
            if (d + t <= k)
                d += t;
            else {
                d = 0;
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func partitionArray(nums []int, k int) int {
	sort.Ints(nums)
	d, ans := 0, 1
	for i, v := range nums[1:] {
		t := v - nums[i]
		if d+t <= k {
			d += t
		} else {
			d = 0
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function partitionArray(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let prev = nums[0] + k;
    for (let num of nums) {
        if (num <= prev) continue;
        prev = num + k;
        ans++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
