# [2171. Removing Minimum Number of Magic Beans](https://leetcode.com/problems/removing-minimum-number-of-magic-beans)

[中文文档](/solution/2100-2199/2171.Removing%20Minimum%20Number%20of%20Magic%20Beans/README.md)

## Description

<p>You are given an array of <strong>positive</strong> integers <code>beans</code>, where each integer represents the number of magic beans found in a particular magic bag.</p>

<p><strong>Remove</strong> any number of beans (<strong>possibly none</strong>) from each bag such that the number of beans in each remaining <strong>non-empty</strong> bag (still containing <strong>at least one</strong> bean) is <strong>equal</strong>. Once a bean has been removed from a bag, you are <strong>not</strong> allowed to return it to any of the bags.</p>

<p>Return <em>the <strong>minimum</strong> number of magic beans that you have to remove</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> beans = [4,<u><strong>1</strong></u>,6,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
- We remove 1 bean from the bag with only 1 bean.
  This results in the remaining bags: [4,<b><u>0</u></b>,6,5]
- Then we remove 2 beans from the bag with 6 beans.
  This results in the remaining bags: [4,0,<strong><u>4</u></strong>,5]
- Then we remove 1 bean from the bag with 5 beans.
  This results in the remaining bags: [4,0,4,<b><u>4</u></b>]
We removed a total of 1 + 2 + 1 = 4 beans to make the remaining non-empty bags have an equal number of beans.
There are no other solutions that remove 4 beans or fewer.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> beans = [<strong><u>2</u></strong>,10,<u><strong>3</strong></u>,<strong><u>2</u></strong>]
<strong>Output:</strong> 7
<strong>Explanation:</strong>
- We remove 2 beans from one of the bags with 2 beans.
  This results in the remaining bags: [<u><strong>0</strong></u>,10,3,2]
- Then we remove 2 beans from the other bag with 2 beans.
  This results in the remaining bags: [0,10,3,<u><strong>0</strong></u>]
- Then we remove 3 beans from the bag with 3 beans. 
  This results in the remaining bags: [0,10,<u><strong>0</strong></u>,0]
We removed a total of 2 + 2 + 3 = 7 beans to make the remaining non-empty bags have an equal number of beans.
There are no other solutions that removes 7 beans or fewer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= beans.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= beans[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumRemoval(self, beans: List[int]) -> int:
        beans.sort()
        ans = s = sum(beans)
        n = len(beans)
        for i, v in enumerate(beans):
            ans = min(ans, s - v * (n - i))
        return ans
```

### **Java**

```java
class Solution {
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        long s = 0;
        for (int v : beans) {
            s += v;
        }
        long ans = s;
        int n = beans.length;
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, s - (long) beans[i] * (n - i));
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minimumRemoval(beans: number[]): number {
    const n = beans.length;
    let sum = beans.reduce((a, c) => a + c, 0);
    beans.sort((a, b) => a - b);
    let ans = sum;
    for (let i = 0; i < n; i++) {
        let num = beans[i];
        ans = Math.min(sum - num * (n - i), ans);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimumRemoval(vector<int>& beans) {
        sort(beans.begin(), beans.end());
        long long s = accumulate(beans.begin(), beans.end(), 0ll);
        long long ans = s;
        int n = beans.size();
        for (int i = 0; i < n; ++i) ans = min(ans, s - 1ll * beans[i] * (n - i));
        return ans;
    }
};
```

### **Go**

```go
func minimumRemoval(beans []int) int64 {
	sort.Ints(beans)
	s := 0
	for _, v := range beans {
		s += v
	}
	ans := s
	n := len(beans)
	for i, v := range beans {
		ans = min(ans, s-v*(n-i))
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
