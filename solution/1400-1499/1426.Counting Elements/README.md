# [1426. 数元素](https://leetcode-cn.com/problems/counting-elements)

[English Version](/solution/1400-1499/1426.Counting%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>， 对于元素 <code>x</code> ，只有当 <code>x + 1</code> 也在数组&nbsp;<code>arr</code> 里时，才能记为 <code>1</code> 个数。</p>

<p>如果数组&nbsp;<code>arr</code> 里有重复的数，每个重复的数单独计算。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>1 和 2 被计算次数因为 2 和 3 在数组 arr 里。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,1,3,3,5,5,7,7]
<strong>输出：</strong>0
<strong>解释：</strong>所有的数都不算, 因为数组里没有 2、4、6、8。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,3,2,3,5,0]
<strong>输出：</strong>3
<strong>解释：</strong>0、1、2 被计算了因为 1、2、3 在数组里。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [1,1,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>两个 1 被计算了因为有 2 在数组里。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countElements(self, arr: List[int]) -> int:
        s = set(arr)
        res = 0
        for num in arr:
            if num + 1 in s:
                res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countElements(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int num : arr) {
            s.add(num);
        }
        int res = 0;
        for (int num : arr) {
            if (s.contains(num + 1)) {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countElements(vector<int>& arr) {
        unordered_set<int> s;
        for (int num : arr) s.insert(num);
        int res = 0;
        for (int num : arr)
            if (s.count(num + 1)) ++res;
        return res;
    }
};
```

### **Go**

```go
func countElements(arr []int) int {
	s := make(map[int]bool)
	for _, num := range arr {
		s[num] = true
	}
	res := 0
	for _, num := range arr {
		if s[num+1] {
			res++
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
