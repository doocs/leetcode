# [1409. Queries on a Permutation With Key](https://leetcode.com/problems/queries-on-a-permutation-with-key)

[中文文档](/solution/1400-1499/1409.Queries%20on%20a%20Permutation%20With%20Key/README.md)

## Description

<p>Given the array <code>queries</code> of positive integers between <code>1</code> and <code>m</code>, you have to process all <code>queries[i]</code> (from <code>i=0</code> to <code>i=queries.length-1</code>) according to the following rules:</p>

<ul>
	<li>In the beginning, you have the permutation <code>P=[1,2,3,...,m]</code>.</li>
	<li>For the current <code>i</code>, find the position of <code>queries[i]</code> in the permutation <code>P</code> (<strong>indexing from 0</strong>) and then move this at the beginning of the permutation <code>P.</code>&nbsp;Notice that the position of <code>queries[i]</code> in <code>P</code> is the result for <code>queries[i]</code>.</li>
</ul>

<p>Return an array containing the result for the given <code>queries</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> queries = [3,1,2,1], m = 5

<strong>Output:</strong> [2,1,2,1] 

<strong>Explanation:</strong> The queries are processed as follow: 

For i=0: queries[i]=3, P=[1,2,3,4,5], position of 3 in P is <strong>2</strong>, then we move 3 to the beginning of P resulting in P=[3,1,2,4,5]. 

For i=1: queries[i]=1, P=[3,1,2,4,5], position of 1 in P is <strong>1</strong>, then we move 1 to the beginning of P resulting in P=[1,3,2,4,5]. 

For i=2: queries[i]=2, P=[1,3,2,4,5], position of 2 in P is <strong>2</strong>, then we move 2 to the beginning of P resulting in P=[2,1,3,4,5]. 

For i=3: queries[i]=1, P=[2,1,3,4,5], position of 1 in P is <strong>1</strong>, then we move 1 to the beginning of P resulting in P=[1,2,3,4,5]. 

Therefore, the array containing the result is [2,1,2,1].  

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> queries = [4,1,2,2], m = 4

<strong>Output:</strong> [3,1,2,0]

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> queries = [7,5,5,8,3], m = 8

<strong>Output:</strong> [6,5,0,7,5]

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 10^3</code></li>
	<li><code>1 &lt;= queries.length &lt;= m</code></li>
	<li><code>1 &lt;= queries[i] &lt;= m</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def processQueries(self, queries: List[int], m: int) -> List[int]:
        nums = list(range(1, m + 1))
        res = []
        for num in queries:
            res.append(nums.index(num))
            nums.remove(num)
            nums.insert(0, num)
        return res
```

### **Java**

```java
class Solution {
    public int[] processQueries(int[] queries, int m) {
        List<Integer> nums = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            nums.add(i + 1);
        }
        int[] res = new int[queries.length];
        int i = 0;
        for (int num : queries) {
            res[i++] = nums.indexOf(num);
            nums.remove(Integer.valueOf(num));
            nums.add(0, num);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> processQueries(vector<int>& queries, int m) {
        vector<int> nums(m);
        iota(nums.begin(), nums.end(), 1);
        vector<int> res;
        for (int num : queries)
        {
            int idx = -1;
            for (int i = 0; i < m; ++i)
            {
                if (nums[i] == num) {
                    idx = i;
                    break;
                }
            }
            res.push_back(idx);
            nums.erase(nums.begin() + idx);
            nums.insert(nums.begin(), num);
        }
        return res;
    }
};
```

### **Go**

```go
func processQueries(queries []int, m int) []int {
	nums := make([]int, m)
	for i := 0; i < m; i++ {
		nums[i] = i + 1
	}
	var res []int
	for _, num := range queries {
		idx := -1
		for i := 0; i < m; i++ {
			if nums[i] == num {
				idx = i
				break
			}
		}
		res = append(res, idx)
		nums = append(nums[:idx], nums[idx+1:]...)
		nums = append([]int{num}, nums...)
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
