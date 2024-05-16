---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.03.Magic%20Index/README.md
---

<!-- problem:start -->

# [面试题 08.03. 魔术索引](https://leetcode.cn/problems/magic-index-lcci)

[English Version](/lcci/08.03.Magic%20Index/README_EN.md)

## 题目描述

<!-- description:start -->

<p>魔术索引。 在数组<code>A[0...n-1]</code>中，有所谓的魔术索引，满足条件<code>A[i] = i</code>。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：nums = [0, 2, 3, 4, 5]
<strong> 输出</strong>：0
<strong> 说明</strong>: 0下标的元素为0
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：nums = [1, 1, 1]
<strong> 输出</strong>：1
</pre>

<p><strong>提示:</strong></p>

<ol>
	<li>nums长度在[1, 1000000]之间</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分搜索

我们设计一个函数 $dfs(i, j)$，表示在数组 $nums[i, j]$ 中寻找魔术索引。如果找到了，返回魔术索引的值，否则返回 $-1$。那么答案就是 $dfs(0, n-1)$。

函数 $dfs(i, j)$ 的实现如下：

1. 如果 $i > j$，返回 $-1$。
2. 否则，我们取中间位置 $mid = (i + j) / 2$，然后递归调用 $dfs(i, mid-1)$，如果返回值不为 $-1$，说明在左半部分找到了魔术索引，直接返回。否则，如果 $nums[mid] = mid$，说明找到了魔术索引，直接返回。否则，递归调用 $dfs(mid+1, j)$ 并返回。

时间复杂度最坏情况下为 $O(n)$，空间复杂度最坏情况下为 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def findMagicIndex(self, nums: List[int]) -> int:
        def dfs(i: int, j: int) -> int:
            if i > j:
                return -1
            mid = (i + j) >> 1
            l = dfs(i, mid - 1)
            if l != -1:
                return l
            if nums[mid] == mid:
                return mid
            return dfs(mid + 1, j)

        return dfs(0, len(nums) - 1)
```

```java
class Solution {
    public int findMagicIndex(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private int dfs(int[] nums, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) >> 1;
        int l = dfs(nums, i, mid - 1);
        if (l != -1) {
            return l;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return dfs(nums, mid + 1, j);
    }
}
```

```cpp
class Solution {
public:
    int findMagicIndex(vector<int>& nums) {
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return -1;
            }
            int mid = (i + j) >> 1;
            int l = dfs(i, mid - 1);
            if (l != -1) {
                return l;
            }
            if (nums[mid] == mid) {
                return mid;
            }
            return dfs(mid + 1, j);
        };
        return dfs(0, nums.size() - 1);
    }
};
```

```go
func findMagicIndex(nums []int) int {
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return -1
		}
		mid := (i + j) >> 1
		if l := dfs(i, mid-1); l != -1 {
			return l
		}
		if nums[mid] == mid {
			return mid
		}
		return dfs(mid+1, j)
	}
	return dfs(0, len(nums)-1)
}
```

```ts
function findMagicIndex(nums: number[]): number {
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return -1;
        }
        const mid = (i + j) >> 1;
        const l = dfs(i, mid - 1);
        if (l !== -1) {
            return l;
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return dfs(mid + 1, j);
    };
    return dfs(0, nums.length - 1);
}
```

```rust
impl Solution {
    fn dfs(nums: &Vec<i32>, i: usize, j: usize) -> i32 {
        if i >= j || nums[j - 1] < 0 {
            return -1;
        }
        let mid = (i + j) >> 1;
        if nums[mid] >= (i as i32) {
            let l = Self::dfs(nums, i, mid);
            if l != -1 {
                return l;
            }
        }
        if nums[mid] == (mid as i32) {
            return mid as i32;
        }
        Self::dfs(nums, mid + 1, j)
    }

    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        Self::dfs(&nums, 0, nums.len())
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMagicIndex = function (nums) {
    const dfs = (i, j) => {
        if (i > j) {
            return -1;
        }
        const mid = (i + j) >> 1;
        const l = dfs(i, mid - 1);
        if (l !== -1) {
            return l;
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return dfs(mid + 1, j);
    };
    return dfs(0, nums.length - 1);
};
```

```swift
class Solution {
    func findMagicIndex(_ nums: [Int]) -> Int {
        return find(nums, 0, nums.count - 1)
    }

    private func find(_ nums: [Int], _ i: Int, _ j: Int) -> Int {
        if i > j {
            return -1
        }
        let mid = (i + j) >> 1
        let l = find(nums, i, mid - 1)
        if l != -1 {
            return l
        }
        if nums[mid] == mid {
            return mid
        }
        return find(nums, mid + 1, j)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
