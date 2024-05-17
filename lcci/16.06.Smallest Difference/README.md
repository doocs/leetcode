---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.06.Smallest%20Difference/README.md
---

<!-- problem:start -->

# [面试题 16.06. 最小差](https://leetcode.cn/problems/smallest-difference-lcci)

[English Version](/lcci/16.06.Smallest%20Difference/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个整数数组<code>a</code>和<code>b</code>，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
<strong>输出：</strong> 3，即数值对(11, 8)
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>1 <= a.length, b.length <= 100000</code></li>
<li><code>-2147483648 <= a[i], b[i] <= 2147483647</code></li>
<li>正确结果在区间[-2147483648, 2147483647]内</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们可以对数组 $b$ 进行排序，并对数组 $a$ 中的每个元素 $x$ 在数组 $b$ 中进行二分查找，找到最接近 $x$ 的元素 $y$，那么 $x$ 和 $y$ 的差的绝对值就是 $x$ 和 $b$ 中最接近 $x$ 的元素的差的绝对值。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $b$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        b.sort()
        ans = inf
        n = len(b)
        for x in a:
            j = bisect_left(b, x)
            if j < n:
                ans = min(ans, b[j] - x)
            if j:
                ans = min(ans, x - b[j - 1])
        return ans
```

#### Java

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(b);
        long ans = Long.MAX_VALUE;
        for (int x : a) {
            int j = search(b, x);
            if (j < b.length) {
                ans = Math.min(ans, (long) b[j] - x);
            }
            if (j > 0) {
                ans = Math.min(ans, (long) x - b[j - 1]);
            }
        }
        return (int) ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestDifference(vector<int>& a, vector<int>& b) {
        sort(b.begin(), b.end());
        long long ans = LONG_LONG_MAX;
        for (int x : a) {
            auto it = lower_bound(b.begin(), b.end(), x);
            if (it != b.end()) {
                ans = min(ans, (long long) *it - x);
            }
            if (it != b.begin()) {
                ans = min(ans, x - (long long) *prev(it));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func smallestDifference(a []int, b []int) int {
	sort.Ints(b)
	var ans int = 1e18
	for _, x := range a {
		i := sort.SearchInts(b, x)
		if i < len(b) {
			ans = min(ans, b[i]-x)
		}
		if i > 0 {
			ans = min(ans, x-b[i-1])
		}
	}
	return ans
}
```

#### TypeScript

```ts
function smallestDifference(a: number[], b: number[]): number {
    b.sort((a, b) => a - b);
    let ans = Infinity;
    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (const x of a) {
        const j = search(b, x);
        if (j < b.length) {
            ans = Math.min(ans, b[j] - x);
        }
        if (j > 0) {
            ans = Math.min(ans, x - b[j - 1]);
        }
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func smallestDifference(_ a: [Int], _ b: [Int]) -> Int {
        let sortedB = b.sorted()
        var ans = Int.max

        for x in a {
            let j = search(sortedB, x)
            if j < sortedB.count {
                ans = min(ans, abs(sortedB[j] - x))
            }
            if j > 0 {
                ans = min(ans, abs(x - sortedB[j - 1]))
            }
        }

        return ans
    }

    private func search(_ nums: [Int], _ x: Int) -> Int {
        var l = 0
        var r = nums.count
        while l < r {
            let mid = (l + r) / 2
            if nums[mid] >= x {
                r = mid
            } else {
                l = mid + 1
            }
        }
        return l
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：排序 + 双指针

我们可以对数组 $a$ 和 $b$ 分别进行排序，然后使用双指针的方法，维护两个指针 $i$ 和 $j$，初始时分别指向数组 $a$ 和 $b$ 的起始位置。每一次，我们计算 $a[i]$ 和 $b[j]$ 的差的绝对值，并且更新答案。如果 $a[i]$ 和 $b[j]$ 指向的两个元素中的一个元素比另一个元素要小，则将指向较小元素的指针向前移动一步。当至少有一个指针超出数组范围时，遍历结束。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $a$ 和 $b$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        a.sort()
        b.sort()
        i = j = 0
        ans = inf
        while i < len(a) and j < len(b):
            ans = min(ans, abs(a[i] - b[j]))
            if a[i] < b[j]:
                i += 1
            else:
                j += 1
        return ans
```

#### Java

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        long ans = Long.MAX_VALUE;
        while (i < a.length && j < b.length) {
            ans = Math.min(ans, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] < b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestDifference(vector<int>& a, vector<int>& b) {
        sort(a.begin(), a.end());
        sort(b.begin(), b.end());
        int i = 0, j = 0;
        long long ans = LONG_LONG_MAX;
        while (i < a.size() && j < b.size()) {
            ans = min(ans, abs(1LL * a[i] - 1LL * b[j]));
            if (a[i] < b[j]) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func smallestDifference(a []int, b []int) int {
	sort.Ints(a)
	sort.Ints(b)
	i, j := 0, 0
	var ans int = 1e18
	for i < len(a) && j < len(b) {
		ans = min(ans, abs(a[i]-b[j]))
		if a[i] < b[j] {
			i++
		} else {
			j++
		}
	}
	return ans
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}
```

#### TypeScript

```ts
function smallestDifference(a: number[], b: number[]): number {
    a.sort((a, b) => a - b);
    b.sort((a, b) => a - b);
    let [i, j] = [0, 0];
    let ans = Infinity;
    while (i < a.length && j < b.length) {
        ans = Math.min(ans, Math.abs(a[i] - b[j]));
        if (a[i] < b[j]) {
            ++i;
        } else {
            ++j;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
