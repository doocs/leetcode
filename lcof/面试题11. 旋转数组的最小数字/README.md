---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9811.%20%E6%97%8B%E8%BD%AC%E6%95%B0%E7%BB%84%E7%9A%84%E6%9C%80%E5%B0%8F%E6%95%B0%E5%AD%97/README.md
---

<!-- problem:start -->

# [面试题 11. 旋转数组的最小数字](https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

## 题目描述

<!-- description:start -->

<p>把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。</p>

<p>给你一个可能存在&nbsp;<strong>重复</strong>&nbsp;元素值的数组&nbsp;<code>numbers</code>&nbsp;，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组&nbsp;<code>[3,4,5,1,2]</code> 为 <code>[1,2,3,4,5]</code> 的一次旋转，该数组的最小值为1。&nbsp;&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[3,4,5,1,2]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[2,2,2,0,1]
<strong>输出：</strong>0
</pre>

<p>注意：本题与主站 154 题相同：<a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/">https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

二分查找的变种，需要考虑重复元素的情况。

我们定义两个指针 $l$ 和 $r$ 分别指向数组的左右两端，每次取中间元素 `numbers[mid]` 与右端元素 `numbers[r]` 比较，有以下三种情况：

-   `numbers[mid] > numbers[r]`：中间元素一定不是最小值，因此 $l = mid + 1$；
-   `numbers[mid] < numbers[r]`：中间元素可能是最小值，因此 $r = mid$；
-   `numbers[mid] == numbers[r]`：无法确定最小值的位置，但可以简单地缩小搜索范围，因此 $r = r - 1$。

循环结束时，指针 $l$ 和 $r$ 指向同一个元素，即为最小值。

时间复杂度 $(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minArray(self, numbers: List[int]) -> int:
        l, r = 0, len(numbers) - 1
        while l < r:
            m = (l + r) >> 1
            if numbers[m] > numbers[r]:
                l = m + 1
            elif numbers[m] < numbers[r]:
                r = m
            else:
                r -= 1
        return numbers[l]
```

#### Java

```java
class Solution {
    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (numbers[m] > numbers[r]) {
                l = m + 1;
            } else if (numbers[m] < numbers[r]) {
                r = m;
            } else {
                --r;
            }
        }
        return numbers[l];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minArray(vector<int>& numbers) {
        int l = 0, r = numbers.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else if (numbers[mid] < numbers[r]) {
                r = mid;
            } else {
                --r;
            }
        }
        return numbers[l];
    }
};
```

#### Go

```go
func minArray(numbers []int) int {
	l, r := 0, len(numbers)-1
	for l < r {
		mid := (l + r) >> 1
		if numbers[mid] > numbers[r] {
			l = mid + 1
		} else if numbers[mid] < numbers[r] {
			r = mid
		} else {
			r--
		}
	}
	return numbers[l]
}
```

#### Rust

```rust
impl Solution {
    pub fn min_array(numbers: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = numbers.len() - 1;
        while l < r {
            let mid = (l + r) >> 1;
            match numbers[mid].cmp(&numbers[r]) {
                std::cmp::Ordering::Less => {
                    r = mid;
                }
                std::cmp::Ordering::Equal => {
                    r -= 1;
                }
                std::cmp::Ordering::Greater => {
                    l = mid + 1;
                }
            }
        }
        numbers[l]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} numbers
 * @return {number}
 */
var minArray = function (numbers) {
    let l = 0,
        r = numbers.length - 1;
    while (l < r) {
        let m = (l + r) >>> 1;
        if (numbers[m] > numbers[r]) {
            l = m + 1;
        } else if (numbers[m] < numbers[r]) {
            r = m;
        } else {
            --r;
        }
    }
    return numbers[l];
};
```

#### C#

```cs
public class Solution {
    public int MinArray(int[] numbers) {
        int l = 0, r = numbers.Length - 1;
        while (l < r) {
            int m = (l + r) >> 1;
            if (numbers[m] > numbers[r]) {
                l = m + 1;
            } else if (numbers[m] < numbers[r]) {
                r = m;
            } else {
                --r;
            }
        }
        return numbers[l];
    }
}
```

#### Swift

```swift
class Solution {
    func minArray(_ numbers: [Int]) -> Int {
        var l = 0
        var r = numbers.count - 1
        while l < r {
            let m = (l + r) / 2
            if numbers[m] > numbers[r] {
                l = m + 1
            } else if numbers[m] < numbers[r] {
                r = m
            } else {
                r -= 1
            }
        }
        return numbers[l]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：二分查找（写法二）

注意，我们也可以每次取中间元素 `numbers[mid]` 与左端元素 `numbers[l]` 比较，但需要考虑当前 $[l,..r]$ 区间内的元素是否已经有序，即是否满足 `numbers[l] < numbers[r]`，如果满足，直接返回 `numbers[l]` 即可。其它情况与方法一类似。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minArray(self, numbers: List[int]) -> int:
        l, r = 0, len(numbers) - 1
        while l < r:
            if numbers[l] < numbers[r]:
                return numbers[l]
            mid = (l + r) >> 1
            if numbers[mid] > numbers[l]:
                l = mid + 1
            elif numbers[mid] < numbers[l]:
                r = mid
            else:
                l += 1
        return numbers[l]
```

#### Java

```java
class Solution {
    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] < numbers[r]) {
                break;
            }
            int m = (l + r) >>> 1;
            if (numbers[m] > numbers[l]) {
                l = m + 1;
            } else if (numbers[m] < numbers[l]) {
                r = m;
            } else {
                ++l;
            }
        }
        return numbers[l];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minArray(vector<int>& numbers) {
        int l = 0, r = numbers.size() - 1;
        while (l < r) {
            if (numbers[l] < numbers[r]) {
                break;
            }
            int mid = (l + r) >> 1;
            if (numbers[mid] > numbers[l]) {
                l = mid + 1;
            } else if (numbers[mid] < numbers[l]) {
                r = mid;
            } else {
                ++l;
            }
        }
        return numbers[l];
    }
};
```

#### Go

```go
func minArray(numbers []int) int {
	l, r := 0, len(numbers)-1
	for l < r {
		if numbers[l] < numbers[r] {
			break
		}
		mid := (l + r) >> 1
		if numbers[mid] > numbers[l] {
			l = mid + 1
		} else if numbers[mid] < numbers[l] {
			r = mid
		} else {
			l++
		}
	}
	return numbers[l]
}
```

#### Rust

```rust
impl Solution {
    pub fn min_array(numbers: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = numbers.len() - 1;
        while l < r {
            if numbers[l] < numbers[r] {
                break;
            }
            let mid = (l + r) >> 1;
            match numbers[mid].cmp(&numbers[l]) {
                std::cmp::Ordering::Less => {
                    r = mid;
                }
                std::cmp::Ordering::Equal => {
                    l += 1;
                }
                std::cmp::Ordering::Greater => {
                    l = mid + 1;
                }
            }
        }
        numbers[l]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} numbers
 * @return {number}
 */
var minArray = function (numbers) {
    let l = 0,
        r = numbers.length - 1;
    while (l < r) {
        if (numbers[l] < numbers[r]) {
            break;
        }
        let m = (l + r) >>> 1;
        if (numbers[m] > numbers[l]) {
            l = m + 1;
        } else if (numbers[m] < numbers[l]) {
            r = m;
        } else {
            ++l;
        }
    }
    return numbers[l];
};
```

#### C#

```cs
public class Solution {
    public int MinArray(int[] numbers) {
        int l = 0, r = numbers.Length - 1;
        while (l < r) {
            if (numbers[l] < numbers[r]) {
                break;
            }
            int m = (l + r) >> 1;
            if (numbers[m] > numbers[l]) {
                l = m + 1;
            } else if (numbers[m] < numbers[l]) {
                r = m;
            } else {
                ++l;
            }
        }
        return numbers[l];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
