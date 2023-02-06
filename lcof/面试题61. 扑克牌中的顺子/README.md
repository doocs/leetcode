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

**方法一：遍历**

我们首先明确顺子不成立的核心条件：

1. 存在非 $0$ 的重复。
2. 最大值与最小值的差距超过 4（最大最小值比较不包括 $0$ 在内）。

因此，我们可以用一个哈希表或数组 `vis` 记录数字是否出现过，用 `mi` 和 `mx` 记录最大值和最小值。遍历数组，忽略大小王（$0$），求出数组的最大、最小值。若最后差值超过 $4$，则无法构成顺子。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        vis = set()
        mi, mx = inf, -inf
        for x in nums:
            if x == 0:
                continue
            if x in vis:
                return False
            vis.add(x)
            mi = min(mi, x)
            mx = max(mx, x)
        return mx - mi <= 4
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isStraight(int[] nums) {
        boolean[] vis = new boolean[14];
        int mi = 20, mx = -1;
        for (int x : nums) {
            if (x == 0) {
                continue;
            }
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
            mi = Math.min(mi, x);
            mx = Math.max(mx, x);
        }
        return mx - mi <= 4;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isStraight(vector<int>& nums) {
        bool vis[14]{};
        int mi = 20, mx = -1;
        for (int& x : nums) {
            if (x == 0) {
                continue;
            }
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
            mi = min(mi, x);
            mx = max(mx, x);
        }
        return mx - mi <= 4;
    }
};
```

### **Go**

```go
func isStraight(nums []int) bool {
	vis := map[int]bool{}
	mi, mx := 20, -1
	for _, x := range nums {
		if x == 0 {
			continue
		}
		if vis[x] {
			return false
		}
		vis[x] = true
		mi = min(mi, x)
		mx = max(mx, x)
	}
	return mx-mi <= 4
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

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isStraight = function (nums) {
    const vis = new Array(14).fill(false);
    let mi = 20;
    let mx = -1;
    for (const x of nums) {
        if (x == 0) {
            continue;
        }
        if (vis[x]) {
            return false;
        }
        vis[x] = true;
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return mx - mi <= 4;
};
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
        bool[] vis = new bool[14];
        int mi = 20, mx = -1;
        foreach(int x in nums) {
            if (x == 0) {
                continue;
            }
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
            mi = Math.Min(mi, x);
            mx = Math.Max(mx, x);
        }
        return mx - mi <= 4;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
