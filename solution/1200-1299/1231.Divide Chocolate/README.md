---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1231.Divide%20Chocolate/README.md
rating: 2029
source: 第 11 场双周赛 Q4
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [1231. 分享巧克力 🔒](https://leetcode.cn/problems/divide-chocolate)

[English Version](/solution/1200-1299/1231.Divide%20Chocolate/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一大块巧克力，它由一些甜度不完全相同的小块组成。我们用数组&nbsp;<code>sweetness</code>&nbsp;来表示每一小块的甜度。</p>

<p>你打算和&nbsp;<code>K</code>&nbsp;名朋友一起分享这块巧克力，所以你需要将切割&nbsp;<code>K</code>&nbsp;次才能得到 <code>K+1</code>&nbsp;块，每一块都由一些 <strong>连续&nbsp;</strong>的小块组成。</p>

<p>为了表现出你的慷慨，你将会吃掉&nbsp;<strong>总甜度最小</strong> 的一块，并将其余几块分给你的朋友们。</p>

<p>请找出一个最佳的切割策略，使得你所分得的巧克力&nbsp;<strong>总甜度最大</strong>，并返回这个 <strong>最大总甜度</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>sweetness = [1,2,3,4,5,6,7,8,9], K = 5
<strong>输出：</strong>6
<strong>解释：</strong>你可以把巧克力分成 [1,2,3], [4,5], [6], [7], [8], [9]。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>sweetness = [5,6,7,8,9,1,2,3,4], K = 8
<strong>输出：</strong>1
<strong>解释：</strong>只有一种办法可以把巧克力分成 9 块。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>sweetness = [1,2,2,1,2,2,1,2,2], K = 2
<strong>输出：</strong>5
<strong>解释：</strong>你可以把巧克力分成 [1,2,2], [1,2,2], [1,2,2]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= K &lt;&nbsp;sweetness.length &lt;= 10^4</code></li>
	<li><code>1 &lt;= sweetness[i] &lt;= 10^5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + 贪心

我们注意到，如果我们能吃到一块甜度为 $x$ 的巧克力，那么甜度小于等于 $x$ 的巧克力也都能吃到。这存在着单调性，因此，我们可以使用二分查找，找到最大的满足条件的 $x$。

我们定义二分查找的左边界 $l=0$，右边界 $r=\sum_{i=0}^{n-1} sweetness[i]$。每一次，我们取 $l$ 和 $r$ 的中间值 $mid$，然后判断能否吃到一块甜度为 $mid$ 的巧克力。如果能吃到，那么我们就尝试吃掉甜度更大的巧克力，即令 $l=mid$；否则，我们就尝试吃掉甜度更小的巧克力，即令 $r=mid-1$。在二分查找结束后，我们返回 $l$ 即可。

问题的关键在于，我们如何判断能否吃到一块甜度为 $x$ 的巧克力。我们可以使用贪心的思想，从左到右遍历数组，每次累加当前的甜度，当累加的甜度大于等于 $x$ 时，那么巧克力数 $cnt$ 加 $1$，并将累加的甜度清零。最后判断 $cnt$ 是否大于 $k$ 即可。

时间复杂度 $O(n \times \log \sum_{i=0}^{n-1} sweetness[i])$，空间复杂度 $O(1)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximizeSweetness(self, sweetness: List[int], k: int) -> int:
        def check(x: int) -> bool:
            s = cnt = 0
            for v in sweetness:
                s += v
                if s >= x:
                    s = 0
                    cnt += 1
            return cnt > k

        l, r = 0, sum(sweetness)
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class Solution {
    public int maximizeSweetness(int[] sweetness, int k) {
        int l = 0, r = 0;
        for (int v : sweetness) {
            r += v;
        }
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(sweetness, mid, k)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int x, int k) {
        int s = 0, cnt = 0;
        for (int v : nums) {
            s += v;
            if (s >= x) {
                s = 0;
                ++cnt;
            }
        }
        return cnt > k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximizeSweetness(vector<int>& sweetness, int k) {
        int l = 0, r = accumulate(sweetness.begin(), sweetness.end(), 0);
        auto check = [&](int x) {
            int s = 0, cnt = 0;
            for (int v : sweetness) {
                s += v;
                if (s >= x) {
                    s = 0;
                    ++cnt;
                }
            }
            return cnt > k;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func maximizeSweetness(sweetness []int, k int) int {
	l, r := 0, 0
	for _, v := range sweetness {
		r += v
	}
	check := func(x int) bool {
		s, cnt := 0, 0
		for _, v := range sweetness {
			s += v
			if s >= x {
				s = 0
				cnt++
			}
		}
		return cnt > k
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

#### TypeScript

```ts
function maximizeSweetness(sweetness: number[], k: number): number {
    let l = 0;
    let r = sweetness.reduce((a, b) => a + b);
    const check = (x: number): boolean => {
        let s = 0;
        let cnt = 0;
        for (const v of sweetness) {
            s += v;
            if (s >= x) {
                s = 0;
                ++cnt;
            }
        }
        return cnt > k;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
