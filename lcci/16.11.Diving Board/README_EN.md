# [16.11. Diving Board](https://leetcode.cn/problems/diving-board-lcci)

[中文文档](/lcci/16.11.Diving%20Board/README.md)

## Description

<p>You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of planks, one of length <code>shorter</code> and one of length <code>longer</code>. You must use exactly <code>K</code> planks of wood. Write a method to generate all possible lengths for the diving board.</p>

<p>return all lengths in non-decreasing order.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>

shorter = 1

longer = 2

k = 3

<strong>Output: </strong> {3,4,5,6}

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li>0 &lt; shorter &lt;= longer</li>
	<li>0 &lt;= k &lt;= 100000</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def divingBoard(self, shorter: int, longer: int, k: int) -> List[int]:
        if k == 0:
            return []
        if longer == shorter:
            return [longer * k]
        ans = []
        for i in range(k + 1):
            ans.append(longer * i + shorter * (k - i))
        return ans
```

### **Java**

```java
class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (longer == shorter) {
            return new int[]{longer * k};
        }
        int[] ans = new int[k + 1];
        for (int i = 0; i < k + 1; ++i) {
            ans[i] = longer * i + shorter * (k - i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> divingBoard(int shorter, int longer, int k) {
        if (k == 0) return {};
        if (longer == shorter) return {longer * k};
        vector<int> ans;
        for (int i = 0; i < k + 1; ++i)
            ans.push_back(longer * i + shorter * (k - i));
        return ans;
    }
};
```

### **Go**

```go
func divingBoard(shorter int, longer int, k int) []int {
	if k == 0 {
		return []int{}
	}
	if longer == shorter {
		return []int{longer * k}
	}
	var ans []int
	for i := 0; i < k+1; i++ {
		ans = append(ans, longer*i+shorter*(k-i))
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
