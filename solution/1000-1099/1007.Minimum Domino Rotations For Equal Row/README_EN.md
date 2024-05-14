# [1007. Minimum Domino Rotations For Equal Row](https://leetcode.com/problems/minimum-domino-rotations-for-equal-row)

[中文文档](/solution/1000-1099/1007.Minimum%20Domino%20Rotations%20For%20Equal%20Row/README.md)

<!-- tags:Greedy,Array -->

<!-- difficulty:Medium -->

## Description

<p>In a row of dominoes, <code>tops[i]</code> and <code>bottoms[i]</code> represent the top and bottom halves of the <code>i<sup>th</sup></code> domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)</p>

<p>We may rotate the <code>i<sup>th</sup></code> domino, so that <code>tops[i]</code> and <code>bottoms[i]</code> swap values.</p>

<p>Return the minimum number of rotations so that all the values in <code>tops</code> are the same, or all the values in <code>bottoms</code> are the same.</p>

<p>If it cannot be done, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1007.Minimum%20Domino%20Rotations%20For%20Equal%20Row/images/domino.png" style="height: 300px; width: 421px;" />
<pre>
<strong>Input:</strong> tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 
In this case, it is not possible to rotate the dominoes to make one row of values equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= tops.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>bottoms.length == tops.length</code></li>
	<li><code>1 &lt;= tops[i], bottoms[i] &lt;= 6</code></li>
</ul>

## Solutions

### Solution 1: Greedy

According to the problem description, we know that in order to make all values in $tops$ or all values in $bottoms$ the same, the value must be one of $tops[0]$ or $bottoms[0]$.

Therefore, we design a function $f(x)$ to represent the minimum number of rotations required to make all values equal to $x$. Then the answer is $\min\{f(\textit{tops}[0]), f(\textit{bottoms}[0])\}$.

The calculation method of function $f(x)$ is as follows:

We use two variables $cnt1$ and $cnt2$ to count the number of occurrences of $x$ in $tops$ and $bottoms$, respectively. We subtract the maximum value of $cnt1$ and $cnt2$ from $n$, which is the minimum number of rotations required to make all values equal to $x$. Note that if there are no values equal to $x$ in $tops$ and $bottoms$, the value of $f(x)$ is a very large number, which we represent as $n+1$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minDominoRotations(self, tops: List[int], bottoms: List[int]) -> int:
        def f(x: int) -> int:
            cnt1 = cnt2 = 0
            for a, b in zip(tops, bottoms):
                if x not in (a, b):
                    return inf
                cnt1 += a == x
                cnt2 += b == x
            return len(tops) - max(cnt1, cnt2)

        ans = min(f(tops[0]), f(bottoms[0]))
        return -1 if ans == inf else ans
```

```java
class Solution {
    private int n;
    private int[] tops;
    private int[] bottoms;

    public int minDominoRotations(int[] tops, int[] bottoms) {
        n = tops.length;
        this.tops = tops;
        this.bottoms = bottoms;
        int ans = Math.min(f(tops[0]), f(bottoms[0]));
        return ans > n ? -1 : ans;
    }

    private int f(int x) {
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < n; ++i) {
            if (tops[i] != x && bottoms[i] != x) {
                return n + 1;
            }
            cnt1 += tops[i] == x ? 1 : 0;
            cnt2 += bottoms[i] == x ? 1 : 0;
        }
        return n - Math.max(cnt1, cnt2);
    }
}
```

```cpp
class Solution {
public:
    int minDominoRotations(vector<int>& tops, vector<int>& bottoms) {
        int n = tops.size();
        auto f = [&](int x) {
            int cnt1 = 0, cnt2 = 0;
            for (int i = 0; i < n; ++i) {
                if (tops[i] != x && bottoms[i] != x) {
                    return n + 1;
                }
                cnt1 += tops[i] == x;
                cnt2 += bottoms[i] == x;
            }
            return n - max(cnt1, cnt2);
        };
        int ans = min(f(tops[0]), f(bottoms[0]));
        return ans > n ? -1 : ans;
    }
};
```

```go
func minDominoRotations(tops []int, bottoms []int) int {
	n := len(tops)
	f := func(x int) int {
		cnt1, cnt2 := 0, 0
		for i, a := range tops {
			b := bottoms[i]
			if a != x && b != x {
				return n + 1
			}
			if a == x {
				cnt1++
			}
			if b == x {
				cnt2++
			}
		}
		return n - max(cnt1, cnt2)
	}
	ans := min(f(tops[0]), f(bottoms[0]))
	if ans > n {
		return -1
	}
	return ans
}
```

```ts
function minDominoRotations(tops: number[], bottoms: number[]): number {
    const n = tops.length;
    const f = (x: number): number => {
        let [cnt1, cnt2] = [0, 0];
        for (let i = 0; i < n; ++i) {
            if (tops[i] !== x && bottoms[i] !== x) {
                return n + 1;
            }
            cnt1 += tops[i] === x ? 1 : 0;
            cnt2 += bottoms[i] === x ? 1 : 0;
        }
        return n - Math.max(cnt1, cnt2);
    };
    const ans = Math.min(f(tops[0]), f(bottoms[0]));
    return ans > n ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- end -->
