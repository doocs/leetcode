# [2444. 统计定界子数组的数目](https://leetcode.cn/problems/count-subarrays-with-fixed-bounds)

[English Version](/solution/2400-2499/2444.Count%20Subarrays%20With%20Fixed%20Bounds/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和两个整数 <code>minK</code> 以及 <code>maxK</code> 。</p>

<p><code>nums</code> 的定界子数组是满足下述条件的一个子数组：</p>

<ul>
	<li>子数组中的 <strong>最小值</strong> 等于 <code>minK</code> 。</li>
	<li>子数组中的 <strong>最大值</strong> 等于 <code>maxK</code> 。</li>
</ul>

<p>返回定界子数组的数目。</p>

<p>子数组是数组中的一个连续部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,5,2,7,5], minK = 1, maxK = 5
<strong>输出：</strong>2
<strong>解释：</strong>定界子数组是 [1,3,5] 和 [1,3,5,2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,1], minK = 1, maxK = 1
<strong>输出：</strong>10
<strong>解释：</strong>nums 的每个子数组都是一个定界子数组。共有 10 个子数组。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], minK, maxK &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举右端点**

由题意，我们可以知道，定界子数组的所有元素都在区间 `[minK, maxK]` 中，且最小值一定为 `minK`，最大值一定为 `maxK`。

我们遍历数组 `nums`，统计以 `nums[i]` 为右端点的定界子数组的个数，然后将所有的个数相加即可。

具体实现逻辑如下：

1. 维护最近一个不在区间 `[minK, maxK]` 中的元素的下标 $k$，初始值为 $-1$。那么当前元素 `nums[i]` 的左端点一定大于 $k$。
1. 维护最近一个值为 `minK` 的下标 $j_1$，最近一个值为 `maxK` 的下标 $j_2$，初始值均为 $-1$。那么当前元素 `nums[i]` 的左端点一定小于等于 $\min(j_1, j_2)$。
1. 综上可知，以当前元素为右端点的定界子数组的个数为 $\max(0, \min(j_1, j_2) - k)$。累加所有的个数即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        j1 = j2 = k = -1
        ans = 0
        for i, v in enumerate(nums):
            if v < minK or v > maxK:
                k = i
            if v == minK:
                j1 = i
            if v == maxK:
                j2 = i
            ans += max(0, min(j1, j2) - k)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += Math.max(0, Math.min(j1, j2) - k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        long long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] < minK || nums[i] > maxK) k = i;
            if (nums[i] == minK) j1 = i;
            if (nums[i] == maxK) j2 = i;
            ans += max(0, min(j1, j2) - k);
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubarrays(nums []int, minK int, maxK int) int64 {
	ans := 0
	j1, j2, k := -1, -1, -1
	for i, v := range nums {
		if v < minK || v > maxK {
			k = i
		}
		if v == minK {
			j1 = i
		}
		if v == maxK {
			j2 = i
		}
		ans += max(0, min(j1, j2)-k)
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C**

```c
#define max(a,b) (((a) > (b)) ? (a) : (b))
#define min(a,b) (((a) < (b)) ? (a) : (b))

long long countSubarrays(int *nums, int numsSize, int minK, int maxK) {
    long long res = 0;
    int minIndex = -1;
    int maxIndex = -1;
    int k = -1;
    for (int i = 0; i < numsSize; i++) {
        int num = nums[i];
        if (num == minK) {
            minIndex = i;
        }
        if (num == maxK) {
            maxIndex = i;
        }
        if (num < minK || num > maxK) {
            k = i;
        }
        res += max(min(minIndex, maxIndex) - k, 0);
    }
    return res;
}
```

### **TypeScript**

```ts
function countSubarrays(nums: number[], minK: number, maxK: number): number {
    let res = 0;
    let minIndex = -1;
    let maxIndex = -1;
    let k = -1;
    nums.forEach((num, i) => {
        if (num === minK) {
            minIndex = i;
        }
        if (num === maxK) {
            maxIndex = i;
        }
        if (num < minK || num > maxK) {
            k = i;
        }
        res += Math.max(Math.min(minIndex, maxIndex) - k, 0);
    });
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, min_k: i32, max_k: i32) -> i64 {
        let mut res = 0;
        let mut min_index = -1;
        let mut max_index = -1;
        let mut k = -1;
        for i in 0..nums.len() {
            let num = nums[i];
            let i = i as i64;
            if num == min_k {
                min_index = i;
            }
            if num == max_k {
                max_index = i;
            }
            if num < min_k || num > max_k {
                k = i;
            }
            res += 0.max(min_index.min(max_index) - k);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
