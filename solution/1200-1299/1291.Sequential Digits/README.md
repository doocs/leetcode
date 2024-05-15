---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1291.Sequential%20Digits/README.md
rating: 1373
tags:
    - 枚举
---

# [1291. 顺次数](https://leetcode.cn/problems/sequential-digits)

[English Version](/solution/1200-1299/1291.Sequential%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 <code>1</code> 的整数。</p>

<p>请你返回由&nbsp;<code>[low, high]</code>&nbsp;范围内所有顺次数组成的 <strong>有序</strong> 列表（从小到大排序）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输出：</strong>low = 100, high = 300
<strong>输出：</strong>[123,234]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输出：</strong>low = 1000, high = 13000
<strong>输出：</strong>[1234,2345,3456,4567,5678,6789,12345]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>10 &lt;= low &lt;= high &lt;= 10^9</code></li>
</ul>

## 解法

### 方法一：枚举

我们可以枚举数字的第一位 $i$，然后枚举数字的最后一位 $j$，那么这个数字就是 $i,i+1,\cdots,j$ 这 $j-i+1$ 个数字组成的。我们可以通过不断地将数字乘以 $10$ 并加上下一个数字 $j+1$ 来得到下一个数字，如果数字在 $[low, high]$ 的范围内，我们就将它加入答案中。

枚举结束后，我们将答案数组排序并返回即可。

时间复杂度近似 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sequentialDigits(self, low: int, high: int) -> List[int]:
        ans = []
        for i in range(1, 9):
            x = i
            for j in range(i + 1, 10):
                x = x * 10 + j
                if low <= x <= high:
                    ans.append(x)
        return sorted(ans)
```

```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 9; ++i) {
            int x = i;
            for (int j = i + 1; j < 10; ++j) {
                x = x * 10 + j;
                if (x >= low && x <= high) {
                    ans.add(x);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> sequentialDigits(int low, int high) {
        vector<int> ans;
        for (int i = 1; i < 9; ++i) {
            int x = i;
            for (int j = i + 1; j < 10; ++j) {
                x = x * 10 + j;
                if (x >= low && x <= high) {
                    ans.push_back(x);
                }
            }
        }
        sort(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func sequentialDigits(low int, high int) (ans []int) {
	for i := 1; i < 9; i++ {
		x := i
		for j := i + 1; j < 10; j++ {
			x = x*10 + j
			if low <= x && x <= high {
				ans = append(ans, x)
			}
		}
	}
	sort.Ints(ans)
	return
}
```

```ts
function sequentialDigits(low: number, high: number): number[] {
    const ans: number[] = [];
    for (let i = 1; i < 9; ++i) {
        let x = i;
        for (let j = i + 1; j < 10; ++j) {
            x = x * 10 + j;
            if (x >= low && x <= high) {
                ans.push(x);
            }
        }
    }
    ans.sort((a, b) => a - b);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
