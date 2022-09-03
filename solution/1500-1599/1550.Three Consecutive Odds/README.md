# [1550. 存在连续三个奇数的数组](https://leetcode.cn/problems/three-consecutive-odds)

[English Version](/solution/1500-1599/1550.Three%20Consecutive%20Odds/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code>，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,6,4,1]
<strong>输出：</strong>false
<strong>解释：</strong>不存在连续三个元素都是奇数的情况。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,34,3,4,5,7,23,12]
<strong>输出：</strong>true
<strong>解释：</strong>存在连续三个元素都是奇数的情况，即 [5,7,23] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历数组**

直接遍历数组，统计连续奇数的个数，如果个数达到 3，则返回 `true`，否则遍历结束，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$，其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        cnt = 0
        for v in arr:
            if v & 1:
                cnt += 1
            else:
                cnt = 0
            if cnt == 3:
                return True
        return False
```

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        for i in range(len(arr) - 2):
            if arr[i] % 2 + arr[i + 1] % 2 + arr[i + 2] % 2 == 3:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v % 2 == 1) {
                ++cnt;
            } else {
                cnt = 0;
            }
            if (cnt == 3) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool threeConsecutiveOdds(vector<int>& arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v & 1) ++cnt;
            else cnt = 0;
            if (cnt == 3) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func threeConsecutiveOdds(arr []int) bool {
	cnt := 0
	for _, v := range arr {
		if v%2 == 1 {
			cnt++
		} else {
			cnt = 0
		}
		if cnt == 3 {
			return true
		}
	}
	return false
}
```

### **TypeScript**

```ts
function threeConsecutiveOdds(arr: number[]): boolean {
    let cnt = 0;
    for (const v of arr) {
        if (v & 1) {
            ++cnt;
        } else {
            cnt = 0;
        }
        if (cnt == 3) {
            return true;
        }
    }
    return false;
}
```

### **...**

```

```

<!-- tabs:end -->
