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

注意到数组按照非递减顺序排列，因此对于每个 `numbers[i]`，可以通过二分查找的方式找到 `target - numbers[i]` 的位置，如果存在，那么返回 $[i + 1, j + 1]$ 即可。

时间复杂度 $O(n \times \log n)$，其中 $n$ 为数组 `numbers` 的长度。空间复杂度 $O(1)$。

**方法二：双指针**

我们定义两个指针 $i$ 和 $j$，分别指向数组的第一个元素和最后一个元素。每次计算 $numbers[i] + numbers[j]$，如果和等于目标值，那么返回 $[i + 1, j + 1]$ 即可。如果和小于目标值，那么将 $i$ 右移一位，如果和大于目标值，那么将 $j$ 左移一位。

时间复杂度 $O(n)$，其中 $n$ 为数组 `numbers` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        n = len(numbers)
        for i in range(n - 1):
            x = target - numbers[i]
            j = bisect_left(numbers, x, lo=i + 1)
            if j < n and numbers[j] == x:
                return [i + 1, j + 1]
```

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        i, j = 0, len(numbers) - 1
        while i < j:
            x = numbers[i] + numbers[j]
            if x == target:
                return [i + 1, j + 1]
            if x < target:
                i += 1
            else:
                j -= 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, n = numbers.length;; ++i) {
            int x = target - numbers[i];
            int l = i + 1, r = n - 1;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (numbers[mid] >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (numbers[l] == x) {
                return new int[] {i + 1, l + 1};
            }
        }
    }
}
```

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, j = numbers.length - 1;;) {
            int x = numbers[i] + numbers[j];
            if (x == target) {
                return new int[] {i + 1, j + 1};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        for (int i = 0, n = numbers.size();; ++i) {
            int x = target - numbers[i];
            int j = lower_bound(numbers.begin() + i + 1, numbers.end(), x) - numbers.begin();
            if (j < n && numbers[j] == x) {
                return {i + 1, j + 1};
            }
        }
    }
};
```

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        for (int i = 0, j = numbers.size() - 1;;) {
            int x = numbers[i] + numbers[j];
            if (x == target) {
                return {i + 1, j + 1};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
    }
};
```

### **Go**

```go
func twoSum(numbers []int, target int) []int {
	for i, n := 0, len(numbers); ; i++ {
		x := target - numbers[i]
		j := sort.SearchInts(numbers[i+1:], x) + i + 1
		if j < n && numbers[j] == x {
			return []int{i + 1, j + 1}
		}
	}
}
```

```go
func twoSum(numbers []int, target int) []int {
	for i, j := 0, len(numbers)-1; ; {
		x := numbers[i] + numbers[j]
		if x == target {
			return []int{i + 1, j + 1}
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
}
```

### **TypeScript**

```ts
function twoSum(numbers: number[], target: number): number[] {
    const n = numbers.length;
    for (let i = 0; ; ++i) {
        const x = target - numbers[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (numbers[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (numbers[l] === x) {
            return [i + 1, l + 1];
        }
    }
}
```

```ts
function twoSum(numbers: number[], target: number): number[] {
    for (let i = 0, j = numbers.length - 1; ; ) {
        const x = numbers[i] + numbers[j];
        if (x === target) {
            return [i + 1, j + 1];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    const n = numbers.length;
    for (let i = 0; ; ++i) {
        const x = target - numbers[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (numbers[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (numbers[l] === x) {
            return [i + 1, l + 1];
        }
    }
};
```

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    for (let i = 0, j = numbers.length - 1; ; ) {
        const x = numbers[i] + numbers[j];
        if (x === target) {
            return [i + 1, j + 1];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
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
