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

**方法一：双指针**

我们可以使用双指针的方法，维护一个区间 $[l,.. r]$，使得区间内的数之和 $s$ 为 target，如果区间内的数之和小于 target，则右指针 $l$ 右移，如果区间内的数之和大于 target，则左指针 $l$ 右移，直到左指针到达 target 的一半为止。

时间复杂度 $O(target)$，忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findContinuousSequence(self, target: int) -> List[List[int]]:
        l, r = 1, 2
        ans = []
        while l < r:
            s = (l + r) * (r - l + 1) // 2
            if s == target:
                ans.append(list(range(l, r + 1)))
                l += 1
            elif s < target:
                r += 1
            else:
                l += 1
        return ans
```

### **Java**

```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        int l = 1, r = 2;
        List<int[]> ans = new ArrayList<>();
        while (l < r) {
            int s = (l + r) * (r - l + 1) / 2;
            if (s == target) {
                int[] t = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    t[i - l] = i;
                }
                ans.add(t);
                ++l;
            } else if (s < target) {
                ++r;
            } else {
                ++l;
            }
        }
        return ans.toArray(new int[0][]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> findContinuousSequence(int target) {
        vector<vector<int>> ans;
        int l = 1, r = 2;
        while (l < r) {
            int s = (l + r) * (r - l + 1) / 2;
            if (s == target) {
                vector<int> t(r - l + 1);
                iota(t.begin(), t.end(), l);
                ans.emplace_back(t);
                ++l;
            } else if (s < target) {
                ++r;
            } else {
                ++l;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findContinuousSequence(target int) (ans [][]int) {
	l, r := 1, 2
	for l < r {
		s := (l + r) * (r - l + 1) / 2
		if s == target {
			t := make([]int, r-l+1)
			for i := range t {
				t[i] = l + i
			}
			ans = append(ans, t)
			l++
		} else if s < target {
			r++
		} else {
			l++
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number} target
 * @return {number[][]}
 */
var findContinuousSequence = function (target) {
    const ans = [];
    let l = 1;
    let r = 2;
    while (l < r) {
        const s = ((l + r) * (r - l + 1)) >> 1;
        if (s == target) {
            const t = [];
            for (let i = l; i <= r; ++i) {
                t.push(i);
            }
            ans.push(t);
            ++l;
        } else if (s < target) {
            ++r;
        } else {
            ++l;
        }
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public int[][] FindContinuousSequence(int target) {
        List<int[]> ans = new List<int[]>();
        int l = 1, r = 2;
        while (l < r) {
            int s = (l + r) * (r - l + 1) >> 1;
            if (s == target) {
                List<int> t = new List<int>();
                for (int i = l; i <= r; i++) {
                    t.Add(i);
                }
                l += 1;
                ans.Add(t.ToArray());
            } else if (s < target) {
                r += 1;
            } else {
                l += 1;
            }
        }
        return ans.ToArray();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
