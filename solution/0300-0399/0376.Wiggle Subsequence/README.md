# [376. 摆动序列](https://leetcode-cn.com/problems/wiggle-subsequence)

[English Version](/solution/0300-0399/0376.Wiggle%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为<strong>摆动序列。</strong>第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。</p>

<p>例如，&nbsp;<code>[1,7,4,9,2,5]</code> 是一个摆动序列，因为差值 <code>(6,-3,5,-7,3)</code>&nbsp;是正负交替出现的。相反, <code>[1,4,7,2,5]</code>&nbsp;和&nbsp;<code>[1,7,4,5,5]</code> 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。</p>

<p>给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>[1,7,4,9,2,5]
<strong>输出: </strong>6 
<strong>解释: </strong>整个序列均为摆动序列。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>[1,17,5,10,13,15,10,5,16,8]
<strong>输出: </strong>7
<strong>解释: </strong>这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入: </strong>[1,2,3,4,5,6,7,8,9]
<strong>输出: </strong>2</pre>

<p><strong>进阶:</strong><br>
你能否用&nbsp;O(<em>n</em>) 时间复杂度完成此题?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。

设 up 表示以前 i 个元素中的某一个元素结尾的最长上升摆动序列的长度，down 表示以前 i 个元素中的某一个元素结尾的最长下降摆动序列的长度。初始 `up = 1`, `down = 1`。

从数组下标 1 开始遍历：

- 若 `nums[i] > nums[i - 1]`，则需要更新最长上升摆动序列的长度：`up = max(up, down + 1)`
- 若 `nums[i] < nums[i - 1]`，则需要更新最长下降摆动序列的长度：`down = max(down, up + 1)`

最后返回 `max(up, down)` 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def wiggleMaxLength(self, nums: List[int]) -> int:
        up = down = 1
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                up = max(up, down + 1)
            elif nums[i] < nums[i - 1]:
                down = max(down, up + 1)
        return max(up, down)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(up, down + 1);
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(down, up + 1);
            }
        }
        return Math.max(up, down);
    }
}
```

### **TypeScript**

```ts
function wiggleMaxLength(nums: number[]): number {
    let up = 1, down = 1;
    for (let i = 1; i < nums.length; ++i) {
        let prev = nums[i - 1], cur = nums[i];
        if (cur > prev) {
            up = Math.max(up, down + 1);
        } else if (cur < prev) {
            down = Math.max(down, up + 1);
        }
    }
    return Math.max(up, down);
};
```

### **C++**

```cpp
class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        int up = 1, down = 1;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] > nums[i - 1]) {
                up = max(up, down + 1);
            } else if (nums[i] < nums[i - 1]) {
                down = max(down, up + 1);
            }
        }
        return max(up, down);
    }
};
```

### **Go**

```go
func wiggleMaxLength(nums []int) int {
	up, down := 1, 1
	for i := 1; i < len(nums); i++ {
		if nums[i] > nums[i-1] {
			up = max(up, down+1)
		} else if nums[i] < nums[i-1] {
			down = max(down, up+1)
		}
	}
	return max(up, down)
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
