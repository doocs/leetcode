---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2604.Minimum%20Time%20to%20Eat%20All%20Grains/README.md
tags:
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [2604. 吃掉所有谷子的最短时间 🔒](https://leetcode.cn/problems/minimum-time-to-eat-all-grains)

[English Version](/solution/2600-2699/2604.Minimum%20Time%20to%20Eat%20All%20Grains/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一条线上有 <code>n</code> 只母鸡和 <code>m</code> 颗谷子。给定两个整数数组 <code>hens</code> 和 <code>grains</code> ，它们的大小分别为 <code>n</code> 和 <code>m</code> ，表示母鸡和谷子的初始位置。</p>

<p>如果一只母鸡和一颗谷子在同一个位置，那么这只母鸡可以吃掉这颗谷子。吃掉一颗谷子的时间可以忽略不计。一只母鸡也可以吃掉多颗谷子。</p>

<p>在 <code>1</code> 秒钟内，一只母鸡可以向左或向右移动 <code>1</code> 个单位。母鸡可以同时且独立地移动。</p>

<p>如果母鸡行动得当，返回吃掉所有谷子的 <strong>最短</strong> 时间。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>hens = [3,6,7], grains = [2,4,7,9]
<b>输出：</b>2
<b>解释：</b>
母鸡吃掉所有谷子的一种方法如下：
- 第一只母鸡在 1 秒钟内吃掉位置 2 处的谷子。
- 第二只母鸡在 2 秒钟内吃掉位置 4 处的谷子。
- 第三只母鸡在 2 秒钟内吃掉位置 7 和 9 处的谷子。 
所以，需要的最长时间为2秒。 
可以证明，在2秒钟之前，母鸡不能吃掉所有谷子。</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>hens = [4,6,109,111,213,215], grains = [5,110,214]
<b>输出：</b>1
<b>解释：</b>
母鸡吃掉所有谷子的一种方法如下：
- 第一只母鸡在 1 秒钟内吃掉位置 5 处的谷子。
- 第四只母鸡在 1 秒钟内吃掉位置 110 处的谷子。
- 第六只母鸡在 1 秒钟内吃掉位置 214 处的谷子。
- 其他母鸡不动。 
所以，需要的最长时间为 1 秒。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hens.length, grains.length &lt;= 2*10<sup>4</sup></code></li>
	<li><code>0 &lt;= hens[i], grains[j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们先将鸡和谷物按照位置从小到大排序，接下来二分枚举时间 $t$，找到一个最小的 $t$ 使得所有谷物能在 $t$ 秒内被吃完。

对于每个鸡，我们用指针 $j$ 指向当前还未被吃的谷物中最左边的谷物，记当前鸡的位置为 $x$，谷物的位置为 $y$，则有以下几种情况：

-   如果 $y \leq x$，我们记 $d = x - y$，如果 $d \gt t$，那么当前谷物无法被吃掉，直接返回 `false`。否则，我们向右移动指针 $j$，直到 $j=m$ 或者 $grains[j] \gt x$。此时我们需要判断鸡是否能吃到 $j$ 指向的谷物，如果能吃到，我们继续向右移动指针 $j$，直到 $j=m$ 或者 $min(d, grains[j] - x) + grains[j] - y \gt t$。
-   如果 $y \lt x$，我们向右移动指针 $j$，直到 $j=m$ 或者 $grains[j] - x \gt t$。

如果 $j=m$，说明所有谷物都被吃完了，返回 `true`，否则返回 `false`。

时间复杂度 $O(n \times \log n + m \times \log m + (m + n) \times \log U)$，空间复杂度 $O(\log m + \log n)$。其中 $n$ 和 $m$ 分别为鸡和谷物的数量，而 $U$ 为所有鸡和谷物的位置的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTime(self, hens: List[int], grains: List[int]) -> int:
        def check(t):
            j = 0
            for x in hens:
                if j == m:
                    return True
                y = grains[j]
                if y <= x:
                    d = x - y
                    if d > t:
                        return False
                    while j < m and grains[j] <= x:
                        j += 1
                    while j < m and min(d, grains[j] - x) + grains[j] - y <= t:
                        j += 1
                else:
                    while j < m and grains[j] - x <= t:
                        j += 1
            return j == m

        hens.sort()
        grains.sort()
        m = len(grains)
        r = abs(hens[0] - grains[0]) + grains[-1] - grains[0] + 1
        return bisect_left(range(r), True, key=check)
```

#### Java

```java
class Solution {
    private int[] hens;
    private int[] grains;
    private int m;

    public int minimumTime(int[] hens, int[] grains) {
        m = grains.length;
        this.hens = hens;
        this.grains = grains;
        Arrays.sort(hens);
        Arrays.sort(grains);
        int l = 0;
        int r = Math.abs(hens[0] - grains[0]) + grains[m - 1] - grains[0];
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

    private boolean check(int t) {
        int j = 0;
        for (int x : hens) {
            if (j == m) {
                return true;
            }
            int y = grains[j];
            if (y <= x) {
                int d = x - y;
                if (d > t) {
                    return false;
                }
                while (j < m && grains[j] <= x) {
                    ++j;
                }
                while (j < m && Math.min(d, grains[j] - x) + grains[j] - y <= t) {
                    ++j;
                }
            } else {
                while (j < m && grains[j] - x <= t) {
                    ++j;
                }
            }
        }
        return j == m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumTime(vector<int>& hens, vector<int>& grains) {
        int m = grains.size();
        sort(hens.begin(), hens.end());
        sort(grains.begin(), grains.end());
        int l = 0;
        int r = abs(hens[0] - grains[0]) + grains[m - 1] - grains[0];
        auto check = [&](int t) -> bool {
            int j = 0;
            for (int x : hens) {
                if (j == m) {
                    return true;
                }
                int y = grains[j];
                if (y <= x) {
                    int d = x - y;
                    if (d > t) {
                        return false;
                    }
                    while (j < m && grains[j] <= x) {
                        ++j;
                    }
                    while (j < m && min(d, grains[j] - x) + grains[j] - y <= t) {
                        ++j;
                    }
                } else {
                    while (j < m && grains[j] - x <= t) {
                        ++j;
                    }
                }
            }
            return j == m;
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
func minimumTime(hens []int, grains []int) int {
	sort.Ints(hens)
	sort.Ints(grains)
	m := len(grains)
	l, r := 0, abs(hens[0]-grains[0])+grains[m-1]-grains[0]
	check := func(t int) bool {
		j := 0
		for _, x := range hens {
			if j == m {
				return true
			}
			y := grains[j]
			if y <= x {
				d := x - y
				if d > t {
					return false
				}
				for j < m && grains[j] <= x {
					j++
				}
				for j < m && min(d, grains[j]-x)+grains[j]-y <= t {
					j++
				}
			} else {
				for j < m && grains[j]-x <= t {
					j++
				}
			}
		}
		return j == m
	}
	for l < r {
		mid := (l + r) >> 1
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minimumTime(hens: number[], grains: number[]): number {
    hens.sort((a, b) => a - b);
    grains.sort((a, b) => a - b);
    const m = grains.length;
    let l = 0;
    let r = Math.abs(hens[0] - grains[0]) + grains[m - 1] - grains[0] + 1;

    const check = (t: number): boolean => {
        let j = 0;
        for (const x of hens) {
            if (j === m) {
                return true;
            }
            const y = grains[j];
            if (y <= x) {
                const d = x - y;
                if (d > t) {
                    return false;
                }
                while (j < m && grains[j] <= x) {
                    ++j;
                }
                while (j < m && Math.min(d, grains[j] - x) + grains[j] - y <= t) {
                    ++j;
                }
            } else {
                while (j < m && grains[j] - x <= t) {
                    ++j;
                }
            }
        }
        return j === m;
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
