---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1095.Find%20in%20Mountain%20Array/README.md
rating: 1827
tags:
    - 数组
    - 二分查找
    - 交互
---

# [1095. 山脉数组中查找目标值](https://leetcode.cn/problems/find-in-mountain-array)

[English Version](/solution/1000-1099/1095.Find%20in%20Mountain%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>（这是一个 <strong>交互式问题&nbsp;</strong>）</p>

<p>给你一个 <strong>山脉数组</strong>&nbsp;<code>mountainArr</code>，请你返回能够使得&nbsp;<code>mountainArr.get(index)</code>&nbsp;<strong>等于</strong>&nbsp;<code>target</code>&nbsp;<strong>最小</strong>&nbsp;的下标 <code>index</code>&nbsp;值。</p>

<p>如果不存在这样的下标 <code>index</code>，就请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p>何为山脉数组？如果数组&nbsp;<code>A</code> 是一个山脉数组的话，那它满足如下条件：</p>

<p><strong>首先</strong>，<code>A.length &gt;= 3</code></p>

<p><strong>其次</strong>，在&nbsp;<code>0 &lt; i&nbsp;&lt; A.length - 1</code>&nbsp;条件下，存在 <code>i</code> 使得：</p>

<ul>
	<li><code>A[0] &lt; A[1] &lt; ... A[i-1] &lt; A[i]</code></li>
	<li><code>A[i] &gt; A[i+1] &gt; ... &gt; A[A.length - 1]</code></li>
</ul>

<p>&nbsp;</p>

<p>你将&nbsp;<strong>不能直接访问该山脉数组</strong>，必须通过&nbsp;<code>MountainArray</code>&nbsp;接口来获取数据：</p>

<ul>
	<li><code>MountainArray.get(k)</code>&nbsp;- 会返回数组中索引为<code>k</code>&nbsp;的元素（下标从 0 开始）</li>
	<li><code>MountainArray.length()</code>&nbsp;- 会返回该数组的长度</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<p>对&nbsp;<code>MountainArray.get</code>&nbsp;发起超过 <code>100</code> 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。</p>

<p>为了帮助大家更好地理解交互式问题，我们准备了一个样例 &ldquo;<strong>答案</strong>&rdquo;：<a href="https://leetcode.cn/playground/RKhe3ave" target="_blank">https://leetcode.cn/playground/RKhe3ave</a>，请注意这 <strong>不是一个正确答案</strong>。</p>

<ol>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>array = [1,2,3,4,5,3,1], target = 3
<strong>输出：</strong>2
<strong>解释：</strong>3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>array = [0,1,2,4,2,1], target = 3
<strong>输出：</strong>-1
<strong>解释：</strong>3 在数组中没有出现，返回 -1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= mountain_arr.length() &lt;= 10000</code></li>
	<li><code>0 &lt;= target &lt;= 10^9</code></li>
	<li><code>0 &lt;= mountain_arr.get(index) &lt;=&nbsp;10^9</code></li>
</ul>

## 解法

### 方法一：二分查找

我们先通过二分查找，找到数组中的最大值所在的下标 $l$，那么数组就可以被分成两段，前半段是递增的，后半段是递减的。

然后我们在前半段中使用二分查找，查找目标值所在的下标，如果找不到，再在后半段中使用二分查找，查找目标值所在的下标。

时间复杂度 $O(\log n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# """
# This is MountainArray's API interface.
# You should not implement it, or speculate about its implementation
# """
# class MountainArray:
#    def get(self, index: int) -> int:
#    def length(self) -> int:


class Solution:
    def findInMountainArray(self, target: int, mountain_arr: 'MountainArray') -> int:
        def search(l: int, r: int, k: int) -> int:
            while l < r:
                mid = (l + r) >> 1
                if k * mountain_arr.get(mid) >= k * target:
                    r = mid
                else:
                    l = mid + 1
            return -1 if mountain_arr.get(l) != target else l

        n = mountain_arr.length()
        l, r = 0, n - 1
        while l < r:
            mid = (l + r) >> 1
            if mountain_arr.get(mid) > mountain_arr.get(mid + 1):
                r = mid
            else:
                l = mid + 1
        ans = search(0, l, 1)
        return search(l + 1, n - 1, -1) if ans == -1 else ans
```

```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    private MountainArray mountainArr;
    private int target;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        this.mountainArr = mountainArr;
        this.target = target;
        int ans = search(0, l, 1);
        return ans == -1 ? search(l + 1, n - 1, -1) : ans;
    }

    private int search(int l, int r, int k) {
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (k * mountainArr.get(mid) >= k * target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return mountainArr.get(l) == target ? l : -1;
    }
}
```

```cpp
/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * class MountainArray {
 *   public:
 *     int get(int index);
 *     int length();
 * };
 */

class Solution {
public:
    int findInMountainArray(int target, MountainArray& mountainArr) {
        int n = mountainArr.length();
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        auto search = [&](int l, int r, int k) -> int {
            while (l < r) {
                int mid = (l + r) >> 1;
                if (k * mountainArr.get(mid) >= k * target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return mountainArr.get(l) == target ? l : -1;
        };
        int ans = search(0, l, 1);
        return ans == -1 ? search(l + 1, n - 1, -1) : ans;
    }
};
```

```go
/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * type MountainArray struct {
 * }
 *
 * func (this *MountainArray) get(index int) int {}
 * func (this *MountainArray) length() int {}
 */

func findInMountainArray(target int, mountainArr *MountainArray) int {
	n := mountainArr.length()
	l, r := 0, n-1
	for l < r {
		mid := (l + r) >> 1
		if mountainArr.get(mid) > mountainArr.get(mid+1) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	search := func(l, r, k int) int {
		for l < r {
			mid := (l + r) >> 1
			if k*mountainArr.get(mid) >= k*target {
				r = mid
			} else {
				l = mid + 1
			}
		}
		if mountainArr.get(l) == target {
			return l
		}
		return -1
	}
	ans := search(0, l, 1)
	if ans == -1 {
		return search(l+1, n-1, -1)
	}
	return ans
}
```

```ts
/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Master {
 *      get(index: number): number {}
 *
 *      length(): number {}
 * }
 */

function findInMountainArray(target: number, mountainArr: MountainArray) {
    const n = mountainArr.length();
    let l = 0;
    let r = n - 1;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    const search = (l: number, r: number, k: number): number => {
        while (l < r) {
            const mid = (l + r) >> 1;
            if (k * mountainArr.get(mid) >= k * target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return mountainArr.get(l) === target ? l : -1;
    };
    const ans = search(0, l, 1);
    return ans === -1 ? search(l + 1, n - 1, -1) : ans;
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn find_in_mountain_array(target: i32, mountain_arr: &MountainArray) -> i32 {
        let n = mountain_arr.length();

        // First find the maximum element in the array
        let mut l = 0;
        let mut r = n - 1;

        while l < r {
            let mid = (l + r) >> 1;
            if mountain_arr.get(mid) > mountain_arr.get(mid + 1) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        let left = Self::binary_search(mountain_arr, 0, l, 1, target);

        if left == -1 {
            Self::binary_search(mountain_arr, l, n - 1, -1, target)
        } else {
            left
        }
    }

    #[allow(dead_code)]
    fn binary_search(m: &MountainArray, mut l: i32, mut r: i32, k: i32, target: i32) -> i32 {
        let n = m.length();

        while l < r {
            let mid = (l + r) >> 1;
            if k * m.get(mid) >= k * target {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if m.get(l) == target {
            l
        } else {
            -1
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
