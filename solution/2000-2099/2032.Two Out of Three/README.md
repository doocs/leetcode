# [2032. 至少在两个数组中出现的值](https://leetcode.cn/problems/two-out-of-three)

[English Version](/solution/2000-2099/2032.Two%20Out%20of%20Three/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你三个整数数组 <code>nums1</code>、<code>nums2</code> 和 <code>nums3</code> ，请你构造并返回一个 <strong>元素各不相同的</strong> 数组，且由 <strong>至少</strong> 在 <strong>两个</strong> 数组中出现的所有值组成<em>。</em>数组中的元素可以按 <strong>任意</strong> 顺序排列。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
<strong>输出：</strong>[3,2]
<strong>解释：</strong>至少在两个数组中出现的所有值为：
- 3 ，在全部三个数组中都出现过。
- 2 ，在数组 nums1 和 nums2 中出现过。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
<strong>输出：</strong>[2,3,1]
<strong>解释：</strong>至少在两个数组中出现的所有值为：
- 2 ，在数组 nums2 和 nums3 中出现过。
- 3 ，在数组 nums1 和 nums2 中出现过。
- 1 ，在数组 nums1 和 nums3 中出现过。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
<strong>输出：</strong>[]
<strong>解释：</strong>不存在至少在两个数组中出现的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length, nums3.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i], nums2[j], nums3[k] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoOutOfThree(
        self, nums1: List[int], nums2: List[int], nums3: List[int]
    ) -> List[int]:
        s1, s2, s3 = set(nums1), set(nums2), set(nums3)
        ans = []
        for i in range(1, 101):
            a, b, c = i in s1, i in s2, i in s3
            if a + b + c > 1:
                ans.append(i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 100; ++i) {
            if (s1[i] + s2[i] + s3[i] > 1) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int[] get(int[] nums) {
        int[] s = new int[101];
        for (int num : nums) {
            s[num] = 1;
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        auto s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        vector<int> ans;
        for (int i = 1; i <= 100; ++i)
            if (s1[i] + s2[i] + s3[i] > 1)
                ans.push_back(i);
        return ans;
    }

    vector<int> get(vector<int>& nums) {
        vector<int> s(101);
        for (int num : nums) s[num] = 1;
        return s;
    }
};
```

### **Go**

```go
func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) []int {
	s1, s2, s3 := get(nums1), get(nums2), get(nums3)
	var ans []int
	for i := 1; i <= 100; i++ {
		a, b, c := 0, 0, 0
		if s1[i] {
			a++
		}
		if s2[i] {
			b++
		}
		if s3[i] {
			c++
		}
		if a+b+c > 1 {
			ans = append(ans, i)
		}
	}
	return ans
}

func get(nums []int) map[int]bool {
	s := make(map[int]bool, 101)
	for _, num := range nums {
		s[num] = true
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
