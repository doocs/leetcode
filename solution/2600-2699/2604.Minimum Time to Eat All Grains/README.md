# [2604. Minimum Time to Eat All Grains](https://leetcode.cn/problems/minimum-time-to-eat-all-grains)

[English Version](/solution/2600-2699/2604.Minimum%20Time%20to%20Eat%20All%20Grains/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>There are <code>n</code> hens and <code>m</code> grains on a line. You are given the initial positions of the hens and the grains in two integer arrays <code>hens</code> and <code>grains</code> of size <code>n</code> and <code>m</code> respectively.</p>

<p>Any hen can eat a grain if they are on the same position. The time taken for this is negligible. One hen can also eat multiple grains.</p>

<p>In <code>1</code> second, a hen can move right or left by <code>1</code> unit. The hens can move simultaneously and independently of each other.</p>

<p>Return <em>the <strong>minimum</strong> time to eat all grains if the hens act optimally.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> hens = [3,6,7], grains = [2,4,7,9]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
One of the ways hens eat all grains in 2 seconds is described below:
- The first hen eats the grain at position 2 in 1 second. 
- The second hen eats the grain at position 4 in 2 seconds. 
- The third hen eats the grains at positions 7 and 9 in 2 seconds. 
So, the maximum time needed is 2.
It can be proven that the hens cannot eat all grains before 2 seconds.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> hens = [4,6,109,111,213,215], grains = [5,110,214]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
One of the ways hens eat all grains in 1 second is described below:
- The first hen eats the grain at position 5 in 1 second. 
- The fourth hen eats the grain at position 110 in 1 second.
- The sixth hen eats the grain at position 214 in 1 second. 
- The other hens do not move. 
So, the maximum time needed is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hens.length, grains.length &lt;= 2*10<sup>4</sup></code></li>
	<li><code>0 &lt;= hens[i], grains[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

我们先将鸡和谷物按照位置从小到大排序，接下来二分枚举时间 $t$，找到一个最小的 $t$ 使得所有谷物能在 $t$ 秒内被吃完。

对于每个鸡，我们用指针 $j$ 指向当前还未被吃的谷物中最左边的谷物，记当前鸡的位置为 $x$，谷物的位置为 $y$，则有以下几种情况：

-   如果 $y \leq x$，我们记 $d = x - y$，如果 $d \gt t$，那么当前谷物无法被吃掉，直接返回 `false`。否则，我们向右移动指针 $j$，直到 $j=m$ 或者 $grains[j] \gt x$。此时我们需要判断鸡是否能吃到 $j$ 指向的谷物，如果能吃到，我们继续向右移动指针 $j$，直到 $j=m$ 或者 $min(d, grains[j] - x) + grains[j] - y \gt t$。
-   如果 $y \lt x$，我们向右移动指针 $j$，直到 $j=m$ 或者 $grains[j] - x \gt t$。

如果 $j=m$，说明所有谷物都被吃完了，返回 `true`，否则返回 `false`。

时间复杂度 $O(n \times \log n + m \times \log m + (m + n) \times \log U)$，空间复杂度 $O(\log m + \log n)$。其中 $n$ 和 $m$ 分别为鸡和谷物的数量，而 $U$ 为所有鸡和谷物的位置的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

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
                while (
                    j < m &&
                    Math.min(d, grains[j] - x) + grains[j] - y <= t
                ) {
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

### **...**

```

```

<!-- tabs:end -->
