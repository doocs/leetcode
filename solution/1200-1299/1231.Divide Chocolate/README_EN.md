# [1231. Divide Chocolate](https://leetcode.com/problems/divide-chocolate)

[中文文档](/solution/1200-1299/1231.Divide%20Chocolate/README.md)

<!-- tags:Array,Binary Search -->

## Description

<p>You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array&nbsp;<code>sweetness</code>.</p>

<p>You want to share the chocolate with your <code>k</code>&nbsp;friends so you start cutting the chocolate bar into <code>k + 1</code>&nbsp;pieces using&nbsp;<code>k</code>&nbsp;cuts, each piece consists of some <strong>consecutive</strong> chunks.</p>

<p>Being generous, you will eat the piece with the <strong>minimum total sweetness</strong> and give the other pieces to your friends.</p>

<p>Find the <strong>maximum total sweetness</strong> of the&nbsp;piece you can get by cutting the chocolate bar optimally.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sweetness = [1,2,3,4,5,6,7,8,9], k = 5
<strong>Output:</strong> 6
<b>Explanation: </b>You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sweetness = [5,6,7,8,9,1,2,3,4], k = 8
<strong>Output:</strong> 1
<b>Explanation: </b>There is only one way to cut the bar into 9 pieces.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sweetness = [1,2,2,1,2,2,1,2,2], k = 2
<strong>Output:</strong> 5
<b>Explanation: </b>You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= k &lt; sweetness.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sweetness[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Binary Search + Greedy

We notice that if we can eat a piece of chocolate with sweetness $x$, then we can also eat all chocolates with sweetness less than or equal to $x$. This shows monotonicity, therefore, we can use binary search to find the maximum $x$ that satisfies the condition.

We define the left boundary of the binary search as $l=0$, and the right boundary as $r=\sum_{i=0}^{n-1} sweetness[i]$. Each time, we take the middle value $mid$ of $l$ and $r$, and then determine whether we can eat a piece of chocolate with sweetness $mid$. If we can, then we try to eat a piece of chocolate with greater sweetness, i.e., let $l=mid$; otherwise, we try to eat a piece of chocolate with smaller sweetness, i.e., let $r=mid-1$. After the binary search ends, we return $l$.

The key to the problem is how to determine whether we can eat a piece of chocolate with sweetness $x$. We can use a greedy approach, traverse the array from left to right, accumulate the current sweetness each time, when the accumulated sweetness is greater than or equal to $x$, the chocolate count $cnt$ is increased by $1$, and the accumulated sweetness is reset to zero. Finally, check whether $cnt$ is greater than $k$.

The time complexity is $O(n \times \log \sum_{i=0}^{n-1} sweetness[i])$, and the space complexity is $O(1)$. Where $n$ is the length of the array.

<!-- tabs:start -->

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

<!-- end -->
