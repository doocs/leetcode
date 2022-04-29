# [1567. 乘积为正数的最长子数组长度](https://leetcode.cn/problems/maximum-length-of-subarray-with-positive-product)

[English Version](/solution/1500-1599/1567.Maximum%20Length%20of%20Subarray%20With%20Positive%20Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>&nbsp;，请你求出乘积为正数的最长子数组的长度。</p>

<p>一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。</p>

<p>请你返回乘积为正数的最长子数组长度。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp; 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,-2,-3,4]
<strong>输出：</strong>4
<strong>解释：</strong>数组本身乘积就是正数，值为 24 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,-2,-3,-4]
<strong>输出：</strong>3
<strong>解释：</strong>最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-2,-3,0,1]
<strong>输出：</strong>2
<strong>解释：</strong>乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>-10^9 &lt;= nums[i]&nbsp;&lt;= 10^9</code></li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        f1 = 1 if nums[0] > 0 else 0
        f2 = 1 if nums[0] < 0 else 0
        res = f1
        for num in nums[1:]:
            pf1, pf2 = f1, f2
            if num > 0:
                f1 += 1
                if f2 > 0:
                    f2 += 1
                else:
                    f2 = 0
            elif num < 0:
                pf1, pf2 = f1, f2
                f2 = pf1 + 1
                if pf2 > 0:
                    f1 = pf2 + 1
                else:
                    f1 = 0
            else:
                f1 = 0
                f2 = 0
            res = max(res, f1)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getMaxLen(int[] nums) {
        int f1 = nums[0] > 0 ? 1 : 0;
        int f2 = nums[0] < 0 ? 1 : 0;
        int res = f1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > 0) {
                ++f1;
                f2 = f2 > 0 ? f2 + 1 : 0;
            } else if (nums[i] < 0) {
                int pf1 = f1, pf2 = f2;
                f2 = pf1 + 1;
                f1 = pf2 > 0 ? pf2 + 1 : 0;
            } else {
                f1 = 0;
                f2 = 0;
            }
            res = Math.max(res, f1);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function getMaxLen(nums: number[]): number {
    // 连续正数计数n1, 连续负数计数n2
    let n1 = nums[0] > 0 ? 1 : 0,
        n2 = nums[0] < 0 ? 1 : 0;
    let ans = n1;
    for (let i = 1; i < nums.length; ++i) {
        let cur = nums[i];
        if (cur == 0) {
            (n1 = 0), (n2 = 0);
        } else if (cur > 0) {
            ++n1;
            n2 = n2 > 0 ? n2 + 1 : 0;
        } else {
            let t1 = n1,
                t2 = n2;
            n1 = t2 > 0 ? t2 + 1 : 0;
            n2 = t1 + 1;
        }
        ans = Math.max(ans, n1);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int getMaxLen(vector<int>& nums) {
        int f1 = nums[0] > 0 ? 1 : 0;
        int f2 = nums[0] < 0 ? 1 : 0;
        int res = f1;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] > 0) {
                ++f1;
                f2 = f2 > 0 ? f2 + 1 : 0;
            } else if (nums[i] < 0) {
                int pf1 = f1, pf2 = f2;
                f2 = pf1 + 1;
                f1 = pf2 > 0 ? pf2 + 1 : 0;
            } else {
                f1 = 0;
                f2 = 0;
            }
            res = max(res, f1);
        }
        return res;
    }
};
```

### **Go**

```go
func getMaxLen(nums []int) int {
	f1, f2 := 0, 0
	if nums[0] > 0 {
		f1 = 1
	}
	if nums[0] < 0 {
		f2 = 1
	}
	res := f1
	for i := 1; i < len(nums); i++ {
		if nums[i] > 0 {
			f1++
			if f2 > 0 {
				f2++
			} else {
				f2 = 0
			}
		} else if nums[i] < 0 {
			pf1, pf2 := f1, f2
			f2 = pf1 + 1
			if pf2 > 0 {
				f1 = pf2 + 1
			} else {
				f1 = 0
			}
		} else {
			f1, f2 = 0, 0
		}
		res = max(res, f1)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
