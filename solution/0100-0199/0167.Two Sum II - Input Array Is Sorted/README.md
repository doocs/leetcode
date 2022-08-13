# [167. 两数之和 II - 输入有序数组](https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted)

[English Version](/solution/0100-0199/0167.Two%20Sum%20II%20-%20Input%20array%20is%20sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong> 开始的整数数组&nbsp;<code>numbers</code> ，该数组已按<strong><em> </em>非递减顺序排列&nbsp; </strong>，请你从数组中找出满足相加之和等于目标数&nbsp;<code>target</code> 的两个数。如果设这两个数分别是 <code>numbers[index<sub>1</sub>]</code> 和 <code>numbers[index<sub>2</sub>]</code> ，则 <code>1 &lt;= index<sub>1</sub> &lt; index<sub>2</sub> &lt;= numbers.length</code> 。</p>

<p>以长度为 2 的整数数组 <code>[index<sub>1</sub>, index<sub>2</sub>]</code> 的形式返回这两个整数的下标 <code>index<sub>1</sub></code><em> </em>和<em> </em><code>index<sub>2</sub></code>。</p>

<p>你可以假设每个输入 <strong>只对应唯一的答案</strong> ，而且你 <strong>不可以</strong> 重复使用相同的元素。</p>

<p>你所设计的解决方案必须只使用常量级的额外空间。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numbers = [<strong><em>2</em></strong>,<strong><em>7</em></strong>,11,15], target = 9
<strong>输出：</strong>[1,2]
<strong>解释：</strong>2 与 7 之和等于目标数 9 。因此 index<sub>1</sub> = 1, index<sub>2</sub> = 2 。返回 [1, 2] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numbers = [<strong><em>2</em></strong>,3,<strong><em>4</em></strong>], target = 6
<strong>输出：</strong>[1,3]
<strong>解释：</strong>2 与 4 之和等于目标数 6 。因此 index<sub>1</sub> = 1, index<sub>2</sub> = 3 。返回 [1, 3] 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>numbers = [<strong><em>-1</em></strong>,<strong><em>0</em></strong>], target = -1
<strong>输出：</strong>[1,2]
<strong>解释：</strong>-1 与 0 之和等于目标数 -1 。因此 index<sub>1</sub> = 1, index<sub>2</sub> = 2 。返回 [1, 2] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> 按 <strong>非递减顺序</strong> 排列</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li><strong>仅存在一个有效答案</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

用指针 i 固定第一个数，然后二分查找 `[i + 1, n - 1]` 范围内是否存在 j，使得 `numbers[j] == target - numbers[i]`。

时间复杂度 O(nlogn)。

**方法二：双指针**

初始时两个指针 i, j 分别指向数组的首尾位置。每次计算两指针对应的两个元素之和 x，判断 x 与 target 的大小关系：

-   `x == target`，说明找到了答案，返回 `[i + 1, j + 1]`；
-   `x < target`，指针 i 右移；
-   `x > target`，指针 j 左移。

若循环结束后依然没找到答案，则返回 `[-1, -1]`。

时间复杂度 O(n)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

二分查找：

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        n = len(numbers)
        for i in range(n - 1):
            x = target - numbers[i]
            j = bisect.bisect_left(numbers, x, lo=i + 1)
            if j != n and numbers[j] == x:
                return [i + 1, j + 1]
        return [-1, -1]
```

双指针：

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        i, j = 1, len(numbers)
        while i < j:
            x = numbers[i - 1] + numbers[j - 1]
            if x == target:
                return [i, j]
            if x < target:
                i += 1
            else:
                j -= 1
        return [-1, -1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

二分查找：

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, n = numbers.length; i < n - 1; ++i) {
            int x = target - numbers[i];
            int left = i + 1, right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (numbers[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (numbers[left] == x) {
                return new int[]{i + 1, left + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
```

双指针：

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 1, j = numbers.length;
        while (i < j) {
            int x = numbers[i - 1] + numbers[j - 1];
            if (x == target) {
                return new int[]{i, j};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
        return new int[]{-1, -1};
    }
}
```

### **TypeScript**

二分查找：

```ts
function twoSum(numbers: number[], target: number): number[] {
    for (let i = 0, n = numbers.length; i < n - 1; ++i) {
        const x = target - numbers[i];
        let left = i + 1,
            right = n - 1;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (numbers[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (numbers[left] == x) {
            return [i + 1, left + 1];
        }
    }
    return [-1, -1];
}
```

双指针：

```ts
function twoSum(numbers: number[], target: number): number[] {
    let i = 1,
        j = numbers.length;
    while (i < j) {
        const x = numbers[i - 1] + numbers[j - 1];
        if (x == target) {
            return [i, j];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
    return [-1, -1];
}
```

### **C++**

二分查找：

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        for (int i = 0, n = numbers.size(); i < n - 1; ++i) {
            int x = target - numbers[i];
            int j = lower_bound(numbers.begin() + i + 1, numbers.end(), x) - numbers.begin();
            if (j != n && numbers[j] == x) return {i + 1, j + 1};
        }
        return {-1, -1};
    }
};
```

双指针：

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int i = 1, j = numbers.size();
        while (i < j)
        {
            int x = numbers[i - 1] + numbers[j - 1];
            if (x == target) return {i, j};
            if (x < target) ++i;
            else --j;
        }
        return {-1, -1};
    }
};
```

### **Go**

二分查找：

```go
func twoSum(numbers []int, target int) []int {
	for i, n := 0, len(numbers); i < n-1; i++ {
		x := target - numbers[i]
		left, right := i+1, n-1
		for left < right {
			mid := (left + right) >> 1
			if numbers[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if numbers[left] == x {
			return []int{i + 1, left + 1}
		}
	}
	return []int{-1, -1}
}
```

双指针：

```go
func twoSum(numbers []int, target int) []int {
	i, j := 1, len(numbers)
	for i < j {
		x := numbers[i-1] + numbers[j-1]
		if x == target {
			return []int{i, j}
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
	return []int{-1, -1}
}
```

### **JavaScript**

二分查找：

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    for (let i = 0, n = numbers.length; i < n - 1; ++i) {
        const x = target - numbers[i];
        let left = i + 1,
            right = n - 1;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (numbers[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (numbers[left] == x) {
            return [i + 1, left + 1];
        }
    }
    return [-1, -1];
};
```

双指针：

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    let i = 1,
        j = numbers.length;
    while (i < j) {
        const x = numbers[i - 1] + numbers[j - 1];
        if (x == target) {
            return [i, j];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
    return [-1, -1];
};
```

### **Rust**

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        let mut l = 0;
        let mut r = n - 1;
        loop {
            match (numbers[l] + numbers[r]).cmp(&target) {
                Ordering::Less => l += 1,
                Ordering::Greater => r -= 1,
                Ordering::Equal => break,
            }
        }
        vec![l as i32 + 1, r as i32 + 1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
