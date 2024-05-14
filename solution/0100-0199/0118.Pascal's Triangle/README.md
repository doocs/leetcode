# [118. 杨辉三角](https://leetcode.cn/problems/pascals-triangle)

[English Version](/solution/0100-0199/0118.Pascal%27s%20Triangle/README_EN.md)

<!-- tags:数组,动态规划 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数 <em><code>numRows</code>，</em>生成「杨辉三角」的前 <em><code>numRows</code> </em>行。</p>

<p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0118.Pascal%27s%20Triangle/images/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> numRows = 5
<strong>输出:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> numRows = 1
<strong>输出:</strong> [[1]]
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 <= numRows <= 30</code></li>
</ul>

## 解法

### 方法一：模拟

我们先创建一个答案数组 $f$，然后将 $f$ 的第一行元素设为 $[1]$。接下来，我们从第二行开始，每一行的开头和结尾元素都是 $1$，其它 $f[i][j] = f[i - 1][j - 1] + f[i - 1][j]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是行数。

<!-- tabs:start -->

```python
class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        f = [[1]]
        for i in range(numRows - 1):
            g = [1] + [a + b for a, b in pairwise(f[-1])] + [1]
            f.append(g)
        return f
```

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> f = new ArrayList<>();
        f.add(List.of(1));
        for (int i = 0; i < numRows - 1; ++i) {
            List<Integer> g = new ArrayList<>();
            g.add(1);
            for (int j = 0; j < f.get(i).size() - 1; ++j) {
                g.add(f.get(i).get(j) + f.get(i).get(j + 1));
            }
            g.add(1);
            f.add(g);
        }
        return f;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> f;
        f.push_back(vector<int>(1, 1));
        for (int i = 0; i < numRows - 1; ++i) {
            vector<int> g;
            g.push_back(1);
            for (int j = 0; j < f[i].size() - 1; ++j) {
                g.push_back(f[i][j] + f[i][j + 1]);
            }
            g.push_back(1);
            f.push_back(g);
        }
        return f;
    }
};
```

```go
func generate(numRows int) [][]int {
	f := [][]int{[]int{1}}
	for i := 0; i < numRows-1; i++ {
		g := []int{1}
		for j := 0; j < len(f[i])-1; j++ {
			g = append(g, f[i][j]+f[i][j+1])
		}
		g = append(g, 1)
		f = append(f, g)
	}
	return f
}
```

```ts
function generate(numRows: number): number[][] {
    const f: number[][] = [[1]];
    for (let i = 0; i < numRows - 1; ++i) {
        const g: number[] = [1];
        for (let j = 0; j < f[i].length - 1; ++j) {
            g.push(f[i][j] + f[i][j + 1]);
        }
        g.push(1);
        f.push(g);
    }
    return f;
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn generate(num_rows: i32) -> Vec<Vec<i32>> {
        let mut ret_vec: Vec<Vec<i32>> = Vec::new();
        let mut cur_vec: Vec<i32> = Vec::new();

        for i in 0..num_rows as usize {
            let mut new_vec: Vec<i32> = vec![1; i + 1];
            for j in 1..i {
                new_vec[j] = cur_vec[j - 1] + cur_vec[j];
            }
            cur_vec = new_vec.clone();
            ret_vec.push(new_vec);
        }

        ret_vec
    }
}
```

```js
/**
 * @param {number} numRows
 * @return {number[][]}
 */
var generate = function (numRows) {
    const f = [[1]];
    for (let i = 0; i < numRows - 1; ++i) {
        const g = [1];
        for (let j = 0; j < f[i].length - 1; ++j) {
            g.push(f[i][j] + f[i][j + 1]);
        }
        g.push(1);
        f.push(g);
    }
    return f;
};
```

<!-- tabs:end -->

<!-- end -->
