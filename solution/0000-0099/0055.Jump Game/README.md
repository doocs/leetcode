# [55. 跳跃游戏](https://leetcode.cn/problems/jump-game)

[English Version](/solution/0000-0099/0055.Jump%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数数组 <code>nums</code> ，你最初位于数组的 <strong>第一个下标</strong> 。</p>

<p>数组中的每个元素代表你在该位置可以跳跃的最大长度。</p>

<p>判断你是否能够到达最后一个下标。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,1,1,4]
<strong>输出：</strong>true
<strong>解释：</strong>可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,0,4]
<strong>输出：</strong>false
<strong>解释：</strong>无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>0 <= nums[i] <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们用变量 $mx$ 维护当前能够到达的最远下标，初始时 $mx = 0$。

我们从左到右遍历数组，对于遍历到的每个位置 $i$，如果 $mx \lt i$，说明当前位置无法到达，直接返回 `false`。否则，我们可以通过跳跃从位置 $i$ 到达的最远位置为 $i+nums[i]$，我们用 $i+nums[i]$ 更新 $mx$ 的值，即 $mx = \max(mx, i + nums[i])$。

遍历结束，直接返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

相似题目：

-   [45. 跳跃游戏 II](/solution/0000-0099/0045.Jump%20Game%20II/README.md)
-   [1024. 视频拼接](/solution/1000-1099/1024.Video%20Stitching/README.md)
-   [1326. 灌溉花园的最少水龙头数目](/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        mx = 0
        for i, x in enumerate(nums):
            if mx < i:
                return False
            mx = max(mx, i + x)
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (mx < i) {
                return false;
            }
            mx = Math.max(mx, i + nums[i]);
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canJump(vector<int>& nums) {
        int mx = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (mx < i) {
                return false;
            }
            mx = max(mx, i + nums[i]);
        }
        return true;
    }
};
```

### **Go**

```go
func canJump(nums []int) bool {
	mx := 0
	for i, x := range nums {
		if mx < i {
			return false
		}
		mx = max(mx, i+x)
	}
	return true
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
function canJump(nums: number[]): boolean {
    let mx: number = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (mx < i) {
            return false;
        }
        mx = Math.max(mx, i + nums[i]);
    }
    return true;
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canJump = function (nums) {
    let mx = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (mx < i) {
            return false;
        }
        mx = Math.max(mx, i + nums[i]);
    }
    return true;
};
```

### **C#**

```cs
public class Solution {
    public bool CanJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.Length; ++i) {
            if (mx < i) {
                return false;
            }
            mx = Math.Max(mx, i + nums[i]);
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
