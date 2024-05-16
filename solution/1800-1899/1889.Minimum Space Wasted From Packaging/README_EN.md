---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1889.Minimum%20Space%20Wasted%20From%20Packaging/README_EN.md
rating: 2214
source: Weekly Contest 244 Q4
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sorting
---

# [1889. Minimum Space Wasted From Packaging](https://leetcode.com/problems/minimum-space-wasted-from-packaging)

[中文文档](/solution/1800-1899/1889.Minimum%20Space%20Wasted%20From%20Packaging/README.md)

## Description

<p>You have <code>n</code> packages that you are trying to place in boxes, <strong>one package in each box</strong>. There are <code>m</code> suppliers that each produce boxes of <strong>different sizes</strong> (with infinite supply). A package can be placed in a box if the size of the package is <strong>less than or equal to</strong> the size of the box.</p>

<p>The package sizes are given as an integer array <code>packages</code>, where <code>packages[i]</code> is the <strong>size</strong> of the <code>i<sup>th</sup></code> package. The suppliers are given as a 2D integer array <code>boxes</code>, where <code>boxes[j]</code> is an array of <strong>box sizes</strong> that the <code>j<sup>th</sup></code> supplier produces.</p>

<p>You want to choose a <strong>single supplier</strong> and use boxes from them such that the <strong>total wasted space </strong>is <strong>minimized</strong>. For each package in a box, we define the space <strong>wasted</strong> to be <code>size of the box - size of the package</code>. The <strong>total wasted space</strong> is the sum of the space wasted in <strong>all</strong> the boxes.</p>

<ul>
	<li>For example, if you have to fit packages with sizes <code>[2,3,5]</code> and the supplier offers boxes of sizes <code>[4,8]</code>, you can fit the packages of size-<code>2</code> and size-<code>3</code> into two boxes of size-<code>4</code> and the package with size-<code>5</code> into a box of size-<code>8</code>. This would result in a waste of <code>(4-2) + (4-3) + (8-5) = 6</code>.</li>
</ul>

<p>Return <em>the <strong>minimum total wasted space</strong> by choosing the box supplier <strong>optimally</strong>, or </em><code>-1</code> <i>if it is <strong>impossible</strong> to fit all the packages inside boxes. </i>Since the answer may be <strong>large</strong>, return it <strong>modulo </strong><code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> packages = [2,3,5], boxes = [[4,8],[2,8]]
<strong>Output:</strong> 6
<strong>Explanation</strong>: It is optimal to choose the first supplier, using two size-4 boxes and one size-8 box.
The total waste is (4-2) + (4-3) + (8-5) = 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> packages = [2,3,5], boxes = [[1,4],[2,3],[3,4]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no box that the package of size 5 can fit in.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> packages = [3,5,8,10,11,12], boxes = [[12],[11,9],[10,5,14]]
<strong>Output:</strong> 9
<strong>Explanation:</strong> It is optimal to choose the third supplier, using two size-5 boxes, two size-10 boxes, and two size-14 boxes.
The total waste is (5-3) + (5-5) + (10-8) + (10-10) + (14-11) + (14-12) = 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == packages.length</code></li>
	<li><code>m == boxes.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= packages[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= boxes[j].length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= boxes[j][k] &lt;= 10<sup>5</sup></code></li>
	<li><code>sum(boxes[j].length) &lt;= 10<sup>5</sup></code></li>
	<li>The elements in <code>boxes[j]</code> are <strong>distinct</strong>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minWastedSpace(self, packages: List[int], boxes: List[List[int]]) -> int:
        mod = 10**9 + 7
        ans = inf
        packages.sort()
        for box in boxes:
            box.sort()
            if packages[-1] > box[-1]:
                continue
            s = i = 0
            for b in box:
                j = bisect_right(packages, b, lo=i)
                s += (j - i) * b
                i = j
            ans = min(ans, s)
        if ans == inf:
            return -1
        return (ans - sum(packages)) % mod
```

```java
class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        final long inf = 1L << 62;
        Arrays.sort(packages);
        long ans = inf;
        for (var box : boxes) {
            Arrays.sort(box);
            if (packages[n - 1] > box[box.length - 1]) {
                continue;
            }
            long s = 0;
            int i = 0;
            for (int b : box) {
                int j = search(packages, b, i);
                s += 1L * (j - i) * b;
                i = j;
            }
            ans = Math.min(ans, s);
        }
        if (ans == inf) {
            return -1;
        }
        long s = 0;
        for (int p : packages) {
            s += p;
        }
        final int mod = (int) 1e9 + 7;
        return (int) ((ans - s) % mod);
    }

    private int search(int[] nums, int x, int l) {
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int minWastedSpace(vector<int>& packages, vector<vector<int>>& boxes) {
        int n = packages.size(), m = boxes.size();
        sort(packages.begin(), packages.end());
        const int mod = 1e9 + 7;
        const long long inf = 1LL << 62;
        long long ans = inf;
        for (auto& box : boxes) {
            sort(box.begin(), box.end());
            if (packages.back() > box.back()) {
                continue;
            }
            int i = 0;
            long long s = 0;
            for (auto& b : box) {
                int j = upper_bound(packages.begin() + i, packages.end(), b) - packages.begin();
                s += 1LL * (j - i) * b;
                i = j;
            }
            ans = min(ans, s);
        }
        return ans == inf ? -1 : (ans - accumulate(packages.begin(), packages.end(), 0LL)) % mod;
    }
};
```

```go
func minWastedSpace(packages []int, boxes [][]int) int {
	n := len(packages)
	inf := 1 << 62
	sort.Ints(packages)
	ans := inf
	for _, box := range boxes {
		sort.Ints(box)
		if packages[n-1] > box[len(box)-1] {
			continue
		}
		s, i := 0, 0
		for _, b := range box {
			j := sort.SearchInts(packages[i:], b+1) + i
			s += (j - i) * b
			i = j
		}
		ans = min(ans, s)
	}
	if ans == inf {
		return -1
	}
	s := 0
	for _, p := range packages {
		s += p
	}
	const mod = 1e9 + 7
	return (ans - s) % mod
}
```

```ts
function minWastedSpace(packages: number[], boxes: number[][]): number {
    const n = packages.length;
    const inf = Infinity;
    packages.sort((a, b) => a - b);
    let ans = inf;
    for (const box of boxes) {
        box.sort((a, b) => a - b);
        if (packages[n - 1] > box[box.length - 1]) {
            continue;
        }
        let s = 0;
        let i = 0;
        for (const b of box) {
            const j = search(packages, b, i);
            s += (j - i) * b;
            i = j;
        }
        ans = Math.min(ans, s);
    }
    if (ans === inf) {
        return -1;
    }
    const s = packages.reduce((a, b) => a + b, 0);
    return (ans - s) % 1000000007;
}

function search(nums: number[], x: number, l: number): number {
    let r = nums.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- end -->
