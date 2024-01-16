# [面试题 08.13. 堆箱子](https://leetcode.cn/problems/pile-box-lcci)

[English Version](/lcci/08.13.Pile%20Box/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>堆箱子。给你一堆n个箱子，箱子宽 wi、高hi、深di。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。</p>
<p>输入使用数组<code>[wi, di, hi]</code>表示每个箱子。</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
<strong> 输出</strong>：6
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
<strong> 输出</strong>：10
</pre>
<p><strong>提示:</strong></p>
<ol>
	<li>箱子的数目不大于3000个。</li>
</ol>

## 解法

### 方法一：排序 + 动态规划

我们先将箱子按照宽度升序、深度降序的顺序进行排序，然后使用动态规划求解。

定义 $f[i]$ 表示以第 $i$ 个箱子为底部的最大高度。对于 $f[i]$，我们枚举 $j \in [0, i)$，如果 $box[j][1] \lt box[i][1]$ 且 $box[j][2] \lt box[i][2]$，那么我们可以将第 $j$ 个箱子放在第 $i$ 个箱子上面，此时 $f[i] = \max\{f[i], f[j]\}$。最后我们将 $f[i]$ 加上第 $i$ 个箱子的高度，即可得到 $f[i]$ 的最终值。

答案为 $f$ 中的最大值。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是箱子的数目。

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

<!-- tabs:end -->

<!-- end -->
