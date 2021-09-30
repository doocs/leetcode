# [1431. Kids With the Greatest Number of Candies](https://leetcode.com/problems/kids-with-the-greatest-number-of-candies)

[中文文档](/solution/1400-1499/1431.Kids%20With%20the%20Greatest%20Number%20of%20Candies/README.md)

## Description

<p>Given the array <code>candies</code> and the integer <code>extraCandies</code>, where <code>candies[i]</code> represents the number of candies that the <strong><em>ith</em></strong> kid has.</p>

<p>For each kid check if there is a way to distribute <code>extraCandies</code> among the kids such that he or she can have the <strong>greatest</strong> number of candies among them.&nbsp;Notice that multiple kids can have the <strong>greatest</strong> number of candies.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> candies = [2,3,5,1,3], extraCandies = 3

<strong>Output:</strong> [true,true,true,false,true] 

<strong>Explanation:</strong> 

Kid 1 has 2 candies and if he or she receives all extra candies (3) will have 5 candies --- the greatest number of candies among the kids. 

Kid 2 has 3 candies and if he or she receives at least 2 extra candies will have the greatest number of candies among the kids. 

Kid 3 has 5 candies and this is already the greatest number of candies among the kids. 

Kid 4 has 1 candy and even if he or she receives all extra candies will only have 4 candies. 

Kid 5 has 3 candies and if he or she receives at least 2 extra candies will have the greatest number of candies among the kids. 

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> candies = [4,2,1,1,2], extraCandies = 1

<strong>Output:</strong> [true,false,false,false,false] 

<strong>Explanation:</strong> There is only 1 extra candy, therefore only kid 1 will have the greatest number of candies among the kids regardless of who takes the extra candy.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> candies = [12,1,12], extraCandies = 10

<strong>Output:</strong> [true,false,true]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= candies.length &lt;= 100</code></li>
	<li><code>1 &lt;= candies[i] &lt;= 100</code></li>
	<li><code>1 &lt;= extraCandies &lt;= 50</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kidsWithCandies(self, candies: List[int], extraCandies: int) -> List[bool]:
        mx = max(candies)
        return [candy + extraCandies >= mx for candy in candies]
```

### **Java**

```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int mx = 0;
        for (int candy : candies) {
            mx = Math.max(mx, candy);
        }
        List<Boolean> res = new ArrayList<>();
        for (int candy : candies) {
            res.add(candy + extraCandies >= mx);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<bool> kidsWithCandies(vector<int>& candies, int extraCandies) {
        int mx = *max_element(candies.begin(), candies.end());
        vector<bool> res;
        for (int candy : candies) {
            res.push_back(candy + extraCandies >= mx);
        }
        return res;
    }
};
```

### **Go**

```go
func kidsWithCandies(candies []int, extraCandies int) []bool {
	mx := 0
	for _, candy := range candies {
		mx = max(mx, candy)
	}
	var res []bool
	for _, candy := range candies {
		res = append(res, candy+extraCandies >= mx)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
