# [2557. 从一个范围内选择最多整数 II](https://leetcode.cn/problems/maximum-number-of-integers-to-choose-from-a-range-ii)

[English Version](/solution/2500-2599/2557.Maximum%20Number%20of%20Integers%20to%20Choose%20From%20a%20Range%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>banned</code> 和两个整数 <code>n</code> 和 <code>maxSum</code>&nbsp;。你需要按照以下规则选择一些整数：</p>

<ul>
	<li>被选择整数的范围是 <code>[1, n]</code> 。</li>
	<li>每个整数 <strong>至多</strong> 选择 <strong>一次</strong> 。</li>
	<li>被选择整数不能在数组 <code>banned</code> 中。</li>
	<li>被选择整数的和不超过 <code>maxSum</code> 。</li>
</ul>

<p>请你返回按照上述规则 <strong>最多</strong> 可以选择的整数数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>banned = [1,4,6], n = 6, maxSum = 4
<strong>输出：</strong>1
<strong>解释：</strong>你可以选择整数 3 。
3 在范围 [1, 6] 内，且不在 banned 中，所选整数的和为 3 ，也没有超过 maxSum 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>banned = [4,3,5,6], n = 7, maxSum = 18
<strong>输出：</strong>3
<strong>解释：</strong>你可以选择整数 1, 2&nbsp;和 7 。
它们都在范围 [1, 7] 中，且都没出现在 banned 中，所选整数的和为 10 ，没有超过 maxSum 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= banned.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= banned[i] &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= maxSum &lt;= 10<sup>15</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：去重 + 排序 + 二分查找**

我们可以在数组 `banned` 中加入 $0$ 和 $n + 1$，将数组 `banned` 去重并排序。

接下来，我们枚举数组 `banned` 中的每两个相邻元素 $i$ 和 $j$，那么可选的整数范围就是 $[i + 1, j - 1]$。二分枚举我们在此区间内能够选择的元素个数，找到最大的可选元素个数，然后将其加到 $ans$ 中。同时在 `maxSum` 中减去这些元素的和。如果 `maxSum` 小于 $0$，那么我们跳出循环。返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `banned` 的长度。

相似题目：[2557. 从一个范围内选择最多整数 II](/solution/2500-2599/2557.Maximum%20Number%20of%20Integers%20to%20Choose%20From%20a%20Range%20II/README.md)

相似题目：[2556. 从一个范围内选择最多整数 I](/solution/2500-2599/2554.Maximum%20Number%20of%20Integers%20to%20Choose%20From%20a%20Range%20I/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxCount(self, banned: List[int], n: int, maxSum: int) -> int:
        banned.extend([0, n + 1])
        ban = sorted(set(banned))
        ans = 0
        for i, j in pairwise(ban):
            left, right = 0, j - i - 1
            while left < right:
                mid = (left + right + 1) >> 1
                if (i + 1 + i + mid) * mid // 2 <= maxSum:
                    left = mid
                else:
                    right = mid - 1
            ans += left
            maxSum -= (i + 1 + i + left) * left // 2
            if maxSum <= 0:
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxCount(int[] banned, int n, long maxSum) {
        Set<Integer> black = new HashSet<>();
        black.add(0);
        black.add(n + 1);
        for (int x : banned) {
            black.add(x);
        }
        List<Integer> ban = new ArrayList<>(black);
        Collections.sort(ban);
        int ans = 0;
        for (int k = 1; k < ban.size(); ++k) {
            int i = ban.get(k - 1), j = ban.get(k);
            int left = 0, right = j - i - 1;
            while (left < right) {
                int mid = (left + right + 1) >>> 1;
                if ((i + 1 + i + mid) * 1L * mid / 2 <= maxSum) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
            maxSum -= (i + 1 + i + left) * 1L * left / 2;
            if (maxSum <= 0) {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxCount(vector<int>& banned, int n, long long maxSum) {
        banned.push_back(0);
        banned.push_back(n + 1);
        sort(banned.begin(), banned.end());
        banned.erase(unique(banned.begin(), banned.end()), banned.end());
        int ans = 0;
        for (int k = 1; k < banned.size(); ++k) {
            int i = banned[k - 1], j = banned[k];
            int left = 0, right = j - i - 1;
            while (left < right) {
                int mid = left + ((right - left + 1) / 2);
                if ((i + 1 + i + mid) * 1LL * mid / 2 <= maxSum) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
            maxSum -= (i + 1 + i + left) * 1LL * left / 2;
            if (maxSum <= 0) {
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxCount(banned []int, n int, maxSum int64) (ans int) {
	banned = append(banned, []int{0, n + 1}...)
	sort.Ints(banned)
	ban := []int{}
	for i, x := range banned {
		if i > 0 && x == banned[i-1] {
			continue
		}
		ban = append(ban, x)
	}
	for k := 1; k < len(ban); k++ {
		i, j := ban[k-1], ban[k]
		left, right := 0, j-i-1
		for left < right {
			mid := (left + right + 1) >> 1
			if int64((i+1+i+mid)*mid/2) <= maxSum {
				left = mid
			} else {
				right = mid - 1
			}
		}
		ans += left
		maxSum -= int64((i + 1 + i + left) * left / 2)
		if maxSum <= 0 {
			break
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
