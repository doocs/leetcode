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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minWastedSpace(self, packages: List[int], boxes: List[List[int]]) -> int:
        packages.sort()
        res = inf
        for box in boxes:
            box.sort()
            if packages[-1] > box[-1]:
                continue
            t = last = 0
            for b in box:
                idx = bisect_right(packages, b, lo=last)
                t += (idx - last) * b
                last = idx
            res = min(res, t)
        return -1 if res == inf else (res - sum(packages)) % (10**9 + 7)
```

### **Java**

```java
class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        Arrays.sort(packages);
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + packages[i];
        }

        long res = Long.MAX_VALUE;
        for (int[] box : boxes) {
            Arrays.sort(box);
            if (packages[n - 1] > box[box.length - 1]) {
                continue;
            }
            long t = 0;
            int low = 0;
            for (int b : box) {
                int idx = searchRight(packages, b, low);
                t += ((idx - low) * (long) b - (preSum[idx] - preSum[low]));
                low = idx;
            }
            res = Math.min(res, t);
        }
        return res == Long.MAX_VALUE ? -1 : (int) (res % 1000000007);
    }

    private int searchRight(int[] packages, int target, int low) {
        int high = packages.length;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (packages[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
```

### **TypeScript**

```ts
function minWastedSpace(packages: number[], boxes: number[][]): number {
    const MOD = 10 ** 9 + 7;
    packages.sort((a, b) => a - b);
    const max_package = packages[packages.length - 1];
    const total = packages.reduce((a, c) => a + c, 0);
    let res = Infinity;
    for (let box of boxes) {
        box.sort((a, b) => a - b);
        if (max_package > box[box.length - 1]) continue;
        let left = 0,
            sum = 0;
        for (let capacity of box) {
            let right = searchRight(packages, capacity, left);
            sum += (right - left) * capacity;
            left = right;
        }
        res = Math.min(res, sum);
    }
    return res == Infinity ? -1 : (res - total) % MOD;
}

function searchRight(packages: number[], target: number, left: number): number {
    let right = packages.length;
    while (left < right) {
        let mid = (left + right) >> 1;
        if (packages[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
