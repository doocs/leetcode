# [面试题 61. 扑克牌中的顺子](https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>从<strong>若干副扑克牌</strong>中随机抽 <code>5</code> 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3,4,5]
<strong>输出:</strong> True</pre>

<p>&nbsp;</p>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> [0,0,1,2,5]
<strong>输出:</strong> True</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p>数组长度为 5&nbsp;</p>

<p>数组的数取值为 [0, 13] .</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

顺子不成立的核心条件：

-   存在重复。
-   最大值与最小值的差距超过 4（最大最小值比较不包括 0 在内）。

解决方案：

-   数组计数

    -   用数组 `t` 记录是否存在重复的数，存在则直接返回 `false`。
    -   遍历数组，忽略大小王(0)，求出数组的最大、最小值。若最后差值超过 4，则无法构成顺子，例如：`5,6,(0),8,10`。

-   排序
    -   声明一个起始指针，初始化为 0。
    -   对数组进行排序，并遍历数组：
        -   若遍历元素为 0，将起始指针向右移。
        -   若遍历元素与相邻元素相同（忽略 0），则绝对不成立，`return false`。
    -   遍历结束，比较最大值（数组末尾元素）与起始指针所指向的元素，若是两者值相差大于 4，则顺子不成立。
        > 起始指针所做的便是找到除 0 之外，数组当中的最小值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        t = [False for _ in range(14)]
        max_val, min_val = 0, 14
        for num in nums:
            if num == 0:
                continue
            if t[num]:
                return False
            t[num] = True
            max_val = max(max_val, num)
            min_val = min(min_val, num)
        return max_val - min_val <= 4
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isStraight(int[] nums) {
        boolean[] t = new boolean[14];
        int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (t[num]) {
                return false;
            }
            t[num] = true;
            maxVal = Math.max(maxVal, num);
            minVal = Math.min(minVal, num);
        }
        return maxVal - minVal <= 4;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isStraight = function (nums) {
    let zeroCnt = 0;
    nums.sort((a, b) => a - b);
    for (let i = 0; i < nums.length - 1; i++) {
        if (nums[i] === 0) zeroCnt++;
        else {
            if (nums[i] === nums[i + 1]) return false;
            else if (nums[i] === nums[i + 1] - 1) {
                continue;
            } else if (nums[i] >= nums[i + 1] - zeroCnt - 1) {
                zeroCnt--;
            } else {
                return false;
            }
        }
        if (zeroCnt < 0) return false;
    }
    return true;
};
```

### **C++**

```cpp
class Solution {
public:
    bool isStraight(vector<int>& nums) {
        if (nums.size() != 5) {
            return false;
        }

        std::sort(nums.begin(), nums.end());
        int zeroNum = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] != 0) {
                // 这题的用例中，会出现超过两个0的情况
                break;
            }
            zeroNum++;
        }

        for (int i = zeroNum; i < nums.size() - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return false;
            }
        }

        return nums[4] - nums[zeroNum] <= 4;
    }
};
```

### **Go**

```go
func isStraight(nums []int) bool {
	m := make(map[int]struct{})
	mi, ma := 14, 0
	for _, num := range nums {
		if num == 0 {
			continue
		}
		if _, exist := m[num]; exist {
			return false
		}
		mi = min(mi, num)
		ma = max(ma, num)
		m[num] = struct{}{}
	}
	return ma-mi < 5
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **TypeScript**

```ts
function isStraight(nums: number[]): boolean {
    nums.sort((a, b) => a - b);
    let j = 0;
    for (let i = 0; i < 4; i++) {
        if (nums[i] === 0) {
            j++;
        } else if (nums[i] === nums[i + 1]) {
            return false;
        }
    }
    return nums[4] - nums[j] <= 4;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_straight(mut nums: Vec<i32>) -> bool {
        nums.sort();
        let mut j = 0;
        for i in 0..4 {
            if nums[i] == 0 {
                j += 1;
            } else if nums[i] == nums[i + 1] {
                return false;
            }
        }
        nums[4] - nums[j] <= 4
    }
}
```

### **C#**

```cs
public class Solution {
    public bool IsStraight(int[] nums) {
        bool[] t = new bool[14];
        int max_val = 0, min_val = 14;
        foreach(var num in nums) {
            if (num == 0) {
                continue;
            }
            if (t[num]) {
                return false;
            }
            t[num] = true;
            max_val = Math.Max(max_val, num);
            min_val = Math.Min(min_val, num);
        }
        return max_val - min_val <= 4;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
