# [1426. 数元素](https://leetcode.cn/problems/counting-elements)

[English Version](/solution/1400-1499/1426.Counting%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>， 对于元素 <code>x</code> ，只有当 <code>x + 1</code> 也在数组&nbsp;<code>arr</code> 里时，才能记为 <code>1</code> 个数。</p>

<p>如果数组&nbsp;<code>arr</code> 里有重复的数，每个重复的数单独计算。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>1 和 2 被计算次数因为 2 和 3 在数组 arr 里。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,3,3,5,5,7,7]
<strong>输出：</strong>0
<strong>解释：</strong>所有的数都不算, 因为数组里没有 2、4、6、8。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

枚举 `arr` 的每个元素 `x`，判断 `x+1` 是否在 `arr` 中，是则累加答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。

**方法二：哈希表**

将 `arr` 所有元素放入哈希表 `s` 中。然后遍历 `arr` 的每个元素 `x`，判断 `x+1` 是否在 `s` 中，是则累加答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countElements(self, arr: List[int]) -> int:
        return sum(x + 1 in arr for x in arr)
```

```python
class Solution:
    def countElements(self, arr: List[int]) -> int:
        s = set(arr)
        return sum(x + 1 in s for x in arr)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countElements(int[] arr) {
        int ans = 0;
        for (int x : arr) {
            for (int v : arr) {
                if (x + 1 == v) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}
```

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
        int ans = 0;
        for (int x : arr) {
            for (int v : arr) {
                if (x + 1 == v) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countElements(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        int ans = 0;
        for (int x : arr) {
            ans += s.count(x + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func countElements(arr []int) int {
	ans := 0
	for _, x := range arr {
		for _, v := range arr {
			if x+1 == v {
				ans++
				break
			}
		}
	}
	return ans
}
```

```go
func countElements(arr []int) int {
	s := map[int]bool{}
	for _, x := range arr {
		s[x] = true
	}
	ans := 0
	for _, x := range arr {
		if s[x+1] {
			ans++
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var countElements = function (arr) {
    let ans = 0;
    for (const x of arr) {
        ans += arr.includes(x + 1);
    }
    return ans;
};
```

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var countElements = function (arr) {
    const s = new Set();
    for (const x of arr) {
        s.add(x);
    }
    let ans = 0;
    for (const x of arr) {
        if (s.has(x + 1)) {
            ++ans;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
