# [2164. 对奇偶下标分别排序](https://leetcode.cn/problems/sort-even-and-odd-indices-independently)

[English Version](/solution/2100-2199/2164.Sort%20Even%20and%20Odd%20Indices%20Independently/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。根据下述规则重排 <code>nums</code> 中的值：</p>

<ol>
	<li>按 <strong>非递增</strong> 顺序排列 <code>nums</code> <strong>奇数下标</strong> 上的所有值。

    <ul>
    	<li>举个例子，如果排序前 <code>nums = [4,<em><strong>1</strong></em>,2,<em><strong>3</strong></em>]</code> ，对奇数下标的值排序后变为 <code>[4,<em><strong>3</strong></em>,2,<em><strong>1</strong></em>]</code> 。奇数下标 <code>1</code> 和 <code>3</code> 的值按照非递增顺序重排。</li>
    </ul>
    </li>
    <li>按 <strong>非递减</strong> 顺序排列 <code>nums</code> <strong>偶数下标</strong> 上的所有值。
    <ul>
    	<li>举个例子，如果排序前 <code>nums = [<em><strong>4</strong></em>,1,<em><strong>2</strong></em>,3]</code> ，对偶数下标的值排序后变为 <code>[<em><strong>2</strong></em>,1,<em><strong>4</strong></em>,3]</code> 。偶数下标 <code>0</code> 和 <code>2</code> 的值按照非递减顺序重排。</li>
    </ul>
    </li>

</ol>

<p>返回重排 <code>nums</code> 的值之后形成的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,1,2,3]
<strong>输出：</strong>[2,3,4,1]
<strong>解释：</strong>
首先，按非递增顺序重排奇数下标（1 和 3）的值。
所以，nums 从 [4,<em><strong>1</strong></em>,2,<em><strong>3</strong></em>] 变为 [4,<em><strong>3</strong></em>,2,<em><strong>1</strong></em>] 。
然后，按非递减顺序重排偶数下标（0 和 2）的值。
所以，nums 从 [<em><strong>4</strong></em>,1,<em><strong>2</strong></em>,3] 变为 [<em><strong>2</strong></em>,3,<em><strong>4</strong></em>,1] 。
因此，重排之后形成的数组是 [2,3,4,1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1]
<strong>输出：</strong>[2,1]
<strong>解释：</strong>
由于只有一个奇数下标和一个偶数下标，所以不会发生重排。
形成的结果数组是 [2,1] ，和初始数组一样。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortEvenOdd(self, nums: List[int]) -> List[int]:
        a = sorted(nums[::2])
        b = sorted(nums[1::2], reverse=True)
        nums[::2] = a
        nums[1::2] = b
        return nums
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        int[] a = new int[(n + 1) >> 1];
        int[] b = new int[n >> 1];
        for (int i = 0, j = 0; j < n >> 1; i += 2, ++j) {
            a[j] = nums[i];
            b[j] = nums[i + 1];
        }
        if (n % 2 == 1) {
            a[a.length - 1] = nums[n - 1];
        }
        Arrays.sort(a);
        Arrays.sort(b);
        int[] ans = new int[n];
        for (int i = 0, j = 0; j < a.length; i += 2, ++j) {
            ans[i] = a[j];
        }
        for (int i = 1, j = b.length - 1; j >= 0; i += 2, --j) {
            ans[i] = b[j];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sortEvenOdd(vector<int>& nums) {
        int n = nums.size();
        vector<int> a;
        vector<int> b;
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0)
                a.push_back(nums[i]);
            else
                b.push_back(nums[i]);
        }
        sort(a.begin(), a.end());
        sort(b.begin(), b.end(), greater<int>());
        vector<int> ans(n);
        for (int i = 0, j = 0; j < a.size(); i += 2, ++j) ans[i] = a[j];
        for (int i = 1, j = 0; j < b.size(); i += 2, ++j) ans[i] = b[j];
        return ans;
    }
};
```

### **Go**

```go
func sortEvenOdd(nums []int) []int {
	n := len(nums)
	var a []int
	var b []int
	for i, v := range nums {
		if i%2 == 0 {
			a = append(a, v)
		} else {
			b = append(b, v)
		}
	}
	ans := make([]int, n)
	sort.Ints(a)
	sort.Slice(b, func(i, j int) bool {
		return b[i] > b[j]
	})
	for i, j := 0, 0; j < len(a); i, j = i+2, j+1 {
		ans[i] = a[j]
	}
	for i, j := 1, 0; j < len(b); i, j = i+2, j+1 {
		ans[i] = b[j]
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
