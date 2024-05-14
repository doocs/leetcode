---
comment: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.13.Pile%20Box/README_EN.md
---

# [08.13. Pile Box](https://leetcode.cn/problems/pile-box-lcci)

[中文文档](/lcci/08.13.Pile%20Box/README.md)

## Description

<p>You have a stack of n boxes, with widths wi, heights hi, and depths di. The boxes cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly larger than the box above it in width, height, and depth. Implement a method to compute the height of the tallest possible stack. The height of a stack is the sum of the heights of each box.</p>
<p>The input use <code>[wi, di, hi]</code>&nbsp;to represents each box.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]

<strong> Output</strong>: 6

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]

<strong> Output</strong>: 10

</pre>
<p><strong>Note:</strong></p>
<ol>
	<li><code>box.length &lt;= 3000</code></li>
</ol>

## Solutions

### Solution 1: Sorting + Dynamic Programming

First, we sort the boxes in ascending order by width and descending order by depth, then use dynamic programming to solve the problem.

We define $f[i]$ as the maximum height with the $i$-th box at the bottom. For $f[i]$, we enumerate $j \in [0, i)$, if $box[j][1] < box[i][1]$ and $box[j][2] < box[i][2]$, then we can put the $j$-th box on top of the $i$-th box, in which case $f[i] = \max\{f[i], f[j]\}$. Finally, we add the height of the $i$-th box to $f[i]$ to get the final value of $f[i]$.

The answer is the maximum value in $f$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the number of boxes.

<!-- tabs:start -->

```python
class Solution:
    def pileBox(self, box: List[List[int]]) -> int:
        box.sort(key=lambda x: (x[0], -x[1]))
        n = len(box)
        f = [0] * n
        for i in range(n):
            for j in range(i):
                if box[j][1] < box[i][1] and box[j][2] < box[i][2]:
                    f[i] = max(f[i], f[j])
            f[i] += box[i][2]
        return max(f)
```

```java
class Solution {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int n = box.length;
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            f[i] += box[i][2];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int pileBox(vector<vector<int>>& box) {
        sort(box.begin(), box.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] < b[0] || (a[0] == b[0] && b[1] < a[1]);
        });
        int n = box.size();
        int f[n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                    f[i] = max(f[i], f[j]);
                }
            }
            f[i] += box[i][2];
        }
        return *max_element(f, f + n);
    }
};
```

```go
func pileBox(box [][]int) int {
	sort.Slice(box, func(i, j int) bool {
		a, b := box[i], box[j]
		return a[0] < b[0] || (a[0] == b[0] && b[1] < a[1])
	})
	n := len(box)
	f := make([]int, n)
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			if box[j][1] < box[i][1] && box[j][2] < box[i][2] {
				f[i] = max(f[i], f[j])
			}
		}
		f[i] += box[i][2]
	}
	return slices.Max(f)
}
```

```ts
function pileBox(box: number[][]): number {
    box.sort((a, b) => (a[0] === b[0] ? b[1] - a[1] : a[0] - b[0]));
    const n = box.length;
    const f: number[] = new Array(n).fill(0);
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                f[i] = Math.max(f[i], f[j]);
            }
        }
        f[i] += box[i][2];
        ans = Math.max(ans, f[i]);
    }
    return ans;
}
```

```swift
class Solution {
    func pileBox(_ box: [[Int]]) -> Int {
        let boxes = box.sorted {
            if $0[0] == $1[0] {
                return $0[1] > $1[1]
            } else {
                return $0[0] < $1[0]
            }
        }

        let n = boxes.count
        var f = Array(repeating: 0, count: n)
        var ans = 0

        for i in 0..<n {
            f[i] = boxes[i][2]
            for j in 0..<i {
                if boxes[j][1] < boxes[i][1] && boxes[j][2] < boxes[i][2] {
                    f[i] = max(f[i], f[j] + boxes[i][2])
                }
            }
            ans = max(ans, f[i])
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
