---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3134.Find%20the%20Median%20of%20the%20Uniqueness%20Array/README.md
rating: 2451
source: 第 395 场周赛 Q4
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 滑动窗口
---

<!-- problem:start -->

# [3134. 找出唯一性数组的中位数](https://leetcode.cn/problems/find-the-median-of-the-uniqueness-array)

[English Version](/solution/3100-3199/3134.Find%20the%20Median%20of%20the%20Uniqueness%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。数组 <code>nums</code> 的<strong> 唯一性数组</strong> 是一个按元素从小到大排序的数组，包含了 <code>nums</code> 的所有<span data-keyword="subarray-nonempty">非空子数组中</span>不同元素的个数。</p>

<p>换句话说，这是由所有 <code>0 &lt;= i &lt;= j &lt; nums.length</code> 的 <code>distinct(nums[i..j])</code> 组成的递增数组。</p>

<p>其中，<code>distinct(nums[i..j])</code> 表示从下标 <code>i</code> 到下标 <code>j</code> 的子数组中不同元素的数量。</p>

<p>返回 <code>nums</code> <strong>唯一性数组 </strong>的 <strong>中位数 </strong>。</p>

<p><strong>注意</strong>，数组的 <strong>中位数 </strong>定义为有序数组的中间元素。如果有两个中间元素，则取值较小的那个。<!-- notionvc: 7e0f5178-4273-4a82-95ce-3395297921dc --></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的唯一性数组为 <code>[distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])]</code>，即 <code>[1, 1, 1, 2, 2, 3]</code> 。唯一性数组的中位数为 1 ，因此答案是 1 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,4,3,4,5]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的唯一性数组为 <code>[1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3]</code> 。唯一性数组的中位数为 2 ，因此答案是 2 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,3,5,4]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的唯一性数组为 <code>[1, 1, 1, 1, 2, 2, 2, 3, 3, 3]</code> 。唯一性数组的中位数为 2 ，因此答案是 2 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + 双指针

我们记数组 $\textit{nums}$ 的长度为 $n$，那么唯一性数组的长度为 $m = \frac{(1 + n) \times n}{2}$，而唯一性数组的中位数就是这 $m$ 个数中的第 $\frac{m + 1}{2}$ 小的数字。

考虑唯一性数组中，有多少个数小于等于 $x$。随着 $x$ 的增大，只会有越来越多的数小于等于 $x$。这存在着单调性，因此，我们可以二分枚举 $x$，找到第一个 $x$，满足唯一性数组中小于等于 $x$ 的数的个数大于等于 $\frac{m + 1}{2}$，这个 $x$ 就是唯一性数组的中位数。

我们定义二分查找的左边界 $l = 0$，右边界 $r = n$，然后进行二分查找，对于每个 $\textit{mid}$，我们检查唯一性数组中小于等于 $\textit{mid}$ 的数的个数是否大于等于 $\frac{m + 1}{2}$。我们通过函数 $\text{check}(mx)$ 来实现这一点。

函数 $\text{check}(mx)$ 的实现思路如下：

由于子数组越长，不同元素的个数越多，因此，我们可以利用双指针维护一个滑动窗口，使得窗口中的子数组的不同元素的个数不超过 $mx$。具体地，我们维护一个哈希表 $\textit{cnt}$，$\textit{cnt}[x]$ 表示窗口中元素 $x$ 的个数。我们使用两个指针 $l$ 和 $r$，其中 $l$ 表示窗口的左边界，而 $r$ 表示窗口的右边界。初始时 $l = r = 0$。

我们枚举 $r$，对于每个 $r$，我们将 $\textit{nums}[r]$ 加入窗口中，并更新 $\textit{cnt}[\textit{nums}[r]]$。如果窗口中的不同元素的个数超过了 $mx$，我们需要将 $l$ 右移，直到窗口中的不同元素的个数不超过 $mx$。此时，右端点为 $r$，而左端点为 $[l,..r]$ 的子数组都是满足条件的，一共有 $r - l + 1$ 个子数组。我们将这个数量累加到 $k$ 中，如果 $k$ 大于等于 $\frac{m + 1}{2}$，那么说明唯一性数组中小于等于 $\textit{mid}$ 的数的个数大于等于 $\frac{m + 1}{2}$，我们返回 $\text{true}$，否则返回 $\text{false}$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def medianOfUniquenessArray(self, nums: List[int]) -> int:
        def check(mx: int) -> bool:
            cnt = defaultdict(int)
            k = l = 0
            for r, x in enumerate(nums):
                cnt[x] += 1
                while len(cnt) > mx:
                    y = nums[l]
                    cnt[y] -= 1
                    if cnt[y] == 0:
                        cnt.pop(y)
                    l += 1
                k += r - l + 1
                if k >= (m + 1) // 2:
                    return True
            return False

        n = len(nums)
        m = (1 + n) * n // 2
        return bisect_left(range(n), True, key=check)
```

#### Java

```java
class Solution {
    private long m;
    private int[] nums;

    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        m = (1L + n) * n / 2;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int mx) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long k = 0;
        for (int l = 0, r = 0; r < nums.length; ++r) {
            int x = nums[r];
            cnt.merge(x, 1, Integer::sum);
            while (cnt.size() > mx) {
                int y = nums[l++];
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            }
            k += r - l + 1;
            if (k >= (m + 1) / 2) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int medianOfUniquenessArray(vector<int>& nums) {
        int n = nums.size();
        using ll = long long;
        ll m = (1LL + n) * n / 2;
        int l = 0, r = n;
        auto check = [&](int mx) -> bool {
            unordered_map<int, int> cnt;
            ll k = 0;
            for (int l = 0, r = 0; r < n; ++r) {
                int x = nums[r];
                ++cnt[x];
                while (cnt.size() > mx) {
                    int y = nums[l++];
                    if (--cnt[y] == 0) {
                        cnt.erase(y);
                    }
                }
                k += r - l + 1;
                if (k >= (m + 1) / 2) {
                    return true;
                }
            }
            return false;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func medianOfUniquenessArray(nums []int) int {
	n := len(nums)
	m := (1 + n) * n / 2
	return sort.Search(n, func(mx int) bool {
		cnt := map[int]int{}
		l, k := 0, 0
		for r, x := range nums {
			cnt[x]++
			for len(cnt) > mx {
				y := nums[l]
				cnt[y]--
				if cnt[y] == 0 {
					delete(cnt, y)
				}
				l++
			}
			k += r - l + 1
			if k >= (m+1)/2 {
				return true
			}
		}
		return false
	})
}
```

#### TypeScript

```ts
function medianOfUniquenessArray(nums: number[]): number {
    const n = nums.length;
    const m = Math.floor(((1 + n) * n) / 2);
    let [l, r] = [0, n];
    const check = (mx: number): boolean => {
        const cnt = new Map<number, number>();
        let [l, k] = [0, 0];
        for (let r = 0; r < n; ++r) {
            const x = nums[r];
            cnt.set(x, (cnt.get(x) || 0) + 1);
            while (cnt.size > mx) {
                const y = nums[l++];
                cnt.set(y, cnt.get(y)! - 1);
                if (cnt.get(y) === 0) {
                    cnt.delete(y);
                }
            }
            k += r - l + 1;
            if (k >= Math.floor((m + 1) / 2)) {
                return true;
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
