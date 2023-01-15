# [2293. 极大极小游戏](https://leetcode.cn/problems/min-max-game)

[English Version](/solution/2200-2299/2293.Min%20Max%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，其长度是 <code>2</code> 的幂。</p>

<p>对 <code>nums</code> 执行下述算法：</p>

<ol>
	<li>设 <code>n</code> 等于 <code>nums</code> 的长度，如果 <code>n == 1</code> ，<strong>终止</strong> 算法过程。否则，<strong>创建</strong> 一个新的整数数组&nbsp;<code>newNums</code> ，新数组长度为 <code>n / 2</code> ，下标从 <strong>0</strong> 开始。</li>
	<li>对于满足&nbsp;<code>0 &lt;= i &lt; n / 2</code> 的每个 <strong>偶数</strong> 下标 <code>i</code> ，将 <code>newNums[i]</code> <strong>赋值</strong> 为 <code>min(nums[2 * i], nums[2 * i + 1])</code> 。</li>
	<li>对于满足&nbsp;<code>0 &lt;= i &lt; n / 2</code> 的每个 <strong>奇数</strong> 下标 <code>i</code> ，将 <code>newNums[i]</code> <strong>赋值</strong> 为 <code>max(nums[2 * i], nums[2 * i + 1])</code> 。</li>
	<li>用 <code>newNums</code> 替换 <code>nums</code> 。</li>
	<li>从步骤 1 开始 <strong>重复</strong> 整个过程。</li>
</ol>

<p>执行算法后，返回 <code>nums</code> 中剩下的那个数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2293.Min%20Max%20Game/images/example1drawio-1.png" style="width: 500px; height: 240px;" /></p>

<pre>
<strong>输入：</strong>nums = [1,3,5,2,4,8,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>重复执行算法会得到下述数组。
第一轮：nums = [1,5,4,2]
第二轮：nums = [1,4]
第三轮：nums = [1]
1 是最后剩下的那个数字，返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3]
<strong>输出：</strong>3
<strong>解释：</strong>3 就是最后剩下的数字，返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1024</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums.length</code> 是 <code>2</code> 的幂</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意，我们可以模拟整个过程，最后剩下的数字即为答案。在实现上，我们不需要额外创建数组，直接在原数组上进行操作即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMaxGame(self, nums: List[int]) -> int:
        n = len(nums)
        while n > 1:
            n >>= 1
            for i in range(n):
                a, b = nums[i << 1], nums[i << 1 | 1]
                nums[i] = min(a, b) if i % 2 == 0 else max(a, b)
        return nums[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMaxGame(int[] nums) {
        for (int n = nums.length; n > 1;) {
            n >>= 1;
            for (int i = 0; i < n; ++i) {
                int a = nums[i << 1], b = nums[i << 1 | 1];
                nums[i] = i % 2 == 0 ? Math.min(a, b) : Math.max(a, b);
            }
        }
        return nums[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMaxGame(vector<int>& nums) {
        for (int n = nums.size(); n > 1;) {
            n >>= 1;
            for (int i = 0; i < n; ++i) {
                int a = nums[i << 1], b = nums[i << 1 | 1];
                nums[i] = i % 2 == 0 ? min(a, b) : max(a, b);
            }
        }
        return nums[0];
    }
};
```

### **Go**

```go
func minMaxGame(nums []int) int {
	for n := len(nums); n > 1; {
		n >>= 1
		for i := 0; i < n; i++ {
			a, b := nums[i<<1], nums[i<<1|1]
			if i%2 == 0 {
				nums[i] = min(a, b)
			} else {
				nums[i] = max(a, b)
			}
		}
	}
	return nums[0]
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

### **TypeScript**

```ts
function minMaxGame(nums: number[]): number {
    for (let n = nums.length; n > 1; ) {
        n >>= 1;
        for (let i = 0; i < n; ++i) {
            const a = nums[i << 1];
            const b = nums[(i << 1) | 1];
            nums[i] = i % 2 == 0 ? Math.min(a, b) : Math.max(a, b);
        }
    }
    return nums[0];
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_max_game(mut nums: Vec<i32>) -> i32 {
        let mut n = nums.len();
        while n != 1 {
            n >>= 1;
            for i in 0..n {
                nums[i] = (if i & 1 == 1 {
                    i32::max
                } else {
                    i32::min
                })(nums[i << 1], nums[i << 1 | 1])
            }
        }
        nums[0]
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))
#define max(a, b) (((a) > (b)) ? (a) : (b))

int minMaxGame(int *nums, int numsSize) {
    while (numsSize != 1) {
        numsSize >>= 1;
        for (int i = 0; i < numsSize; i++) {
            int a = nums[i << 1];
            int b = nums[i << 1 | 1];
            nums[i] = i & 1 ? max(a, b) : min(a, b);
        }
    }
    return nums[0];
}
```

### **...**

```

```

<!-- tabs:end -->
