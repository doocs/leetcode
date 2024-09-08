---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3281.Maximize%20Score%20of%20Numbers%20in%20Ranges/README.md
---

<!-- problem:start -->

# [3281. 范围内整数的最大得分](https://leetcode.cn/problems/maximize-score-of-numbers-in-ranges)

[English Version](/solution/3200-3299/3281.Maximize%20Score%20of%20Numbers%20in%20Ranges/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>start</code> 和一个整数 <code>d</code>，代表 <code>n</code> 个区间 <code>[start[i], start[i] + d]</code>。</p>

<p>你需要选择 <code>n</code> 个整数，其中第 <code>i</code> 个整数必须属于第 <code>i</code> 个区间。所选整数的 <strong>得分</strong> 定义为所选整数两两之间的 <strong>最小 </strong>绝对差。</p>

<p>返回所选整数的 <strong>最大可能得分 </strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">start = [6,0,3], d = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>可以选择整数 8, 0 和 4 获得最大可能得分，得分为 <code>min(|8 - 0|, |8 - 4|, |0 - 4|)</code>，等于 4。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">start = [2,6,13,13], d = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>可以选择整数 2, 7, 13 和 18 获得最大可能得分，得分为 <code>min(|2 - 7|, |2 - 13|, |2 - 18|, |7 - 13|, |7 - 18|, |13 - 18|)</code>，等于 5。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= start.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= start[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= d &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们不妨先对 $\textit{start}$ 数组进行排序，然后我们考虑从左到右选择整数，那么得分就等于我们选择的相邻两个整数的差值的最小值。

如果一个差值 $x$ 满足条件，那么对于任意 $x' \lt x$，也一定满足条件。因此我们可以使用二分查找的方法来找到最大的满足条件的差值。

我们定义二分查找的左边界 $l = 0$，右边界 $r = \textit{start}[-1] + d - \textit{start}[0]$，然后我们每次取中间值 $mid = \left\lfloor \frac{l + r + 1}{2} \right\rfloor$，然后判断是否满足条件。

我们定义一个函数 $\text{check}(mi)$ 来判断是否满足条件，具体实现如下：

-   我们定义一个变量 $\textit{last} = -\infty$，表示上一个选择的整数。
-   我们遍历 $\textit{start}$ 数组，如果 $\textit{last} + \textit{mi} > \textit{st} + d$，那么说明我们无法选择整数 $\textit{st}$，返回 $\text{false}$。否则，我们更新 $\textit{last} = \max(\textit{st}, \textit{last} + \textit{mi})$。
-   如果遍历完整个 $\textit{start}$ 数组都满足条件，那么返回 $\text{true}$。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是 $\textit{start}$ 数组的长度和最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPossibleScore(self, start: List[int], d: int) -> int:
        def check(mi: int) -> bool:
            last = -inf
            for st in start:
                if last + mi > st + d:
                    return False
                last = max(st, last + mi)
            return True

        start.sort()
        l, r = 0, start[-1] + d - start[0]
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
    private int[] start;
    private int d;

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        this.start = start;
        this.d = d;
        int n = start.length;
        int l = 0, r = start[n - 1] + d - start[0];
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int mi) {
        long last = Long.MIN_VALUE;
        for (int st : start) {
            if (last + mi > st + d) {
                return false;
            }
            last = Math.max(st, last + mi);
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPossibleScore(vector<int>& start, int d) {
        ranges::sort(start);
        auto check = [&](int mi) -> bool {
            long long last = LLONG_MIN;
            for (int st : start) {
                if (last + mi > st + d) {
                    return false;
                }
                last = max((long long) st, last + mi);
            }
            return true;
        };
        int l = 0, r = start.back() + d - start[0];
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
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
func maxPossibleScore(start []int, d int) int {
	check := func(mi int) bool {
		last := math.MinInt64
		for _, st := range start {
			if last+mi > st+d {
				return false
			}
			last = max(st, last+mi)
		}
		return true
	}
	sort.Ints(start)
	l, r := 0, start[len(start)-1]+d-start[0]
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
function maxPossibleScore(start: number[], d: number): number {
    start.sort((a, b) => a - b);
    let [l, r] = [0, start.at(-1)! + d - start[0]];
    const check = (mi: number): boolean => {
        let last = -Infinity;
        for (const st of start) {
            if (last + mi > st + d) {
                return false;
            }
            last = Math.max(st, last + mi);
        }
        return true;
    };
    while (l < r) {
        const mid = l + ((r - l + 1) >> 1);
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
