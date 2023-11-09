# [剑指 Offer II 006. 排序数组中两个数字之和](https://leetcode.cn/problems/kLl5u1)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个已按照<strong><em> </em>升序排列&nbsp; </strong>的整数数组&nbsp;<code>numbers</code> ，请你从数组中找出两个数满足相加之和等于目标数&nbsp;<code>target</code> 。</p>

<p>函数应该以长度为 <code>2</code> 的整数数组的形式返回这两个数的下标值<em>。</em><code>numbers</code> 的下标 <strong>从 0&nbsp;开始计数</strong> ，所以答案数组应当满足 <code>0&nbsp;&lt;= answer[0] &lt; answer[1] &lt;&nbsp;numbers.length</code>&nbsp;。</p>

<p>假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numbers = [1,2,4,6,10], target = 8
<strong>输出：</strong>[1,3]
<strong>解释：</strong>2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numbers = [2,3,4], target = 6
<strong>输出：</strong>[0,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>numbers = [-1,0], target = -1
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> 按 <strong>递增顺序</strong> 排列</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li>仅存在一个有效答案</li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 167 题相似（下标起点不同）：<a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们注意到数组按照非递减顺序排列，因此对于每个 $numbers[i]$，可以通过二分查找的方式找到 $target - numbers[i]$ 的位置，如果存在，那么返回 $[i, j]$ 即可。

时间复杂度 $O(n \times \log n)$，其中 $n$ 为数组 $numbers$ 的长度。空间复杂度 $O(1)$。

**方法二：双指针**

我们定义两个指针 $i$ 和 $j$，分别指向数组的第一个元素和最后一个元素。每次计算 $numbers[i] + numbers[j]$，如果和等于目标值，那么返回 $[i, j]$ 即可。如果和小于目标值，那么将 $i$ 右移一位，如果和大于目标值，那么将 $j$ 左移一位。

时间复杂度 $O(n)$，其中 $n$ 为数组 $numbers$ 的长度。空间复杂度 $O(1)$。

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
                return [i, j]
```

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        i, j = 0, len(numbers) - 1
        while i < j:
            x = numbers[i] + numbers[j]
            if x == target:
                return [i, j]
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
                return new int[] {i, l};
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
                return new int[] {i, j};
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
                return {i, j};
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
                return {i, j};
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
			return []int{i, j}
		}
	}
}
```

```go
func twoSum(numbers []int, target int) []int {
	for i, j := 0, len(numbers)-1; ; {
		x := numbers[i] + numbers[j]
		if x == target {
			return []int{i, j}
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
            return [i, l];
        }
    }
}
```

```ts
function twoSum(numbers: number[], target: number): number[] {
    for (let i = 0, j = numbers.length - 1; ; ) {
        const x = numbers[i] + numbers[j];
        if (x === target) {
            return [i, j];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
}
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
            match target.cmp(&(numbers[l] + numbers[r])) {
                Ordering::Less => {
                    r -= 1;
                }
                Ordering::Greater => {
                    l += 1;
                }
                Ordering::Equal => {
                    break;
                }
            }
        }
        vec![l as i32, r as i32]
    }
}
```

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        for i in 0..n - 1 {
            let num = target - numbers[i];
            let mut l = i + 1;
            let mut r = n - 1;
            while l <= r {
                let mid = l + (r - l) / 2;
                match num.cmp(&numbers[mid]) {
                    Ordering::Less => {
                        r = mid - 1;
                    }
                    Ordering::Greater => {
                        l = mid + 1;
                    }
                    Ordering::Equal => {
                        return vec![i as i32, mid as i32];
                    }
                }
            }
        }
        vec![-1, -1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
