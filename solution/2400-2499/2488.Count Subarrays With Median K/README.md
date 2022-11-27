# [2488. 统计中位数为 K 的子数组](https://leetcode.cn/problems/count-subarrays-with-median-k)

[English Version](/solution/2400-2499/2488.Count%20Subarrays%20With%20Median%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的数组 <code>nums</code> ，该数组由从 <code>1</code> 到 <code>n</code> 的 <strong>不同</strong> 整数组成。另给你一个正整数 <code>k</code> 。</p>

<p>统计并返回 <code>num</code> 中的 <strong>中位数</strong> 等于 <code>k</code> 的非空子数组的数目。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>数组的中位数是按 <strong>递增</strong> 顺序排列后位于 <strong>中间</strong> 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 <strong>左</strong> 的那个元素。

    <ul>
    	<li>例如，<code>[2,3,1,4]</code> 的中位数是 <code>2</code> ，<code>[8,4,3,5,1]</code> 的中位数是 <code>4</code> 。</li>
    </ul>
    </li>
    <li>子数组是数组中的一个连续部分。</li>

</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,2,1,4,5], k = 4
<strong>输出：</strong>3
<strong>解释：</strong>中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [2,3,1], k = 3
<strong>输出：</strong>1
<strong>解释：</strong>[3] 是唯一一个中位数等于 3 的子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= n</code></li>
	<li><code>nums</code> 中的整数互不相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：中心扩展**

我们先找到数字 $k$ 所在的位置 $i$，然后从 $i$ 向两边扩展，统计以 $i$ 为中心的子数组个数。

扩展的过程中维护两个变量 $mi$ 和 $mx$，分别表示比 $k$ 小的数字个数和比 $k$ 大的数字个数。

先从 $i+1$ 向右扩展，若出现 $0 \leq mx-mi \leq 1$，此时右侧数组的中位数为 $k$，答案加 $1$。然后我们将 $mx-mi$ 的出现次数存放在数组或哈希表中，以便后续查询。

接着从 $i-1$ 向左扩展，若出现 $0 \leq mx-mi \leq 1$，此时左侧数组的中位数为 $k$，答案加 $1$。然后我们查询数组或哈希表中 $mi-mx$ 以及 $mi-mx+1$ 的出现次数，将其加到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        i = next(i for i, v in enumerate(nums) if v == k)
        n = len(nums)
        ans = 1
        d = defaultdict(int)
        mi = mx = 0
        for j in range(i + 1, n):
            if nums[j] < k:
                mi += 1
            else:
                mx += 1
            if 0 <= mx - mi <= 1:
                ans += 1
            d[mx - mi] += 1
        mi = mx = 0
        for j in range(i - 1, -1, -1):
            if nums[j] < k:
                mi += 1
            else:
                mx += 1
            if 0 <= mx - mi <= 1:
                ans += 1
            ans += d[mi - mx] + d[mi - mx + 1]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        for (int j = 0; j < n; ++j) {
            if (nums[j] == k) {
                i = j;
                break;
            }
        }
        int ans = 1;
        int[] d = new int[n << 1 | 1];
        int mi = 0, mx = 0;
        for (int j = i + 1; j < n; ++j) {
            if (nums[j] < k) {
                ++mi;
            } else {
                ++mx;
            }
            if (mx - mi >= 0 && mx - mi <= 1) {
                ++ans;
            }
            ++d[mx - mi + n];
        }
        mi = 0;
        mx = 0;
        for (int j = i - 1; j >= 0; --j) {
            if (nums[j] < k) {
                ++mi;
            } else {
                ++mx;
            }
            if (mx - mi >= 0 && mx - mi <= 1) {
                ++ans;
            }
            ans += d[mi - mx + n] + d[mi - mx + 1 + n];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int i = 0;
        for (int j = 0; j < n; ++j) {
            if (nums[j] == k) {
                i = j;
                break;
            }
        }
        int ans = 1;
        int d[n << 1 | 1];
        memset(d, 0, sizeof d);
        int mi = 0, mx = 0;
        for (int j = i + 1; j < n; ++j) {
            if (nums[j] < k) ++mi;
            else ++mx;
            if (mx - mi >= 0 && mx - mi <= 1) ++ans;
            ++d[mx - mi + n];
        }
        mi = 0, mx = 0;
        for (int j = i - 1; ~j; --j) {
            if (nums[j] < k) ++mi;
            else ++mx;
            if (mx - mi >= 0 && mx - mi <= 1) ++ans;
            ans += d[mi - mx + n] + d[mi - mx + n + 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubarrays(nums []int, k int) int {
	n := len(nums)
	var i int
	for j, v := range nums {
		if v == k {
			i = j
			break
		}
	}
	ans := 1
	d := make([]int, n<<1|1)
	mi, mx := 0, 0
	for j := i + 1; j < n; j++ {
		if nums[j] < k {
			mi++
		} else {
			mx++
		}
		if mx-mi >= 0 && mx-mi <= 1 {
			ans++
		}
		d[mx-mi+n]++
	}
	mi, mx = 0, 0
	for j := i - 1; j >= 0; j-- {
		if nums[j] < k {
			mi++
		} else {
			mx++
		}
		if mx-mi >= 0 && mx-mi <= 1 {
			ans++
		}
		ans += d[mi-mx+n] + d[mi-mx+n+1]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
