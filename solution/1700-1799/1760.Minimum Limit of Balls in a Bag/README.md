# [1760. 袋子里最少数目的球](https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag)

[English Version](/solution/1700-1799/1760.Minimum%20Limit%20of%20Balls%20in%20a%20Bag/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，其中 <code>nums[i]</code> 表示第 <code>i</code> 个袋子里球的数目。同时给你一个整数 <code>maxOperations</code> 。</p>

<p>你可以进行如下操作至多 <code>maxOperations</code> 次：</p>

<ul>
	<li>选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 <strong>正整数</strong> 个球。
    <ul>
    	<li>比方说，一个袋子里有 <code>5</code> 个球，你可以把它们分到两个新袋子里，分别有 <code>1</code> 个和 <code>4</code> 个球，或者分别有 <code>2</code> 个和 <code>3</code> 个球。</li>
    </ul>
    </li>
</ul>

<p>你的开销是单个袋子里球数目的 <strong>最大值</strong> ，你想要 <strong>最小化</strong> 开销。</p>

<p>请你返回进行上述操作后的最小开销。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [9], maxOperations = 2
<b>输出：</b>3
<b>解释：</b>
- 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[<strong>9</strong>] -> [6,3] 。
- 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[<strong>6</strong>,3] -> [3,3,3] 。
装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,4,8,2], maxOperations = 4
<b>输出：</b>2
<strong>解释：</strong>
- 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,<strong>8</strong>,2] -> [2,4,4,4,2] 。
- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,<strong>4</strong>,4,4,2] -> [2,2,2,4,4,2] 。
- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,<strong>4</strong>,4,2] -> [2,2,2,2,2,4,2] 。
- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,<strong>4</strong>,2] -> [2,2,2,2,2,2,2,2] 。
装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [7,17], maxOperations = 2
<b>输出：</b>7
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= maxOperations, nums[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们可以将题目可以转换为：对某个开销值，看它能不能在 maxOperations 次操作内得到。因此，二分枚举开销值，找到最小的且满足条件的开销值即可。

时间复杂度 $O(n \times \log M)$。其中 $n$ 和 $M$ 分别为数组 `nums` 的长度和最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumSize(self, nums: List[int], maxOperations: int) -> int:
        def check(mx: int) -> bool:
            return sum((x - 1) // mx for x in nums) <= maxOperations

        return bisect_left(range(1, max(nums)), True, key=check) + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = 0;
        for (int x : nums) {
            right = Math.max(right, x);
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            long cnt = 0;
            for (int x : nums) {
                cnt += (x - 1) / mid;
            }
            if (cnt <= maxOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSize(vector<int>& nums, int maxOperations) {
        int left = 1, right = *max_element(nums.begin(), nums.end());
        while (left < right) {
            int mid = (left + right) >> 1;
            long long cnt = 0;
            for (int x : nums) {
                cnt += (x - 1) / mid;
            }
            if (cnt <= maxOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func minimumSize(nums []int, maxOperations int) int {
	r := 0
	for _, x := range nums {
		r = max(r, x)
	}
	return 1 + sort.Search(r, func(mx int) bool {
		mx++
		cnt := 0
		for _, x := range nums {
			cnt += (x - 1) / mx
		}
		return cnt <= maxOperations
	})
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} maxOperations
 * @return {number}
 */
var minimumSize = function (nums, maxOperations) {
    let left = 1;
    let right = Math.max(...nums);
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (const x of nums) {
            cnt += ~~((x - 1) / mid);
        }
        if (cnt <= maxOperations) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
```

### **TypeScript**

```ts
function minimumSize(nums: number[], maxOperations: number): number {
    let left = 1;
    let right = Math.max(...nums);
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (const x of nums) {
            cnt += ~~((x - 1) / mid);
        }
        if (cnt <= maxOperations) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
