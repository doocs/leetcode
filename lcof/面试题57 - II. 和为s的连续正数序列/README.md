# [面试题 57 - II. 和为 s 的连续正数序列](https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/)

## 题目描述

<p>输入一个正整数 <code>target</code> ，输出所有和为 <code>target</code> 的连续正整数序列（至少含有两个数）。</p>

<p>序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>target = 9
<strong>输出：</strong>[[2,3,4],[4,5]]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>target = 15
<strong>输出：</strong>[[1,2,3,4,5],[4,5,6],[7,8]]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10^5</code></li>
</ul>

<p>&nbsp;</p>

**限制：**

-   `1 <= target <= 10^5`

## 解法

双指针：`p = 1`，`q = 2`。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findContinuousSequence(self, target: int) -> List[List[int]]:
        res = []
        p, q = 1, 2
        while p < q:
            s = (p + q) * (q - p + 1) >> 1
            if s == target:
                res.append([i for i in range(p, q + 1)])
                p += 1
            elif s < target:
                q += 1
            else:
                p += 1
        return res
```

### **Java**

```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int p = 1, q = 2;
        while (p < q) {
            int s = (p + q) * (q - p + 1) >> 1;
            if (s == target) {
                int[] t = new int[q - p + 1];
                for (int i = 0; i < t.length; ++i) {
                    t[i] = p + i;
                }
                list.add(t);
                ++p;
            } else if (s < target) {
                ++q;
            } else {
                ++p;
            }
        }
        int[][] res = new int[list.size()][];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} target
 * @return {number[][]}
 */
var findContinuousSequence = function (target) {
    let res = [];
    let window = [];
    let i = 1;
    let sum = 0;
    while (1) {
        if (sum < target) {
            window.push(i);
            sum += i;
            i++;
        } else if (sum > target) {
            let a = window.shift();
            if (window.length < 2) break;
            sum -= a;
        } else {
            res.push([...window]);
            window.push(i);
            sum += i;
            i++;
            if (window.length === 2) break;
        }
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> build(int small, int big) {
        vector<int> ret;
        for (int i = small; i <= big; i++) {
            ret.push_back(i);
        }

        return ret;
    }

    vector<vector<int>> findContinuousSequence(int target) {
        vector<vector<int>> ret;
        int small = 1;
        int big = 2;
        int mid = (target + 1) / 2;
        int curSum = small + big;

        if (target < 3) {
            ret;
        }

        while (small < mid) {
            if (curSum == target) {
                ret.push_back(build(small, big));
            }

            while (curSum > target && small < mid) {
                // 一直减去，减去到比target小停止
                curSum -= small;
                small++;

                if (curSum == target && small < mid) {
                    ret.push_back(build(small, big));
                }
            }

            big++;
            curSum += big;
        }

        return ret;
    }
};
```

### **Go**

```go
func findContinuousSequence(target int) [][]int {
	ans := make([][]int, 0)
	window := 0
	left, right := 1, 1
	for n := target / 2; left <= n; {
		if window < target {
			window += right
			right++
		} else if window > target {
			window -= left
			left++
		} else {
			tmp := make([]int, 0, right-left)
			for i := left; i < right; i++ {
				tmp = append(tmp, i)
			}
			ans = append(ans, tmp)
			window -= left
			left++
		}
	}
	return ans
}
```

### **C#**

```cs
public class Solution {
    public int[][] FindContinuousSequence(int target) {
        List<int[]> res = new List<int[]>();
        int p = 1, q = 2;
        while (p < q) {
            int s = (p + q) * (q - p + 1) >> 1;
            if (s == target) {
                List<int> tmp = new List<int>();
                for (int i = p; i < q + 1; i++) {
                    tmp.Add(i);
                }
                p += 1;
                res.Add(tmp.ToArray());
            } else if (s < target) {
                q += 1;
            } else {
                p += 1;
            }
        }
        return res.ToArray();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
